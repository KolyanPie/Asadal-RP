package net.ddns.asadal.asadalrp.controller.admin.domain;

import net.ddns.asadal.asadalrp.domain.Item;
import net.ddns.asadal.asadalrp.domain.dto.ItemDto;
import net.ddns.asadal.asadalrp.service.admin.domain.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveItem(ItemDto item) {
        String answer = itemService.saveItem(item);
        if (answer.equals(item.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateItem(ItemDto itemDto) {
        if (itemDto.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (itemService.getItem(itemDto.getId()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String answer = itemService.updateItem(itemDto);
        if (answer.equals(itemDto.getName())) {
            return ResponseEntity.ok(answer);
        } else {
            return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Item> getItem(@RequestParam Long id) {
        Item item = itemService.getItem(id);

        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removeItem(@RequestParam Long id) {
        if (itemService.removeItem(id)) {
            return ResponseEntity.ok(id);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> findItems(
            @RequestParam(required = false, defaultValue = "") String term
    ) {
        return ResponseEntity.ok(Collections.singletonMap("results", itemService.findItems(term)));
    }
}
