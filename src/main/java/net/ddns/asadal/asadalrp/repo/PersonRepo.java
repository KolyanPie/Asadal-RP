package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Long> {}
