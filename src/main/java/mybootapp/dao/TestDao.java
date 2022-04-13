package mybootapp.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.runner.RunWith;


import mybootapp.model.Group;
import mybootapp.model.Person;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class TestDao {

    @Autowired
    IDao dao;

    static Date date1, date2, date3;
    static Person person1, person2, person3;
    static Group group1, group2, group3, group4, group5;

    @SuppressWarnings("deprecation")
	@BeforeClass
    public static void beforeAll() {
        group1 = new Group ("L1");
        group2 = new Group ("L2");
        group3 = new Group ("L3");
        group4 = new Group ("M1");
        group5 = new Group ("M2");

        date1 = new Date(1999, 12, 12);
        date2 = new Date(2001,8,9);
        date3 = new Date(2012, 7,12);

        person1 = new Person("d18032358", "DIA", "Hamoydy",
                date1, "monMotDePasse","mon@mail.com","google.com");

        person2 = new Person("charlotte.meier", "MEIER", "Charlotte",
                date2, "mdpcharlotte", "charl@orange.com", "");

        person3 = new Person("", "DIA", "Mamadou",
                date3, "mdpmamadou", "dia@hotmail.fr", "dia.com");

        person1.setGroup(group1);
        person2.setGroup(group2);
        person3.setGroup(group5);
    }

    @AfterClass
    public static void afterAll() {

    }


    @Test
    public void testAddandFind () {
        dao.add(group1);
        dao.add(person1);

        String expected = person1.getId();
        Person result = dao.find(Person.class, expected);
        String value =  result.getId();
        assertEquals (expected, value);
    }

    @Test
    public void testUpdate () {
        dao.add(group2);
        dao.add(person2);
        String expected = "John";
        person2.setFirstname(expected);
        dao.update(person2);
        Person result = dao.find(Person.class, person2.getId());
        String value = result.getFirstname();
        assertTrue (value.equals(expected));
    }

    // retourne true si un groupe appartient a une collection de groupes, sinon false
    private boolean groupIsInside (Group groupToFind, Collection<Group> list) {
        long id = groupToFind.getId();
        for (Group group : list) {
            if (group.getId() == id)
                return true;
        }
        return false;
    }

    @Test
    public void testFindAll () {
        dao.add(group3);
        dao.add(group4);

        Collection<Group> resultList = dao.findAll(Group.class);

        assertTrue(groupIsInside(group3, resultList));
        assertTrue(groupIsInside(group4, resultList));
    }

    // retourne true si une personne appartient a une collection de personnes, sinon false
    private boolean personIsInside (Person personToFind, Collection<Person> list) {
        String id = personToFind.getId();
        for (Person person : list) {
            if (person.getId().equals(id))
                return true;
        }
        return false;
    }

    @Test
    public void testFindAllPersonsInGroup () {
        dao.add(group5);
        dao.add(person3);

        Collection<Person> resultList = dao.findAllPersonsInGroup(group5.getId());

        assertTrue(resultList.size() == 1);
        assertTrue(personIsInside(person3, resultList));
    }
}
