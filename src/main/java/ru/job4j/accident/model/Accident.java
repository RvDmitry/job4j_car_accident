package ru.job4j.accident.model;

import java.util.Objects;
import java.util.Set;

/**
 * Class Accident
 * Класс описывает инцидент.
 * @author Dmitry Razumov
 * @version 1
 */
public class Accident {
    /**
     * Идентификатор инцидента.
     */
    private int id;
    /**
     * Наименование инцидента.
     */
    private String name;
    /**
     * Описание инцидента.
     */
    private String text;
    /**
     * Адрес инцидента.
     */
    private String address;
    /**
     * Тип инцидента.
     */
    private AccidentType type;
    /**
     * Набор статей, которые были нарушены при инциденте.
     */
    private Set<Rule> rules;

    /**
     * Фабрика создает инцидент.
     * @param id Идентификатор.
     * @param name Наименование.
     * @param text Описание.
     * @param address Адрес.
     * @return Инцидент.
     */
    public static Accident of(int id, String name, String text, String address, AccidentType type) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        return accident;
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

    /**
     * Метод возвращает описание.
     * @return Описание.
     */
    public String getText() {
        return text;
    }

    /**
     * Метод задает описание.
     * @param text Описание.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Метод возвращает адрес.
     * @return Адрес.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Метод задает адрес.
     * @param address Адрес.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Метод возвращает тип инцидента.
     * @return Тип инцидента.
     */
    public AccidentType getType() {
        return type;
    }

    /**
     * Метод задает тип инцидента.
     * @param type Тип инцидента.
     */
    public void setType(AccidentType type) {
        this.type = type;
    }

    /**
     * Метод возвращает набор статей.
     * @return Набор статей.
     */
    public Set<Rule> getRules() {
        return rules;
    }

    /**
     * Метод задает набор статей.
     * @param rules Набор статей.
     */
    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", address='" + address + '\''
                + ", type=" + type
                + ", rules=" + rules
                + '}';
    }
}
