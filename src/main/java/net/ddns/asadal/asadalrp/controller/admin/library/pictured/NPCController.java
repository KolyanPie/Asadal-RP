package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.NPC;
import net.ddns.asadal.asadalrp.service.library.pictured.NPCService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/npc")
public class NPCController extends AbstractPicturedLibraryController<NPC, NPCService> {

    public NPCController(NPCService service) {
        super(service);
    }
}
