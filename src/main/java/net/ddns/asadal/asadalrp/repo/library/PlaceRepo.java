package net.ddns.asadal.asadalrp.repo.library;

import net.ddns.asadal.asadalrp.domain.library.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {
    Place findByName(String name);

    List<Place> findAllByNameIgnoreCaseContains(String name);
}
