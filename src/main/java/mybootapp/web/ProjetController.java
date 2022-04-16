package mybootapp.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import mybootapp.model.Groupe;
import mybootapp.repo.GroupRepository;
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

    protected final Log logger = (Log) LogFactory.getLog(getClass());

    @Autowired
    GroupRepository groupRepository;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView printHome() {
        var groups = groupRepository.findAll();
        return new ModelAndView("home", "groups", groups);
    }

    @RequestMapping(value = "/addgroup", method = RequestMethod.GET)
    public ModelAndView addGroup(HttpSession session) {
        Groupe groupe = new Groupe("group"+groupRepository.findAll().size());
        groupRepository.save(groupe);
        var groups = groupRepository.findAll();
        return new ModelAndView("home", "groups", groups);
    }

    @RequestMapping(value = "/plus10", method = RequestMethod.GET)
    public ModelAndView plus10(
            @RequestParam(value = "num", defaultValue = "100") Integer value,
            @RequestParam("date") @DateTimeFormat(pattern="dd.MM.yyyy")Date date) {

        logger.info("Running plus10 controler with param = " + value);

        ModelAndView model = new ModelAndView("hello", "now", value + 10);
        model.addObject("date", date);
        return model;
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
