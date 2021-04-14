package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

/**
 * Class AuthorityRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}
