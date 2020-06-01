package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.Character;
import net.ddns.asadal.asadalrp.domain.library.CharacterType;

import java.util.List;

public interface CharacterRepo extends AbstractLibraryRepo<Character> {
    List<Character> findAllByTypeAndNameIgnoreCaseContains(CharacterType type, String name);
}
