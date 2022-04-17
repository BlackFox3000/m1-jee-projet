package mybootapp.test;

import mybootapp.model.Person;
import mybootapp.repo.PersonRepository;
import mybootapp.web.Starter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class PersonTest {

    @Autowired
    PersonRepository repo;
    Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date(2010,05,7));
    Person p2 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(1995,02,17));


    @BeforeEach
    public void setUp() throws ParseException {
        repo.save(p1);
    }

//    @AfterEach
//    public void tearDown() {
//        // tu netoie là
//    }


    @Test
    public void testCreate() {
        Person p3 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(2010,8,5));
        repo.save(p3);
        assertEquals(repo.findAll().get(3),p3);
    }

    @Test
    public void getAll(){
        var persons = repo.findAll();
        var result = new ArrayList<Person>();
        result.add(p1);
        result.add(p2);
        for (int i = 0 ; i<persons.size(); i++) {
            assertEquals(persons.get(i),result.get(i));
        }
    }

    @Test
    public void edit(){
        p1.setBirthdate(new Date(2001,10,9));
        p1.setEmail("magic@mail.fr");
        p1.setFirstname("Paul");
        p1.setLastname("Begue");

        assertEquals("3910-06-07", p1.getBirthdate().toString());
        assertEquals("magic@mail.fr",p1.getEmail());
        assertEquals("Paul",p1.getFirstname());
        assertEquals("Begue",p1.getLastname());

    }



}
