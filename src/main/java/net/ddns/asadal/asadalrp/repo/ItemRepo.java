package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findByName(String name);

    List<Item> findAllByNameIgnoreCaseContains(String name);
}
