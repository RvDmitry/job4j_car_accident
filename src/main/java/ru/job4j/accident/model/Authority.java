package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class Authority
 * Класс описывает роль пользователя.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "authorities")
public class Authority {
    /**
     * Идентификатор роли.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Наименование роли.
     */
    private String authority;

    /**
     * Метод возвращает идентификатор.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод задает идентификатор.
     * @param id Идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает наименование роли.
     * @return Наименование.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Метод задает наименование.
     * @param authority Наименование.
     */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority = (Authority) o;
        return id == authority.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
