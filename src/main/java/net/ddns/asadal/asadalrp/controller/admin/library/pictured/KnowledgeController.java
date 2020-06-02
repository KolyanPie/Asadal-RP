package net.ddns.asadal.asadalrp.controller.admin.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Knowledge;
import net.ddns.asadal.asadalrp.service.library.pictured.KnowledgeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/knowledge")
public class KnowledgeController extends AbstractPicturedLibraryController<Knowledge, KnowledgeService> {

    public KnowledgeController(KnowledgeService service) {
        super(service);
    }
}
