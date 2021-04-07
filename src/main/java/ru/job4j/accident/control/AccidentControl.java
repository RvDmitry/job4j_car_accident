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
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Class AccidentControl
 *
 * @author Dmitry Razumov
 * @version 1
 */
@Controller
public class AccidentControl {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentControl.class.getName());

    private final AccidentJdbcTemplate accidents;

    public AccidentControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("rules", accidents.findAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        AccidentType type = accidents.findTypeById(accident.getType().getId());
        accident.setType(type);
        Set<Rule> rules = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String id : ids) {
                rules.add(accidents.findRuleById(Integer.parseInt(id)));
            }
        }
        accident.setRules(rules);
        accident = accidents.save(accident);
        LOG.info("Инцидент - {} сохранен.", accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findAccidentById(id);
        LOG.info("Редактирование инцидента: {}", accident);
        model.addAttribute("accident", accident);
        model.addAttribute("types", accidents.findAllTypes());
        model.addAttribute("rules", accidents.findAllRules());
        return "accident/edit";
    }
}
