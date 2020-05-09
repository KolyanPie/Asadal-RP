package net.ddns.asadal.asadalrp.controller.admin.domain;

import net.ddns.asadal.asadalrp.domain.Action;
import net.ddns.asadal.asadalrp.domain.dto.ActionDto;
import net.ddns.asadal.asadalrp.service.admin.domain.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/action")
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveAction(ActionDto action) {
        String answer = actionService.saveAction(action);
        if (answer.equals(action.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateAction(ActionDto actionDto) {
        if (actionService.getAction(actionDto.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = actionService.updateAction(actionDto);
        if (answer.equals(actionDto.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Action> getAction(@RequestParam Long id) {
        Action action = actionService.getAction(id);

        if (action == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(action);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removeAction(@RequestParam Long id) {
        if (actionService.removeAction(id)) {
            return ResponseEntity.ok(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> findActions(
            @RequestParam(required = false, defaultValue = "") String term
    ) {
        return ResponseEntity.ok(Collections.singletonMap("results", actionService.findActions(term)));
    }
}
