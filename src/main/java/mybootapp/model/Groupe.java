package mybootapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
public class Groupe implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@NotNull(message = "Le nom est obligatoire!")
	private String 	name;

	@OneToMany(mappedBy= "groupe", fetch=FetchType.LAZY)
	private Collection<Person> persons;
	
	public Groupe() {
		super();
	}
	
	public Groupe(String name) {
		super();
		this.name = name;
		this.persons = new ArrayList<>();
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person){
		persons.add(person);
	}
	
}