package mybootapp.test;

import mybootapp.model.Groupe;
import mybootapp.model.Person;
import mybootapp.repo.GroupRepository;
import mybootapp.repo.PersonRepository;
import mybootapp.web.Starter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class GroupeTest {

    @Autowired
    PersonRepository repoPerson;
    GroupRepository repoGroup;
    Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date(2010,05,7));
    Person p2 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(1995,02,17));
    Groupe g1 = new Groupe("groupe1");
    Groupe g2 = new Groupe("groupe2");

    @BeforeEach
    public void setUp() throws ParseException {
        // t'ajoute tes trucs ici
        repoGroup.save(g1);
    }

    //créer un groupe
    @Test
    public void create() {
        repoGroup.save(g1);
        repoGroup.save(g2);
        String name = g1.getName();
        assertNotNull(name, g1);;
    }

    //suprimmer un groupe
    @Test
    public void delete(){
        repoGroup.save(g1);
        long idTest = 100;
        g1.setId(idTest);
        System.out.println("l'id est " + g1.getId());
        repoGroup.delete(g1);
    }

    //retirer une personne
    public void removePerson(){

    }

    //retourner le contenu (liste de personne)
    @Test
    public void getAll(){
        repoGroup.save(g1);
        repoGroup.save(g2);
        repoPerson.save(p1);
        repoPerson.save(p2);
        p1.setGroup(g1);
        p2.setGroup(g1);

        int group_id = 1;
        g1.setId(group_id);

        var persons = repoPerson.findAll();
        Person p = persons.get(1);

        Assertions.assertNotNull(p);
        assertEquals(group_id, p.getGroupe().getId());
        assertEquals("groupe1", p.getGroupe().getName());
        assertEquals(1, p.getId());
        assertEquals("dia", p.getLastname());
        assertEquals("hamoydy", p.getFirstname());
        assertEquals(2, persons.size());
    }

}
