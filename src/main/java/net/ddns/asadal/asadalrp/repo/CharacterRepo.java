package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Character;
import net.ddns.asadal.asadalrp.domain.CharacterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepo extends JpaRepository<Character, Long> {
    Character findByName(String name);

    List<Character> findAllByNameIgnoreCaseContains(String name);

    List<Character> findAllByCharacterTypeAndNameIgnoreCaseContains(CharacterType type, String name);
}
