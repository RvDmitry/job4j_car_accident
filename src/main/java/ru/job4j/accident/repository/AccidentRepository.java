package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;

import java.util.List;

/**
 * Class AccidentRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a left join fetch a.rules order by a.id")
    List<Accident> findAllWithRules();

    @Query("select distinct a from Accident a left join fetch a.rules where a.id = :id")
    Accident findByIdWithRules(@Param("id") int id);
}
