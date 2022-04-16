package mybootapp.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Password {
  
    @SuppressWarnings("unused")
	private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    @SuppressWarnings("unused")
	private String token;
  
    @OneToOne(targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "person_id")
    private Person person;
  
    @SuppressWarnings("unused")
	private Date expiryDate;
}
