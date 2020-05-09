package net.ddns.asadal.asadalrp.controller.admin.domain;

import net.ddns.asadal.asadalrp.domain.Character;
import net.ddns.asadal.asadalrp.domain.dto.CharacterDto;
import net.ddns.asadal.asadalrp.service.admin.domain.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/character")
public class CharacterController {
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCharacter(CharacterDto character) {
        String answer = characterService.saveCharacter(character);
        if (answer.equals(character.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCharacter(CharacterDto characterDto) {
        if (characterDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (characterService.getCharacter(characterDto.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = characterService.updateCharacter(characterDto);
        if (answer.equals(characterDto.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Character> getCharacter(@RequestParam Long id) {
        Character character = characterService.getCharacter(id);

        if (character == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(character);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removeCharacter(@RequestParam Long id) {
        if (characterService.removeCharacter(id)) {
            return ResponseEntity.ok(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> findCharacters(
            @RequestParam(required = false, defaultValue = "") String term
    ) {
        return ResponseEntity.ok(Collections.singletonMap("results", characterService.findCharacters(term)));
    }
}
