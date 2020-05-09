package net.ddns.asadal.asadalrp.repo;

import net.ddns.asadal.asadalrp.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {
    Place findByName(String name);

    List<Place> findAllByNameIgnoreCaseContains(String name);
}
