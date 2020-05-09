package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Item;
import net.ddns.asadal.asadalrp.domain.dto.ItemDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.ItemRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${error.name.unavailable}")
    private String nameUnavailable;
    @Value("${error.file.trouble}")
    private String fileTrouble;

    private final ItemRepo itemRepo;
    private final MoodletRepo moodletRepo;
    private final ActionRepo actionRepo;

    public ItemService(ItemRepo itemRepo, MoodletRepo moodletRepo, ActionRepo actionRepo) {
        this.itemRepo = itemRepo;
        this.moodletRepo = moodletRepo;
        this.actionRepo = actionRepo;
    }

    public String saveItem(ItemDto itemDto) {
        String name = itemDto.getName();
        if (StringUtils.isEmpty(name) || itemRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Item item = createItem(itemDto);

        if (item == null) {
            return fileTrouble;
        }
        itemRepo.save(item);
        return item.getName();
    }

    public List<Map<String, Object>> findItems(String name) {
        return itemRepo.findAllByNameIgnoreCaseContains(name)
                .stream().map(item -> new HashMap<String, Object>(2) {{
                    put("id", item.getId());
                    put("text", item.getName());
                }}).collect(Collectors.toList());
    }

    public Item getItem(Long id) {
        return itemRepo.findById(id).orElse(null);
    }

    public boolean removeItem(Long id) {
        Item item = getItem(id);

        if (item == null) {
            return false;
        }
        itemRepo.delete(item);
        return true;
    }

    public String updateItem(ItemDto itemDto) {
        Item itemFromDb = getItem(itemDto.getId());
        String name = itemDto.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(itemFromDb.getName()) && actionRepo.findByName(name) != null)) {
            return nameUnavailable;
        }
        Item item = createItem(itemDto);
        if (item == null) {
            return fileTrouble;
        }
        itemRepo.save(item);
        return item.getName();
    }

    private Item createItem(ItemDto itemDto) {
        Item item = new Item();
        MultipartFile picture = itemDto.getPicture();
        if (picture != null && !picture.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "." + picture.getOriginalFilename();
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                if (!uploadDir.mkdir()) {
                    return null;
                }
            }
            try {
                picture.transferTo(new File(uploadPath + "/" + filename));
            } catch (IOException e) {
                return null;
            }
            item.setPicture(filename);
        }
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setAdminHint(itemDto.getAdminHint());
        item.setMoodlets(new HashSet<>(moodletRepo.findAllById(itemDto.getMoodlets())));
        item.setActions(new HashSet<>(actionRepo.findAllById(itemDto.getActions())));
        return item;
    }
}
