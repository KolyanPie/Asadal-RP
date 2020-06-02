package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.library.Character;
import net.ddns.asadal.asadalrp.domain.library.CharacterType;
import net.ddns.asadal.asadalrp.repo.library.CharacterRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CharacterService extends AbstractUnPicturedLibraryService<Character, CharacterRepo> {

    public CharacterService(CharacterRepo repo) {
        super(repo);
    }


    @Override
    public List<Map<String, Object>> find(String name) {
        List<Map<String, Object>> list = new ArrayList<>(CharacterType.values().length);

        for (CharacterType value : CharacterType.values()) {
            list.add(new HashMap<String, Object>(2) {{
                put("text", value);
                put("children", repo.findAllByTypeAndNameIgnoreCaseContains(value, name)
                        .stream().map(character -> new HashMap<String, Object>(2) {{
                            put("id", character.getId());
                            put("text", character.getName());
                        }}).collect(Collectors.toList()));
            }});
        }
        return list;
    }
}
