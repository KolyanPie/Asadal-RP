package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Place;
import net.ddns.asadal.asadalrp.repo.library.PlaceRepo;
import org.springframework.stereotype.Service;

@Service
public class PlaceService extends AbstractPicturedLibraryService<Place, PlaceRepo> {

    public PlaceService(PlaceRepo repo) {
        super(repo);
    }
}
