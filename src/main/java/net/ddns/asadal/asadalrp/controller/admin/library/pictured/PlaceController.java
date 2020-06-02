package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Place;
import net.ddns.asadal.asadalrp.service.library.pictured.PlaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/place")
public class PlaceController extends AbstractPicturedLibraryController<Place, PlaceService> {

    public PlaceController(PlaceService service) {
        super(service);
    }
}
