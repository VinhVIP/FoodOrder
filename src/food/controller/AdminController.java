package food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value = { "", "index" })
	public String index(ModelMap model) {

		return "admin/index";
	}
	
}
