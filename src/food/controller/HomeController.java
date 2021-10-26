package food.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import food.dao.AccountDAO;
import food.dao.CategoryDAO;
import food.dao.FoodDAO;
import food.entity.Account;
import food.entity.Category;
import food.entity.Food;

@Controller
public class HomeController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private FoodDAO foodDAO;
	
	@Autowired
	private AccountDAO accountDAO;

	@RequestMapping(value = { "index", "home", "" })
	public String index(ModelMap model, HttpSession session) {
		List<Category> list = categoryDAO.listCategories();
		model.addAttribute("categories", list);
		
		List<Food> newFoods = foodDAO.listNewestFood(1);
		model.addAttribute("newFoods", newFoods);

		return "home";
	}
	
	@RequestMapping("login")
	public String login(HttpSession session) {
		// Fake login
		// Login vào account có id = ?
		Account account = accountDAO.getAccount(4);
		System.out.println(account.getName());
		session.setAttribute("account", account);
		
		return "redirect:/home.htm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/home.htm";
	}

}
