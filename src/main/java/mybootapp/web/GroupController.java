package mybootapp.web;


import mybootapp.model.Groupe;
import mybootapp.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GroupController {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@RequestMapping( value="/groups", method=RequestMethod.GET )
	public String groups(
			Model model,
			@RequestParam(name="nom", defaultValue="") String nom,
			@RequestParam(name="page", defaultValue="1") int page,
			@RequestParam(name="taille", defaultValue="5") int taille) {
		
		page--;
		
		try {
			@SuppressWarnings("deprecation")
			Page<Groupe> pageGroups = groupRepository.find("%"+nom+"%",new QPageRequest(page, taille));
			model.addAttribute("att_groupName", nom);
			if(pageGroups.isEmpty()) {
				model.addAttribute("att_emptyList", "Le nom du groupe que vous avez entrer n'existe pas !");
			} else {
				model.addAttribute("att_groupList", pageGroups);
			}
			int[] pages = new int[pageGroups.getTotalPages()];
			model.addAttribute("att_pages", pages);
			model.addAttribute("att_taille", taille);
			model.addAttribute("att_page", page);
		} catch (Exception e) {
			model.addAttribute("att_exception", e.getMessage());
		}
		
		return "groups"; // ==> groups.jsp
	}

}