package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.library.Moodlet;
import net.ddns.asadal.asadalrp.service.library.MoodletService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/moodlet")
public class MoodletController extends AbstractUnPicturedLibraryController<Moodlet, MoodletService> {

    public MoodletController(MoodletService service) {
        super(service);
    }
}
