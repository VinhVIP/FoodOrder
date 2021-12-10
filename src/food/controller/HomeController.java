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
import food.utils.Constants;

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

		Account user = (Account) session.getAttribute("account");
		int userId = user == null ? -1 : user.getAccountId();

		List<Food> newFoods = foodDAO.listFoods("", -1, Constants.FILTER_BY_NEWEST, userId == 1);
		List<Food> popularFoods = foodDAO.listFoods("", -1, Constants.FILTER_BY_POPULAR, userId == 1);
		List<Food> highRatingFoods = foodDAO.listFoods("", -1, Constants.FILTER_BY_RATING, userId == 1);
		
		model.addAttribute("newFoods", newFoods.subList(0, Math.min(3, newFoods.size())));
		model.addAttribute("mostBuyFoods", popularFoods.subList(0, Math.min(3, popularFoods.size())));
		model.addAttribute("highRatingFoods", highRatingFoods.subList(0, Math.min(3, highRatingFoods.size())));
		
		return "home";
	}

	@RequestMapping("login")
	public String login(HttpSession session) {
		// Fake login
		// Login vào account có id = ?
		Account account = accountDAO.getAccount(1);
		System.out.println(account.getName() + " " + account.getAccountId());
		session.setAttribute("account", account);

		return "redirect:/home.htm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("account");
		return "redirect:/home.htm";
	}
	
	@RequestMapping("map")
	public String map() {
		System.out.println("MAP");
		return "map";
	}

}
