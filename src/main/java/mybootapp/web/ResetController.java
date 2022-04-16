package mybootapp.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetController {
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword() {
		System.out.println("/forgotPassword");
		return "forgotPassword"; // ==> "forgotPassword.jsp" 
	}
	
}