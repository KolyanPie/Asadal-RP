package net.ddns.asadal.asadalrp.service.library;

import net.ddns.asadal.asadalrp.domain.library.Moodlet;
import net.ddns.asadal.asadalrp.repo.library.MoodletRepo;
import org.springframework.stereotype.Service;

@Service
public class MoodletService extends AbstractUnPicturedLibraryService<Moodlet, MoodletRepo> {

    public MoodletService(MoodletRepo repo) {
        super(repo);
    }
}
