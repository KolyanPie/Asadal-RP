package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeRepo extends JpaRepository<Knowledge, Long> {
    Knowledge findByName(String name);

    List<Knowledge> findAllByNameIgnoreCaseContains(String name);
}
