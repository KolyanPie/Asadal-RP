package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Place;
import net.ddns.asadal.asadalrp.domain.dto.PlaceDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import net.ddns.asadal.asadalrp.repo.PersonRepo;
import net.ddns.asadal.asadalrp.repo.PlaceRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaceService {
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${error.name.unavailable}")
    private String nameUnavailable;
    @Value("${error.file.trouble}")
    private String fileTrouble;

    private final PlaceRepo placeRepo;
    private final MoodletRepo moodletRepo;
    private final ActionRepo actionRepo;
    private final PersonRepo personRepo;

    public PlaceService(
            PlaceRepo placeRepo,
            MoodletRepo moodletRepo,
            ActionRepo actionRepo,
            PersonRepo personRepo
    ) {
        this.placeRepo = placeRepo;
        this.moodletRepo = moodletRepo;
        this.actionRepo = actionRepo;
        this.personRepo = personRepo;
    }

    public String savePlace(PlaceDto placeDto) {
        String name = placeDto.getName();
        if (StringUtils.isEmpty(name) || placeRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Place place = createPlace(placeDto);

        if (place == null) {
            return fileTrouble;
        }
        placeRepo.save(place);
        return place.getName();
    }

    public List<Map<String, Object>> findPlaces(String name) {
        return placeRepo.findAllByNameIgnoreCaseContains(name)
                .stream().map(place -> new HashMap<String, Object>(2) {{
                    put("id", place.getId());
                    put("text", place.getName());
                }}).collect(Collectors.toList());
    }

    public Place getPlace(Long id) {
        return placeRepo.findById(id).orElse(null);
    }

    public boolean removePlace(Long id) {
        Place place = getPlace(id);

        if (place == null) {
            return false;
        }
        placeRepo.delete(place);
        return true;
    }

    public String updatePlace(PlaceDto placeDto) {
        Place placeFromDb = getPlace(placeDto.getId());
        String name = placeDto.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(placeFromDb.getName()) && actionRepo.findByName(name) != null)) {
            return nameUnavailable;
        }
        Place place = createPlace(placeDto);
        if (place == null) {
            return fileTrouble;
        }
        placeRepo.save(place);
        return place.getName();
    }

    private Place createPlace(PlaceDto placeDto) {
        Place place = new Place();
        MultipartFile picture = placeDto.getPicture();
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
            place.setPicture(filename);
        }

        place.setId(placeDto.getId());
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        place.setAdminHint(placeDto.getAdminHint());
        place.setMoodlets(new HashSet<>(moodletRepo.findAllById(placeDto.getMoodlets())));
        place.setActions(new HashSet<>(actionRepo.findAllById(placeDto.getActions())));
        place.setPersons(new HashSet<>(personRepo.findAllById(placeDto.getPersons())));
        return place;
    }
}
