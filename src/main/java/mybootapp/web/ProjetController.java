package mybootapp.web;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import mybootapp.model.Groupe;
import mybootapp.model.Person;
import mybootapp.repo.GroupRepository;
import mybootapp.repo.PersonRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value = "/detail/{param}", method = RequestMethod.GET)
    public ModelAndView detail(   @PathVariable("param") Integer id) {
        Groupe group = groupRepository.getById(id.longValue());
        return new ModelAndView("groupDetail", "persons", group.getPersons());
    }

    @RequestMapping(value = "/voir/{param}", method = RequestMethod.GET)
    public ModelAndView voir(@PathVariable("param") Integer param) {
        logger.info("Running param controler with param=" + param);
        return new ModelAndView("hello", "now", param);
    }

    @RequestMapping(value = "/matrix/{param}", method = RequestMethod.GET)
    @ResponseBody
    public String testMatrix(//
                             @PathVariable("param") String param, //
                             @MatrixVariable( defaultValue = "A") String a, //
                             @MatrixVariable(name = "b", defaultValue = "1") Integer b//
    ) {
        return String.format("param=%s, a=%s, b=%d", param, a, b);
    }

    @RequestMapping(value = "/matrix1/{param}", method = RequestMethod.GET)
    @ResponseBody
    public String testMatrix1(//
                              @PathVariable("param") String param, //
                              @MatrixVariable( defaultValue = "A") Map<String,String> map){//

        return String.format("param=%s", map);

    }

}
