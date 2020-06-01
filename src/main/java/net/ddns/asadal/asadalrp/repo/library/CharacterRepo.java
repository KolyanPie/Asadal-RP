package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.Character;
import net.ddns.asadal.asadalrp.domain.library.CharacterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepo extends JpaRepository<Character, Long> {
    Character findByName(String name);

    List<Character> findAllByNameIgnoreCaseContains(String name);

    List<Character> findAllByTypeAndNameIgnoreCaseContains(CharacterType type, String name);
}
