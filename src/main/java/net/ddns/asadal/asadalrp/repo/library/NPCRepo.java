package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.NPC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NPCRepo extends JpaRepository<NPC, Long> {
    NPC findByName(String name);

    List<NPC> findAllByNameIgnoreCaseContains(String name);
}
