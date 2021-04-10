package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Comparator;
import java.util.List;

/**
 * Class AccidentHibernate
 * Класс осуществляет взаимодействие с базой данных с помощью Hibernate.
 * @author Dmitry Razumov
 * @version 1
 */
@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            session.getTransaction().commit();
            return accident;
        }
    }

    public List<Accident> findAllAccidents() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List accidents = session.createQuery("select distinct a from Accident a "
                    + "left join fetch a.rules").list();
            session.getTransaction().commit();
            return accidents;
        }
    }

    public List<AccidentType> findAllTypes() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List types = session.createQuery("from AccidentType ").list();
            session.getTransaction().commit();
            return types;
        }
    }

    public List<Rule> findAllRules() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            List rules = session.createQuery("from Rule ").list();
            session.getTransaction().commit();
            rules.sort(Comparator.comparingInt(Rule::getId));
            return rules;
        }
    }

    public Accident findAccidentById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Accident accident = (Accident) session.createQuery("select distinct a from Accident a "
                    + "left join fetch a.rules where a.id = :id")
                    .setParameter("id", id).uniqueResult();
            session.getTransaction().commit();
            return accident;
        }
    }

    public AccidentType findTypeById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            AccidentType type = session.get(AccidentType.class, id);
            session.getTransaction().commit();
            return type;
        }
    }

    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Rule rule = session.get(Rule.class, id);
            session.getTransaction().commit();
            return rule;
        }
    }
}
