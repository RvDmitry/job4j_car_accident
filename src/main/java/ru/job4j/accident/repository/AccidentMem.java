package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.*;

/**
 * Class AccidentMem
 * Класс реализует хранилище инцидентов.
 * @author Dmitry Razumov
 * @version 1
 */
@Repository
public class AccidentMem {
    /**
     * Синглтон, создает объект хранилища.
     */
    private static final AccidentMem INST = new AccidentMem();
    /**
     * Коллекция хранит инциденты.
     */
    private final Map<Integer, Accident> accidents = new HashMap<>();

    /**
     * Конструктор - создает инциденты.
     */
    private AccidentMem() {
        Accident one = Accident.of(1, "Name 1", "Text 1", "Address 1");
        Accident two = Accident.of(2, "Name 2", "Text 2", "Address 2");
        Accident three = Accident.of(3, "Name 3", "Text 3", "Address 3");
        Accident four = Accident.of(4, "Name 4", "Text 4", "Address 4");
        Accident five = Accident.of(5, "Name 5", "Text 5", "Address 5");
        accidents.put(one.getId(), one);
        accidents.put(two.getId(), two);
        accidents.put(three.getId(), three);
        accidents.put(four.getId(), four);
        accidents.put(five.getId(), five);
    }

    /**
     * Метод возвращает синглтон.
     * @return Синглтон.
     */
    public static AccidentMem instOf() {
        return INST;
    }

    /**
     * Метод сохраняет инцидент в хранилище.
     * @param accident Инцидент.
     */
    public void save(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    /**
     * Метод ищет инцидент в хранилище по его идентификатору.
     * @param id Идентификатор.
     * @return Инцидент.
     */
    public Accident findAccidentById(int id) {
        return accidents.get(id);
    }

    /**
     * Метод возвращает все инциденты из хранилища.
     * @return Список инцидентов.
     */
    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }
}
