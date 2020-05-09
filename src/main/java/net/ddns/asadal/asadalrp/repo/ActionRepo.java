package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepo extends JpaRepository<Action, Long> {
    Action findByName(String name);

    List<Action> findAllByNameIgnoreCaseContains(String name);
}
