package mybootapp.test;


import mybootapp.model.Person;
import mybootapp.repo.PersonRepository;
import mybootapp.web.Starter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class PersonTest {

    @Autowired
    PersonRepository repo;
    Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date("10/02/1997"));

    @BeforeEach
    public void setUp() throws ParseException {
        // t'ajoute tes trucs ici
        repo.save(p1);
    }

    @AfterEach
    public void tearDown() {
        // tu netoie là
    }


    @Test
    public void testCreate() {
        System.out.println("ddn"+p1.getBirthdate());
        repo.save(p1);
        System.out.println("name: " + p1.getFirstname());
    }

    @Test
    public void printPersonnes(){
        var persons = repo.findAll();
        for (Person person:persons) {
            System.out.println(person.getFirstname());
        }
        System.out.println("yolo");
    }

}
