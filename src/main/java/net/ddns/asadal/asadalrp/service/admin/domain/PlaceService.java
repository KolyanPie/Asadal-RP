package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Place;
import net.ddns.asadal.asadalrp.domain.dto.PlaceDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import net.ddns.asadal.asadalrp.repo.PersonRepo;
import net.ddns.asadal.asadalrp.repo.PlaceRepo;
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
public class PlaceService {
    @Value("${error.name.unavailable}")
    private String nameUnavailable;
    @Value("${error.file.trouble}")
    private String fileTrouble;

    private final FileUtil fileUtil;
    private final PlaceRepo placeRepo;
    private final MoodletRepo moodletRepo;
    private final ActionRepo actionRepo;
    private final PersonRepo personRepo;

    public PlaceService(
            FileUtil fileUtil, PlaceRepo placeRepo,
            MoodletRepo moodletRepo,
            ActionRepo actionRepo,
            PersonRepo personRepo
    ) {
        this.fileUtil = fileUtil;
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
        String filename = fileUtil.saveFile(placeDto.getPicture());
        if (filename == null) {
            return null;
        }
        Place place = new Place();

        place.setId(placeDto.getId());
        place.setName(placeDto.getName());
        place.setDescription(placeDto.getDescription());
        if (filename.equals("")) {
            place.setPicture(placeRepo.findByName(place.getName()).getPicture());
        } else {
            place.setPicture(filename);
        }
        place.setAdminHint(placeDto.getAdminHint());
        place.setMoodlets(new HashSet<>(moodletRepo.findAllById(placeDto.getMoodlets())));
        place.setActions(new HashSet<>(actionRepo.findAllById(placeDto.getActions())));
        place.setPersons(new HashSet<>(personRepo.findAllById(placeDto.getPersons())));
        return place;
    }
}
