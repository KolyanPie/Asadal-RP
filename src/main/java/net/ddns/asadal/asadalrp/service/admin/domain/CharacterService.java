package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Character;
import net.ddns.asadal.asadalrp.domain.CharacterType;
import net.ddns.asadal.asadalrp.domain.dto.CharacterDto;
import net.ddns.asadal.asadalrp.repo.CharacterRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    @Value("${error.name.unavailable}")
    private String nameUnavailable;

    private final CharacterRepo characterRepo;
    private final MoodletRepo moodletRepo;

    public CharacterService(CharacterRepo characterRepo, MoodletRepo moodletRepo) {
        this.characterRepo = characterRepo;
        this.moodletRepo = moodletRepo;
    }

    public String saveCharacter(CharacterDto characterDto) {
        String name = characterDto.getName();
        if (StringUtils.isEmpty(name) || characterRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Character character = createCharacter(characterDto);

        characterRepo.save(character);
        return character.getName();
    }

    public List<Map<String, Object>> findCharacters(String name) {
        List<Map<String, Object>> list = new ArrayList<>(CharacterType.values().length);

        for (CharacterType value : CharacterType.values()) {
            list.add(new HashMap<String, Object>(2) {{
                put("text", value);
                put("children", characterRepo.findAllByCharacterTypeAndNameIgnoreCaseContains(value, name)
                        .stream().map(character -> new HashMap<String, Object>(2) {{
                            put("id", character.getId());
                            put("text", character.getName());
                        }}).collect(Collectors.toList()));
            }});
        }
        return list;
    }

    public Character getCharacter(Long id) {
        return characterRepo.findById(id).orElse(null);
    }

    public boolean removeCharacter(Long id) {
        Character character = getCharacter(id);

        if (character == null) {
            return false;
        }
        characterRepo.delete(character);
        return true;
    }

    public String updateCharacter(CharacterDto characterDto) {
        Character characterFromDb = getCharacter(characterDto.getId());
        String name = characterDto.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(characterFromDb.getName()) && characterRepo.findByName(name) != null)) {
            return nameUnavailable;
        }
        Character character = createCharacter(characterDto);

        characterRepo.save(character);
        return character.getName();
    }

    private Character createCharacter(CharacterDto characterDto) {
        Character character = new Character();

        character.setId(characterDto.getId());
        character.setName(characterDto.getName());
        character.setDescriptions(characterDto.getDescriptions());
        character.setAdminHint(characterDto.getAdminHint());
        character.setCharacterType(characterDto.getCharacterType());
        character.setMoodlets(new HashSet<>(moodletRepo.findAllById(characterDto.getMoodlets())));
        return character;
    }
}
