package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {
    Person findByName(String name);

    List<Person> findAllByNameIgnoreCaseContains(String name);

    List<Person> findAllByNameIgnoreCaseContainsAndPlayable(String name, boolean playable);
}
