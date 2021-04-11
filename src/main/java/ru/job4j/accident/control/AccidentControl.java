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
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private final AccidentRepository accidentRepository;
    private final RuleRepository ruleRepository;
    private final AccidentTypeRepository typeRepository;

    public AccidentControl(AccidentRepository accidents,
                           RuleRepository ruleRepository,
                           AccidentTypeRepository typeRepository) {
        this.accidentRepository = accidents;
        this.ruleRepository = ruleRepository;
        this.typeRepository = typeRepository;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        List<AccidentType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        AccidentType type = typeRepository.findById(accident.getType().getId()).get();
        accident.setType(type);
        Set<Rule> rules = new HashSet<>();
        String[] ids = req.getParameterValues("rIds");
        if (ids != null) {
            for (String id : ids) {
                rules.add(ruleRepository.findById(Integer.parseInt(id)).get());
            }
        }
        accident.setRules(rules);
        accident = accidentRepository.save(accident);
        LOG.info("Инцидент - {} сохранен.", accident);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model) {
        Accident accident = accidentRepository.findByIdWithRules(id);
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        List<AccidentType> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        LOG.info("Редактирование инцидента: {}", accident);
        model.addAttribute("accident", accident);
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "accident/edit";
    }
}
