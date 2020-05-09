package net.ddns.asadal.asadalrp.service.admin.domain;

import net.ddns.asadal.asadalrp.domain.Moodlet;
import net.ddns.asadal.asadalrp.domain.dto.MoodletDto;
import net.ddns.asadal.asadalrp.repo.MoodletRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MoodletService {
    @Value("${error.name.unavailable}")
    private String nameUnavailable;

    private final MoodletRepo moodletRepo;

    public MoodletService(MoodletRepo moodletRepo) {
        this.moodletRepo = moodletRepo;
    }

    public String saveMoodlet(MoodletDto moodletDto) {
        String name = moodletDto.getName();
        if (StringUtils.isEmpty(name) || moodletRepo.findByName(name) != null) {
            return nameUnavailable;
        }
        Moodlet moodlet = createMoodlet(moodletDto);

        moodletRepo.save(moodlet);
        return moodlet.getName();
    }

    public List<Map<String, Object>> findMoodlets(String name) {
        return moodletRepo.findAllByNameIgnoreCaseContains(name)
                .stream().map(moodlet -> new HashMap<String, Object>(2) {{
                    put("id", moodlet.getId());
                    put("text", moodlet.getName());
                }}).collect(Collectors.toList());
    }

    public Moodlet getMoodlet(Long id) {
        return moodletRepo.findById(id).orElse(null);
    }

    public boolean removeMoodlet(Long id) {
        Moodlet moodlet = getMoodlet(id);

        if (moodlet == null) {
            return false;
        }
        moodletRepo.delete(moodlet);
        return true;
    }

    public String updateMoodlet(MoodletDto moodletDto) {
        Moodlet moodletFromDb = getMoodlet(moodletDto.getId());
        String name = moodletDto.getName();
        if (StringUtils.isEmpty(name) || (!name.equals(moodletFromDb.getName()) && moodletRepo.findByName(name) != null)) {
            return nameUnavailable;
        }
        Moodlet moodlet = createMoodlet(moodletDto);

        moodletRepo.save(moodlet);
        return moodlet.getName();
    }

    private Moodlet createMoodlet(MoodletDto moodletDto) {
        Moodlet moodlet = new Moodlet();

        moodlet.setId(moodletDto.getId());
        moodlet.setName(moodletDto.getName());
        moodlet.setDescription(moodletDto.getDescription());
        moodlet.setDurability(moodletDto.getDurability());
        moodlet.setValue(moodletDto.getValue());
        return moodlet;
    }
}
