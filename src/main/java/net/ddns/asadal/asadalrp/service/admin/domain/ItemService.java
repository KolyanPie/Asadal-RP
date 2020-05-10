package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Item;
import net.ddns.asadal.asadalrp.domain.dto.ItemDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.ItemRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import net.ddns.asadal.asadalrp.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        String filename = FileUtil.saveFile(itemDto.getPicture());
        if (filename == null) {
            return null;
        }
        Item item = new Item();

        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPicture(filename);
        item.setAdminHint(itemDto.getAdminHint());
        item.setMoodlets(new HashSet<>(moodletRepo.findAllById(itemDto.getMoodlets())));
        item.setActions(new HashSet<>(actionRepo.findAllById(itemDto.getActions())));
        return item;
    }
}
