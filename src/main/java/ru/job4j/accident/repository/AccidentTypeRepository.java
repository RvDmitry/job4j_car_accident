package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

/**
 * Interface AccidentTypeRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
