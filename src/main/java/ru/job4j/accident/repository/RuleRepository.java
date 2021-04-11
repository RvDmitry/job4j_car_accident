package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

/**
 * Interface RuleRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
