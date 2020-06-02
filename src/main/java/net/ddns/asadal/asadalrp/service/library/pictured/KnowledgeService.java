package net.ddns.asadal.asadalrp.service.library.pictured;

import net.ddns.asadal.asadalrp.domain.library.Knowledge;
import net.ddns.asadal.asadalrp.repo.library.KnowledgeRepo;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeService extends AbstractPicturedLibraryService<Knowledge, KnowledgeRepo> {

    public KnowledgeService(KnowledgeRepo repo) {
        super(repo);
    }
}
