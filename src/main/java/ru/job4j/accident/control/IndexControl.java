package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "index";
    }
}
