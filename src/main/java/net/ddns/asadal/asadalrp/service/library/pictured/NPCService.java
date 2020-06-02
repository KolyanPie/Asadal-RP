package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.NPC;
import net.ddns.asadal.asadalrp.repo.library.NPCRepo;
import org.springframework.stereotype.Service;

@Service
public class NPCService extends AbstractPicturedLibraryService<NPC, NPCRepo> {

    public NPCService(NPCRepo repo) {
        super(repo);
    }
}
