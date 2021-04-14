package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

/**
 * Class UserRepository
 *
 * @author Dmitry Razumov
 * @version 1
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
