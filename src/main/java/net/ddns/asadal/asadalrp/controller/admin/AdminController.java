package net.ddns.asadal.asadalrp.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/library")
    public ModelAndView library() {
        return new ModelAndView("/static/admin/library.html");
    }
}
