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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class GroupeTest {

    @Autowired
    PersonRepository repoPerson;
    @Autowired
    GroupRepository repoGroup;

    Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date(2010,05,7));
    Person p2 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(1995,02,17));
    Groupe g1 = new Groupe("groupe1");
    Groupe g2 = new Groupe("groupe2");

    @BeforeEach
    public void setUp() throws ParseException {
        // t'ajoute tes trucs ici
        repoGroup.save(g1);
        System.out.println("save repo");
    }

    //créer un groupe
    @Test
    public void create() {
        repoGroup.save(g2);
        String name = g1.getName();
        assertNotNull(name, g1);;
    }

    //suprimmer un groupe
    @Test
    public void delete(){
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
        Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date(2010,05,7));
        Person p2 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(1995,02,17));
        Groupe g1 = new Groupe("groupe1");

        g1.addPerson(p1);
        g1.addPerson(p2);

        repoPerson.save(p1);
        repoPerson.save(p2);



        var persons = repoPerson.findAll();
        var personsGroup = g1.getPersons();

        assertEquals(persons,personsGroup);
    }

}
