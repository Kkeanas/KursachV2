package com.project.kursachv2.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControllerL {
    @GetMapping("/main")
    public String main() {
        return "main";
    }
    @GetMapping("/report/{id}")
    public String report(@PathVariable int id) {
        return "report";
    }
}
