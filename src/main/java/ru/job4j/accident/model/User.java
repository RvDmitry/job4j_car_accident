package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class User
 * Класс описывает пользователя.
 * @author Dmitry Razumov
 * @version 1
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Пароль пользователя.
     */
    private String password;
    /**
     * Имя пользователя.
     */
    private String username;
    /**
     * Роль пользователя.
     */
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
    /**
     * Состояние учетной записи пользователя. Включена или нет.
     */
    private boolean enabled;

    /**
     * Метод возвращает идентификатор.
     * @return Идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод задает идентификатор.
     * @param id Идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает пароль.
     * @return Пароль.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Метод задает пароль.
     * @param password Пароль.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Метод возвращает имя.
     * @return Имя.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод задает имя.
     * @param username Имя.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод возвращает роль.
     * @return Роль.
     */
    public Authority getAuthority() {
        return authority;
    }

    /**
     * Метод задает роль.
     * @param authority Роль.
     */
    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    /**
     * Метод проверяет состояние учетной записи пользователя. Включена учетная запись или нет.
     * @return Состояние учетной записи.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Метод задает состояние учетной записи.
     * @param enabled Состояние учетной записи.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}