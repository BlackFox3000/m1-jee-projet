package mybootapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "GroupTab")
public class Group implements Serializable {
	
	//Chaque personne est placée dans un groupe. Un groupe est composé de quelques dizaines de personnes (par exemple les étudiants du M1 IDL 2019/2020). 
	//Un groupe a donc un nom et un identifiant
    private static final long serialVersionUID = 1L;

    // properties
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 200,
            nullable = false)
    private String name;

    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
            fetch = FetchType.LAZY, mappedBy = "ownGroup")
    private Set<Person> persons;

    // constructors
    public Group() {
        super();
    }

    public Group(String name) {
        super();
        this.name = name;
    }

    // getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    // setters
    public void setId(Long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }
}
