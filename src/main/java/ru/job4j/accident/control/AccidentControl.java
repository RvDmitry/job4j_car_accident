package ru.job4j.accident.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

/**
 * Class AccidentControl
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Controller
public class AccidentControl {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());

    private final AccidentMem accidents;

    public AccidentControl(AccidentMem accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        LOG.info("Сохранение инцидента: {}", accident);
        accidents.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findById(id);
        LOG.info("Редактирование инцидента: {}", accident);
        model.addAttribute("accident", accident);
        return "accident/edit";
    }
}
