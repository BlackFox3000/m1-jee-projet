package mybootapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.io.Serializable;

@Entity(name = "Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    // properties
    @Id()
    @Pattern(regexp="[a-z]([0-9]){8}", message="{person.warn.id}")
    private String id;

    @NotNull
    @Size(min = 3, message = "{person.warn.name}")
    @Basic(optional = false)
    @Column(name = "name", length = 200,
            nullable = false)
    private String name;

    @NotNull
    @Size(min = 3, message = "{person.warn.firstname}")
    @Basic(optional = false)
    @Column(name = "first_name", length = 200,
            nullable = false)
    private String firstname;

    @NotNull
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @NotNull(message = "{person.warn.password.not.null}")
    @Size(min = 6, max = 10, message = "{person.warn.password.size}")
    @Basic(optional = false)
    @Column(name = "password", length = 10,
            nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ownGroup")
    private Group ownGroup;

    @Basic(optional = false)
    @Column(name = "webSite", length = 200)
	private String webSite;

    @Basic(optional = false)
    @Column(name = "mail", length = 50, nullable = false)
	private String mail;

    // constructors
    public Person() {
        super();
    }

    public Person( String id, String name, String firstname, Date birthday, String password, String mail, String webSite) {
        super();
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
        this.password = password;
        this.webSite = webSite;
        this.mail = mail;

    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public Date getBirthday(){
        return birthday;
    }

    public String getPassword(){
        return password;
    }

    public Group getGroup(){
        return ownGroup;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    //a voir
    public void setGroup(Group ownGroup){
        this.ownGroup = ownGroup;
    }
   
    public void setWebSite(String webSite){
        this.webSite = webSite;
    }
    public void setMail(String mail){
        this.mail = mail;
    }

    
    
    


}
