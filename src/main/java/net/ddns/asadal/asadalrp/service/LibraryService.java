package net.ddns.asadal.asadalrp.service;

import net.ddns.asadal.asadalrp.domain.Action;
import net.ddns.asadal.asadalrp.domain.Moodlet;
import net.ddns.asadal.asadalrp.domain.dto.ActionDto;
import net.ddns.asadal.asadalrp.domain.dto.MoodletDto;
import net.ddns.asadal.asadalrp.repo.ActionRepo;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LibraryService {
    private final ActionRepo actionRepo;
    private final MoodletRepo moodletRepo;

    private final String NAME_UNAVAILABLE = "name is not available";

    public LibraryService(
            ActionRepo actionRepo,
            MoodletRepo moodletRepo
    ) {
        this.actionRepo = actionRepo;
        this.moodletRepo = moodletRepo;
    }

    public String saveDomain(ActionDto actionDto) {
        String name = actionDto.getName();
        if (StringUtils.isEmpty(name) || actionRepo.findByName(name) != null) {
            return NAME_UNAVAILABLE;
        }
        Action action = actionDto.createAction();

        actionRepo.save(action);
        return action.getName();
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
