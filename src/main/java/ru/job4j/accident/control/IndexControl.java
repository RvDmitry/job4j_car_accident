package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

/**
 * Class IndexControl
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        List<String> lists = Arrays.asList("One", "Two", "Three", "Four", "Five");
        model.addAttribute("lists", lists);
        return "index";
    }
}
