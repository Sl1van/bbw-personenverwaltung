package ch.bbw.personenverwaltung.service;

import ch.bbw.personenverwaltung.domain.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {
    @Inject
    private EntityManager entityManager;


    @Transactional
    public Person createPerson(Person entry) {
        entityManager.persist(entry);
        return entry;
    }

    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createQuery("FROM Person", Person.class);
        return query.getResultList();
    }

    public List<Person> findPerson(long id) {
        TypedQuery<Person> query = entityManager.createQuery("FROM Person where id= ?1", Person.class);
        return query.setParameter(1, id).getResultList();
    }

    @Transactional
    public Person deletePerson(long id) {
        Person entry = entityManager.find(Person.class, id);
        entityManager.remove(entry);

        return entry;
    }

    @Transactional
    public Person editPerson(long id, Person entry) {
        entityManager.merge(entry);
        return entry;
    }
}