package net.ddns.asadal.asadalrp.controller;

import net.ddns.asadal.asadalrp.domain.dto.MoodletDto;
import net.ddns.asadal.asadalrp.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/library")
    public ModelAndView library() {
        return new ModelAndView("/static/admin/library.html");
    }

    @PostMapping("/save/moodlet")
    public ResponseEntity<String> saveAction(
            MoodletDto moodlet
    ) {
        String answer = libraryService.saveDomain(moodlet);
        if (answer.equals(moodlet.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }
}
