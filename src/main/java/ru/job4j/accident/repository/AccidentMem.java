package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class AccidentMem
 * Класс реализует хранилище инцидентов.
 * @author Dmitry Razumov
 * @version 1
 */
@Repository
public class AccidentMem {
    /**
     * Поле генерирует идентификатор инцидента.
     */
    private static final AtomicInteger ACCIDENT_ID = new AtomicInteger();
    /**
     * Коллекция хранит инциденты.
     */
    private static final Map<Integer, Accident> ACCIDENTS = new HashMap<>();
    /**
     * Коллекция хранит типы инцидентов.
     */
    private final Map<Integer, AccidentType> types = new HashMap<>();
    /**
     * Коллекция хранит статьи нарушений.
     */
    private final Map<Integer, Rule> rules = new HashMap<>();

    /**
     * Конструктор инициализирует хранилище инцидентов.
     */
    public AccidentMem() {
        AccidentType first = AccidentType.of(1, "Две машины");
        AccidentType second = AccidentType.of(2, "Машина и человек");
        AccidentType third = AccidentType.of(3, "Машина и велосипед");
        types.put(first.getId(), first);
        types.put(second.getId(), second);
        types.put(third.getId(), third);
        Rule one = Rule.of(1, "Статья. 1");
        Rule two = Rule.of(2, "Статья. 2");
        Rule three = Rule.of(3, "Статья. 3");
        rules.put(one.getId(), one);
        rules.put(two.getId(), two);
        rules.put(three.getId(), three);
    }

    /**
     * Метод сохраняет инцидент в хранилище.
     * @param accident Инцидент.
     */
    public void create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        ACCIDENTS.put(accident.getId(), accident);
    }

    /**
     * Метод ищет инцидент в хранилище по его идентификатору.
     * @param id Идентификатор.
     * @return Инцидент.
     */
    public Accident findAccidentById(int id) {
        return ACCIDENTS.get(id);
    }

    /**
     * Метод возвращает все инциденты из хранилища.
     * @return Список инцидентов.
     */
    public Collection<Accident> findAllAccidents() {
        return ACCIDENTS.values();
    }

    /**
     * Метод возвращает список типов инцидента.
     * @return Список типов.
     */
    public Collection<AccidentType> findAllTypes() {
        return types.values();
    }

    /**
     * Метод возвращает тип инцидента по его идентификатору.
     * @param id Идентификатор.
     * @return Тип.
     */
    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    /**
     * Метод возвращает список статей.
     * @return Список статей.
     */
    public Collection<Rule> findAllRules() {
        return rules.values();
    }

    /**
     * Метод возвращает статью по ее идентификатору.
     * @param id Идентификатор.
     * @return Статья.
     */
    public Rule findRuleById(int id) {
        return rules.get(id);
    }
}
