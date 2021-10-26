package food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import food.dao.CategoryDAO;
import food.entity.Category;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value="index")
	public String index(ModelMap model) {
		List<Category> list = categoryDAO.listCategories();
		model.addAttribute("categories", list);
		
		return "category/index";
	}
}
