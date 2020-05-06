package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findByName(String name);
}
