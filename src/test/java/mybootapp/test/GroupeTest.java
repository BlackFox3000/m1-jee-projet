package mybootapp.test;

import mybootapp.model.Groupe;
import mybootapp.model.Person;
import mybootapp.repo.PersonRepository;
import mybootapp.web.Starter;
import org.apache.catalina.Group;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.text.ParseException;

@SpringBootTest
@ContextConfiguration(classes = Starter.class)
public class GroupeTest {

    @Autowired
    PersonRepository repo;
    Person p1 = new Person("tintorri","foren","flo@mail.com","flo.com",new Date(2010,05,7));
    Person p2 = new Person("dia","hamoydy","dia@mail.com","dia.com",new Date(1995,02,17));

    @BeforeEach
    public void setUp() throws ParseException {
        // t'ajoute tes trucs ici
        repo.save(p1);
    }

    //créer un groupe
    public void create(){
        Groupe group = new Groupe("premier");
    }

    //suprimmer un groupe
    public void delete(){

    }

    //ajouter une personne
    public void addPerson(){

    }

    //retirer une personne
    public void removePerson(){

    }

    //retourner le contenu (liste de personne)
    public void getAll(){

    }

    //retourner une persone
    public void getPerson(){

    }
}
