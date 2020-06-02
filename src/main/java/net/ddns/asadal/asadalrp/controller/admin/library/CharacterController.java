package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.library.Character;
import net.ddns.asadal.asadalrp.service.library.CharacterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/character")
public class CharacterController extends AbstractUnPicturedLibraryController<Character, CharacterService> {

    public CharacterController(CharacterService service) {
        super(service);
    }
}
