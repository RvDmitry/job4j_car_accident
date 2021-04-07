package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class AccidentJdbcTemplate
 * Класс осуществляет взаимодействие с базой данных.
 * @author Dmitry Razumov
 * @version 1
 */
@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;

    private final RowMapper<Accident> accidentRowMapper = (rs, row) -> {
        Accident accident = new Accident();
        accident.setId(rs.getInt(1));
        accident.setName(rs.getString(2));
        accident.setText(rs.getString(3));
        accident.setAddress(rs.getString(4));
        AccidentType type = new AccidentType();
        type.setId(rs.getInt(5));
        type.setName(rs.getString(6));
        accident.setType(type);
        return accident;
    };

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Метод сохраняет инцидент в БД.
     * @param accident Инцидент.
     * @return Сохраненный инцидент.
     */
    public Accident save(Accident accident) {
        if (accident.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatementCreator psc = cn -> {
                PreparedStatement ps = cn.prepareStatement(
                        "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, accident.getName());
                ps.setString(2, accident.getText());
                ps.setString(3, accident.getAddress());
                ps.setInt(4, accident.getType().getId());
                return ps;
            };
            jdbc.update(psc, keyHolder);
            int id;
            if (keyHolder.getKeys().size() > 1) {
                id = (int) keyHolder.getKeys().get("id");
            } else {
                id = keyHolder.getKey().intValue();
            }
            accident.setId(id);
        } else {
            jdbc.update("update accidents "
                            + "set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType().getId(),
                    accident.getId());
            jdbc.update("delete from accidents_rules where accident_id = ?", accident.getId());
        }
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_rules (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(), rule.getId());
        }
        return accident;
    }

    /**
     * Метод возвращает все инциденты из БД.
     * @return Список инцидентов.
     */
    public List<Accident> findAllAccidents() {
        List<Accident> accidents = jdbc.query(
                "select a.id, a.name, a.text, a.address, t.id, t.name "
                        + "from accidents a, types t where a.type_id = t.id",
                accidentRowMapper
        );
        accidents.sort(Comparator.comparingInt(Accident::getId));
        for (Accident accident : accidents) {
            accident.setRules(findAccidentRules(accident));
        }
        return accidents;
    }

    /**
     * Метод возвращает список типов инцидента.
     * @return Список типов.
     */
    public List<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    /**
     * Метод возвращает список статей.
     * @return Список статей.
     */
    public List<Rule> findAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    /**
     * Метод возвращает инцидент по его идентификатору.
     * @param id Идентификатор.
     * @return Инцидент.
     */
    public Accident findAccidentById(int id) {
        Accident accident = jdbc.queryForObject(
                "select a.id, a.name, a.text, a.address, t.id, t.name "
                        + "from accidents a, types t where a.type_id = t.id and a.id = ?",
                accidentRowMapper,
                id
        );
        accident.setRules(findAccidentRules(accident));
        return accident;
    }

    /**
     * Метод возвращает тип инцидента по его идентификатору.
     * @param id Идентификатор.
     * @return Тип.
     */
    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject(
                "select id, name from types where id = ?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                },
                id);
    }

    /**
     * Метод возвращает статью по ее идентификатору.
     * @param id Идентификатор.
     * @return Статья.
     */
    public Rule findRuleById(int id) {
        return jdbc.queryForObject(
                "select id, name from rules where id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    /**
     * Метод возвращает набор статей, связанных с заданным инцидентом.
     * @param accident Инцидент.
     * @return Набор статей.
     */
    private Set<Rule> findAccidentRules(Accident accident) {
        List<Rule> rules = jdbc.query(
                "select r.id, r.name from accidents_rules ar, rules r "
                        + "where ar.rule_id = r.id and ar.accident_id = ?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt(1));
                    rule.setName(rs.getString(2));
                    return rule;
                },
                accident.getId());
        return new HashSet<>(rules);
    }
}
