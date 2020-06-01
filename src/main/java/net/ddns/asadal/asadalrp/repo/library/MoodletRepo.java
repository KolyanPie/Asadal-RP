package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.Moodlet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoodletRepo extends JpaRepository<Moodlet, Long> {
    Moodlet findByName(String name);

    List<Moodlet> findAllByNameIgnoreCaseContains(String name);
}
