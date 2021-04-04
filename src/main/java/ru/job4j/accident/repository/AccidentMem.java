package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

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
     * Конструктор инициализирует хранилище инцидентов.
     */
    public AccidentMem() {
        AccidentType first = AccidentType.of(1, "Две машины");
        AccidentType second = AccidentType.of(2, "Машина и человек");
        AccidentType third = AccidentType.of(3, "Машина и велосипед");
        types.put(first.getId(), first);
        types.put(second.getId(), second);
        types.put(third.getId(), third);
    }

    /**
     * Метод сохраняет инцидент в хранилище.
     * @param accident Инцидент.
     */
    public void create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENT_ID.incrementAndGet());
        }
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
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

    /**
     * Метод возвращает список типов инцидента.
     * @return Список типов.
     */
    public Collection<AccidentType> loadTypes() {
        return types.values();
    }
}
