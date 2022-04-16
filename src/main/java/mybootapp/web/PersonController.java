package mybootapp.web;

import mybootapp.model.Groupe;
import mybootapp.model.Person;
import mybootapp.repo.GroupRepository;
import mybootapp.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@RequestMapping( value="/personnes", method=RequestMethod.GET )
	public String persons(
			Model model,
			@RequestParam(name="idGroup") long idG,
			@RequestParam(name="nomPersonne", defaultValue="") String np,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t) {
		
		p = 0;
		
		Groupe groupe = groupRepository.findById(idG).get();
		try {
			Page<Person> pagePersons = PersonRepository.findByIdGroupe(idG,new QPageRequest(p, t));
			model.addAttribute("att_nomPersonne", np);
			if(pagePersons.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom de la personne que vous avez entrer n'existe pas dans le groupe <b>" + groupe.getName() + "</b> !");
			} else {
				model.addAttribute("att_personneList", pagePersons);
			}
			
			model.addAttribute("att_nomGroupe", groupe.getName());
			int[] pages = new int[pagePersons.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", t);
			model.addAttribute("att_page", p);
			model.addAttribute("att_idGroupe", idG);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "personnes"; // ==> persons.jsp
	}
	
	@RequestMapping( value="/chercherPersonne", method=RequestMethod.GET )
	public String chercherPersonne(
			Model model,
			@RequestParam(name="idGroupe") long idG,
			@RequestParam(name="nomPersonne", defaultValue="") String np,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t) {
		
		Groupe groupe = groupRepository.findById(idG).get();
		try {
			@SuppressWarnings("deprecation")
			Page<Person> pagePersons = PersonRepository.findByIdGroupeAndPersonLastname(idG,"%"+np+"%",new QPageRequest(p, t));
			model.addAttribute("att_nomPersonne", np);
			if(pagePersons.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom de la personne que vous avez entrer n'existe pas dans le groupe \"" + groupe.getName() + "\" !");
			} else {
				model.addAttribute("att_personneList", pagePersons);
			}
			model.addAttribute("att_nomGroupe", groupe.getName());
			int[] pages = new int[pagePersons.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", t);
			model.addAttribute("att_page", p);
			model.addAttribute("att_idGroupe", idG);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "persons"; 
	}
	
	@RequestMapping( value="/affichePersonne", method=RequestMethod.GET )
	public String affichePersonne(
			Model model,
			@RequestParam(name="idPersonne") long idP,
			@RequestParam(name="page", defaultValue="0") int p,
			@RequestParam(name="taille", defaultValue="5") int t,
			HttpSession session) {
		
		Person sessionPerson = (Person) session.getAttribute("MY_SESSION_PERSONNE");
		String sessionIdPerson;
		if (sessionPerson == null) { sessionIdPerson = ""; }
		else { sessionIdPerson = String.valueOf(sessionPerson.getId()); }
		model.addAttribute("att_sessionIdPerson", sessionIdPerson);
		
		Person person = personRepository.findById((int) idP).get();
		
		if(person == null) { return "error"; }
		
		model.addAttribute("att_personne", person);
		model.addAttribute("att_taille", t);
		model.addAttribute("att_page", p);
		
		return "personne"; 
	}
	
	@RequestMapping( value="/editPersonne", method=RequestMethod.GET )
	public String modifierPerson(
			Model model,
			@ModelAttribute Person personParam,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="taille", defaultValue="5") int taille,
			HttpSession session) {
		
		page--;
		
		model.addAttribute("att_sessionIdPersonne", personParam.getId());
		
		@SuppressWarnings("deprecation")
		Person person = personRepository.getOne((int) personParam.getId());
		
		if( person == null ) {
			return "error"; 
		}
		
		person.setLastname(personParam.getLastname());
		person.setFirstname(personParam.getFirstname());
		person.setEmail(personParam.getEmail());
		person.setWebsite(personParam.getWebsite());
		person.setBirthdate(personParam.getBirthdate());
		
		personRepository.save(person);
		
		model.addAttribute("att_personne", person);
		model.addAttribute("att_taille", taille);
		model.addAttribute("att_page", page);
		
		return "personne"; 

	}
	
}
