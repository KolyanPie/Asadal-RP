package net.ddns.asadal.asadalrp.service;

import net.ddns.asadal.asadalrp.domain.Moodlet;
import net.ddns.asadal.asadalrp.domain.dto.MoodletDto;
import net.ddns.asadal.asadalrp.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LibraryService {
    private final MoodletRepo moodletRepo;

    private final String NAME_UNAVAILABLE = "name is not available";

    public LibraryService(
            MoodletRepo moodletRepo
    ) {
        this.moodletRepo = moodletRepo;
    }

    public String saveDomain(MoodletDto moodletDto) {
        String name = moodletDto.getName();
        if (StringUtils.isEmpty(name) || moodletRepo.findByName(name) != null) {
            return NAME_UNAVAILABLE;
        }
        Moodlet moodlet = moodletDto.createMoodlet();

        moodletRepo.save(moodlet);
        return moodlet.getName();
    }
}
