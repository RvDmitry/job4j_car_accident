package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

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
    public Accident findById(int id) {
        return ACCIDENTS.get(id);
    }

    /**
     * Метод возвращает все инциденты из хранилища.
     * @return Список инцидентов.
     */
    public Collection<Accident> findAll() {
        return ACCIDENTS.values();
    }
}
