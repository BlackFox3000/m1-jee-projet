package mybootapp.dao;

import mybootapp.model.Group;
import mybootapp.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Repository("Dao")
@Transactional
public class Dao implements IDao {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    // start service
    @PostConstruct
    public void start() {
        System.out.println("Start " + this);
    }

    // stop service
    @PreDestroy
    public void stop() {
        System.out.println("Stop " + this);
    }

    @Override
    public <T> Collection<T> findAll(Class<T> myClass) {
        // Récupère une instance de la classe CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Definition d'un nouveau type de requete
        CriteriaQuery<T> cq = cb.createQuery(myClass);
        // clause FROM de la requete
        Root<T> root = cq.from(myClass);
        // clause SELECT de la requete
        cq.select(root);

        // Preparation de la requete pour execution
        TypedQuery<T> q = em.createQuery(cq);
        // Execution de la requete
        List<T> resultList = q.getResultList();

        if (resultList.isEmpty())
            System.err.println("Entities from " + myClass.getName() + " not found.");
        else
            System.err.println("Entities from " + myClass.getName() + " found.");

        return resultList;
    }

    // récupérer les personnes d'un groupe
    @Override
    public Collection<Person> findAllPersonsInGroup(long idGroup) {
        // Récupère une instance de la classe CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Definition d'un nouveau type de requete
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        // clause FROM de la requete
        Root<Person> root = cq.from(Person.class);

        // Recuperation du metamodel
        Metamodel m = em.getMetamodel();
        // Recuperation de l'attribut id du groupe
        EntityType<Person> Person_ = m.entity(Person.class);
        SingularAttribute<Person, Group> groupMeta = Person_.getDeclaredSingularAttribute("ownGroup", Group.class);

        // navigation vers l'entite groupe
        Join<Person, Group> group = root.join(groupMeta);

        // Recuperation de l'attribut id du groupe
        EntityType<Group> Group_ = m.entity(Group.class);
        SingularAttribute<Group, Long> idGroupMeta = Group_.getDeclaredSingularAttribute("id", Long.class);

        // clause WHERE de la requete
        cq.where(cb.equal(group.get(idGroupMeta), idGroup));

        // Preparation de la requete pour execution
        TypedQuery<Person> q = em.createQuery(cq);
        // Execution de la requete
        List<Person> resultList = q.getResultList();

        if (resultList.isEmpty())
            System.err.println("No persons found.");
        else
            System.err.println(resultList.size()+" persons found.");

        return resultList;
    }

    @Override
    public <T> T find(Class<T> myClass, Object id) {
        T entity = em.find(myClass, id);
        if (entity == null)
            System.err.println("Entity not found.");
        else
            System.err.println("Entity found.");

        return entity;
    }

    /* correspond a la methode save */
    public <T> T add(T entity) {
        em.persist(entity);
        System.err.println("Entity added.");
        return (entity);
    }

    /* correspond a la methode save */
    public <T> T update(T entity) {
        entity = em.merge(entity);
        System.err.println("Entity updated.");
        return entity;
    }

    @Override
    public <T> Collection<T> findByStringProperty(Class<T> myClass, String propertyName, String propertyValue) {
        // Récupère une instance de la classe CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();
        // Definition d'un nouveau type de requete
        CriteriaQuery<T> cq = cb.createQuery(myClass);
        // clause FROM de la requete
        Root<T> root = cq.from(myClass);

        // Recuperation du metamodel
        Metamodel m = em.getMetamodel();
        // Recuperation de l'attribut id du groupe
        EntityType<T> Person_ = m.entity(myClass);
        SingularAttribute<T, String> propertyValueMeta = Person_.getDeclaredSingularAttribute(propertyName,
                String.class);

        // clause WHERE de la requete
        cq.where(cb.like(root.get(propertyValueMeta), propertyValue));

        // Preparation de la requete pour execution
        TypedQuery<T> q = em.createQuery(cq);
        // Execution de la requete
        List<T> resultList = q.getResultList();

        if (resultList.isEmpty())
            System.err.println("No " + myClass.getName() + " found.");
        else
            System.err.println(resultList.size() + " " + myClass.getName() + " found.");

        return resultList;
    }
}
