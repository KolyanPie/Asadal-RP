package net.ddns.asadal.asadalrp.controller.admin.library;

import net.ddns.asadal.asadalrp.domain.library.AbstractLibraryDomain;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface CommonController<T extends AbstractLibraryDomain> {

    @GetMapping("/get")
    ResponseEntity<T> get(@RequestParam Long id);

    @DeleteMapping("/remove")
    ResponseEntity<Long> remove(@RequestParam Long id);

    @GetMapping("/list")
    ResponseEntity<Map<String, List<Map<String, Object>>>> find(
            @RequestParam(required = false, defaultValue = "") String term
    );
}
