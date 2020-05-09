package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Person;
import net.ddns.asadal.asadalrp.domain.dto.PersonDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.CharacterRepo;
import net.ddns.asadal.asadalrp.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${error.name.unavailable}")
    private String nameUnavailable;
    @Value("${error.file.trouble}")
    private String fileTrouble;

    private final PersonRepo personRepo;
    private final ActionRepo actionRepo;
    private final CharacterRepo characterRepo;

    public PersonService(
            PersonRepo personRepo,
            ActionRepo actionRepo,
            CharacterRepo characterRepo
    ) {
        this.personRepo = personRepo;
        this.actionRepo = actionRepo;
        this.characterRepo = characterRepo;
    }

    public String savePerson(PersonDto personDto) {
        String name = personDto.getName();
        if (StringUtils.isEmpty(name) || personRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Person person = createPerson(personDto);

        if (person == null) {
            return fileTrouble;
        }
        personRepo.save(person);
        return person.getName();
    }

    public List<Map<String, Object>> findPersons(String name) {
        List<Map<String, Object>> list = new ArrayList<>(2);
        list.add(new HashMap<String, Object>(2) {{
            put("text", "NPC");
            put("children", personRepo.findAllByNameIgnoreCaseContainsAndPlayable(name, false)
                    .stream().map(person -> new HashMap<String, Object>(2) {{
                        put("id", person.getId());
                        put("text", person.getName());
                    }}).collect(Collectors.toList()));
        }});
        list.add(new HashMap<String, Object>(2) {{
            put("text", "PLAYABLE");
            put("children", personRepo.findAllByNameIgnoreCaseContainsAndPlayable(name, true)
                    .stream().map(person -> new HashMap<String, Object>(2) {{
                        put("id", person.getId());
                        put("text", person.getName());
                    }}).collect(Collectors.toList()));
        }});
        return list;
    }

    public Person getPerson(Long id) {
        return personRepo.findById(id).orElse(null);
    }

    public boolean removePerson(Long id) {
        Person person = getPerson(id);

        if (person == null) {
            return false;
        }
        personRepo.delete(person);
        return true;
    }

    public String updatePerson(PersonDto personDto) {
        Person personFromDb = getPerson(personDto.getId());
        String name = personDto.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(personFromDb.getName()) && actionRepo.findByName(name) != null)) {
            return nameUnavailable;
        }
        Person person = createPerson(personDto);
        if (person == null) {
            return fileTrouble;
        }
        personRepo.save(person);
        return person.getName();
    }

    private Person createPerson(PersonDto personDto) {
        Person person = new Person();
        MultipartFile picture = personDto.getPicture();
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
            person.setPicture(filename);
        }
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setDescription(personDto.getDescription());
        person.setAdminHint(personDto.getAdminHint());
        person.setPlayable(personDto.isPlayable());
        person.setActions(new HashSet<>(actionRepo.findAllById(personDto.getActions())));
        person.setCharacters(new HashSet<>(characterRepo.findAllById(personDto.getCharacters())));
        return person;
    }
}
