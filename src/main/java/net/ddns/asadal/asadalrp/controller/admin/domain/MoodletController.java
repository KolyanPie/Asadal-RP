package net.ddns.asadal.asadalrp.controller.admin.domain;

import net.ddns.asadal.asadalrp.domain.dto.MoodletDto;
import net.ddns.asadal.asadalrp.service.admin.domain.MoodletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class MoodletController {
    private final MoodletService moodletService;

    public MoodletController(MoodletService moodletService) {
        this.moodletService = moodletService;
    }

    @PostMapping("/save/moodlet")
    public ResponseEntity<String> saveAction(
            MoodletDto moodlet
    ) {
        String answer = moodletService.saveMoodlet(moodlet);
        if (answer.equals(moodlet.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list/moodlet")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> findMoodlet(
            @RequestParam(required = false, defaultValue = "") String term
    ) {
        return ResponseEntity.ok(Collections.singletonMap("results", moodletService.findMoodlets(term)));
    }
}
