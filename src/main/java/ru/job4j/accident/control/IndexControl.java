package ru.job4j.accident.control;

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

    private final AccidentMem accidents;

    public IndexControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.findAllAccidents());
        return "index";
    }
}
