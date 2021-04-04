package ru.job4j.accident.model;

import java.util.Objects;

/**
 * Class AccidentType
 * Класс описывает тип инцидента.
 * @author Dmitry Razumov
 * @version 1
 */
public class AccidentType {
    /**
     * Идентификатор типа.
     */
    private int id;
    /**
     * Наименование типа.
     */
    private String name;

    /**
     * Фабрика создает тип.
     * @param id Идентификатор.
     * @param name Наименование.
     * @return Тип.
     */
    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

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
     * Метод возвращает наименование.
     * @return наименование.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод задает наименование.
     * @param name Наименование.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccidentType{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
