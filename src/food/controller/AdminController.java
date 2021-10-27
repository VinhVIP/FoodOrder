package food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import food.dao.CategoryDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "", "index" })
	public String index(ModelMap model) {

		return "admin/index";
	}
	
}
