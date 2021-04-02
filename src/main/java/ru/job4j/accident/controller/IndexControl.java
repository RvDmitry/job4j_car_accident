package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.repository.AccidentMem;

/**
 * Class IndexControl
 * Класс реализует контроллер для главной страницы.
 * @author Dmitry Razumov
 * @version 1
 */
@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", AccidentMem.instOf().findAllAccidents());
        return "index";
    }
}
