package mybootapp.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Data
//Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=17028432, allocationSize=100)
@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Person implements Serializable{

	// Attributes :
	private static final long serialVersionUID = 3708085921156953388L;
	@Id
	// Use the sequence that is defined above:
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int id;

	@NotNull(message = "Le lastname est obligatoire!")
	private String lastname;

	@NotNull(message = "Le prénom est obligatoire!")
	private String firstname;

	@NotNull(message = "L'email est obligatoire!")
	private String 	email;

	@NotNull(message = "Le siteweb est obligatoire!")
	private String website;

	@NotNull(message = "La date de naissance est obligatoire!")
	private Date birthdate;
	@ManyToOne
	@JoinColumn(name="id_groupe")
	private Groupe groupe;

	// Constructors :
	public Person() { super(); }

	public Person(String lastname, String firstname, String email, String website, Date dtn) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.website = website;
		this.birthdate = dtn;
	}

	public Person(String lastname, String firstname, String email, String website, Date birthdate, Groupe groupe) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.website = website;
		this.birthdate =birthdate;
		this.groupe = groupe;
	}

	// Getters & Setters :
	public static long getSerialversionuid() { return serialVersionUID; }

	public long getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getLastname() { return lastname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public String getFirstname() { return firstname; }

	public void setFirstname(String firstname) { this.firstname = firstname; }

	public String getEmail() { return email; }

	public void setEmail(String mail) { this.email = mail; }

	public String getWebsite() { return website; }

	public void setWebsite(String website) { this.website = website; }

	public Date getBirthdate() { return birthdate; }

	public void setBirthdate(Date birthdate) { this.birthdate = this.birthdate; }

	public Groupe getGroup() { return groupe; }

	public void setGroup(Groupe groupe) { this.groupe = groupe; }

}