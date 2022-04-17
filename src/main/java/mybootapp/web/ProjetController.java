package mybootapp.web;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import mybootapp.model.Groupe;
import mybootapp.model.Person;
import mybootapp.repo.GroupRepository;
import mybootapp.repo.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller()
@RequestMapping("/projet")
public class ProjetController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PersonRepository personRepository;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView printHome() {
        var groups = groupRepository.findAll();
        var persons = personRepository.findAll();
        ModelAndView model =  new ModelAndView("home", "groups", groups);
        model.addObject("persons",persons);
        return model;
    }

    @RequestMapping(value = "/addgroup", method = RequestMethod.GET)
    public ModelAndView addGroup(HttpSession session) {
        Groupe groupe = new Groupe("group"+(groupRepository.findAll().size()+1));
        groupRepository.save(groupe);
        var groups = groupRepository.findAll();
        var persons = personRepository.findAll();
        ModelAndView model =  new ModelAndView("home", "groups", groups);
        model.addObject("persons",persons);
        return model;
    }

    @RequestMapping(value = "/addperson", method = RequestMethod.GET)
    public ModelAndView addPerson(HttpSession session) {
        String firstname = "firstname"+personRepository.findAll().size();
        String lastname = "lastname"+personRepository.findAll().size();
        Person person = new Person(lastname,firstname,firstname+"."+lastname+"@mail.com",firstname+"."+lastname+".com", new Date(System.currentTimeMillis()));
        personRepository.save(person);
        var groups = groupRepository.findAll();
        var persons = personRepository.findAll();
        ModelAndView model =  new ModelAndView("home", "groups", groups);
        model.addObject("persons",persons);
        return model;
    }

    @RequestMapping(value = "/createPerson", method = RequestMethod.GET)
    public ModelAndView createPerson() {
        return new ModelAndView("personForm");
    }

    @RequestMapping(value = "/createGroup", method = RequestMethod.GET)
    public ModelAndView createGroup() {
        return new ModelAndView("groupForm");
    }


    @RequestMapping(value = "/groupDetail/{param}", method = RequestMethod.GET)
    public ModelAndView groupDetail(   @PathVariable("param") Integer id) {
        Groupe group = groupRepository.getById(id.longValue());
        return new ModelAndView("groupDetail", "persons", group.getPersons());
    }

    @RequestMapping(value = "/personDetail/{param}", method = RequestMethod.GET)
    public ModelAndView personDetail(   @PathVariable("param") Integer id) {
        //Ajouter son groupe
        var person = personRepository.getById(id);
        return new ModelAndView("personDetail", "person", person);
    }

    @RequestMapping(value = "/personForm/{param}", method = RequestMethod.GET)
    public ModelAndView personForm(   @PathVariable("param") Integer id) {
        //Ajouter son groupe
        var person = personRepository.getById(id);
        ModelAndView modelAndView = new ModelAndView("personForm", "person", person);
        var groups = groupRepository.findAll();
        System.out.println("Groupe " + groups.size());
        System.out.println("person"+person.getFirstname());
        modelAndView.addObject("groups",groups);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView saveProduct(@ModelAttribute @Valid Person p, BindingResult result) {
        validator.validate(p, result);
        System.out.println("testtttt");
        personRepository.save(p);
        var groups = groupRepository.findAll();
        var persons = personRepository.findAll();
        ModelAndView model =  new ModelAndView("home", "groups", groups);
        model.addObject("persons",persons);
        return model;
    }

    @Autowired
    PersonValidator validator;

    @ModelAttribute
    public Person newOrEditPerson(
            @RequestParam(value = "id", required = false) String idPerson)
    {
        if (idPerson != null) {
            var p = personRepository.findById(Integer.parseInt(idPerson));
            return p.get();
        }
        return null;
    }


}
