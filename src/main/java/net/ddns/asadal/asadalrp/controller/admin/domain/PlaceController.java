package net.ddns.asadal.asadalrp.controller.admin.domain;

import net.ddns.asadal.asadalrp.domain.Place;
import net.ddns.asadal.asadalrp.domain.dto.PlaceDto;
import net.ddns.asadal.asadalrp.service.admin.domain.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/place")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> savePlace(PlaceDto place) {
        String answer = placeService.savePlace(place);
        if (answer.equals(place.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updatePlace(PlaceDto placeDto) {
        if (placeDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (placeService.getPlace(placeDto.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = placeService.updatePlace(placeDto);
        if (answer.equals(placeDto.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Place> getPlace(@RequestParam Long id) {
        Place place = placeService.getPlace(id);

        if (place == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(place);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removePlace(@RequestParam Long id) {
        if (placeService.removePlace(id)) {
            return ResponseEntity.ok(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> findPlaces(
            @RequestParam(required = false, defaultValue = "") String term
    ) {
        return ResponseEntity.ok(Collections.singletonMap("results", placeService.findPlaces(term)));
    }
}
