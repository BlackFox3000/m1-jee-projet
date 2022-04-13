package mybootapp.dao;

import java.util.Collection;

import mybootapp.model.Person;

public interface IDao {

    // récupérer toutes les entites d'une classe
    public <T> Collection<T> findAll(Class<T> myClass);

    // récupérer les entites de la classe myClass d'un groupe
    public Collection<Person>  findAllPersonsInGroup(long idGroup);

    // lire une instance de la classe myClass et d'identifiant id
    public <T> T find(Class<T> myClass, Object id);

    // ajout d'une entite
    public <T> T add(T entity);

    // mise à jour d'une entite
    public <T> T update(T entity);

    public <T> Collection<T> findByStringProperty(Class<T> myClass, String propertyName, String propertyValue);
}