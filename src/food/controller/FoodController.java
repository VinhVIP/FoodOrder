package food.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import food.dao.CartDAO;
import food.dao.CategoryDAO;
import food.dao.FoodDAO;
import food.dao.OrderDAO;
import food.dao.RatedDAO;
import food.entity.Account;
import food.entity.Cart;
import food.entity.CartKey;
import food.entity.Category;
import food.entity.Food;
import food.entity.Rated;
import food.entity.RatedKey;
import food.utils.Constants;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodDAO foodDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private RatedDAO ratedDAO;
	
	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private OrderDAO orderDAO;

	@RequestMapping(value = "index", params = { "id" })
	public String food(ModelMap model, HttpSession session,  @RequestParam(value = "id") int id) {
		Food food = foodDAO.getFood(id);

		if (food == null) {
			return "not_found";
		}

		List<Category> list = categoryDAO.listCategories();
		int[] countStar = new int[6];
		for (int i = 1; i < 6; i++) {
			countStar[i] = 0;
		}

		double avgStar = 0;
		for (Rated rated : food.getRateds()) {
			avgStar += rated.getStar();
			countStar[rated.getStar()]++;
		}
		if (food.getRateds().size() > 0)
			avgStar /= food.getRateds().size();
		
		List<Food> listFoodSameCategory = foodDAO.listFoodsInCategory(food.getCategory().getCategoryId());

		
		model.addAttribute("categories", list);
		model.addAttribute("category", food.getCategory());
		model.addAttribute("food", food);
		model.addAttribute("avgStar", avgStar);
		model.addAttribute("avgStarString", String.format("%.1f", avgStar));
		model.addAttribute("countStar", countStar);
		model.addAttribute("listFoodSameCategory", listFoodSameCategory);

		Account user = (Account) session.getAttribute("account");
		if(user != null) {
			// Kiểm tra xem user đã từng đặt món ăn này chưa? nếu đặt rồi mới cho đánh giá
			boolean hasOrdered = orderDAO.hasOrdered(user.getAccountId(), id);	
			
			// Kiêm tra món ăn đã có trong giỏ hay chưa
			Cart cart = cartDAO.get(user.getAccountId(), id);
			
			model.addAttribute("hasOrdered", hasOrdered);
			model.addAttribute("addedToCart", cart != null);
			
			if(hasOrdered) {
				Rated rated = ratedDAO.getRated(user.getAccountId(), id);
				
				if(rated == null) {
					rated = new Rated();
				}

				model.addAttribute("userRating", rated);
			}		
		}

		return "food/food";
	}

	@RequestMapping(value = "index", params = { "id_category" })
	public String index(ModelMap model, @RequestParam(value = "id_category") int categoryId) {
		return "redirect:/food/index.htm?id_category=" + categoryId + "&page=1";
	}

	@RequestMapping(value = "index", params = { "id_category", "page" })
	public String index(ModelMap model, @RequestParam(value = "id_category") int categoryId,
			@RequestParam(value = "page") int page) {

		if (page <= 0)
			return "redirect:/food/index.htm?id_category=" + categoryId + "&page=1";

		Category category = categoryDAO.getCategory(categoryId);
		int maxPage = Constants.getMaxPage(category.getFoods().size(), Constants.FPP);
		if (page > maxPage)
			return "redirect:/food/index.htm?id_category=" + categoryId + "&page=" + maxPage;

		List<Food> foods = foodDAO.listFoodsInCategoryByPage(categoryId, page);

		model.addAttribute("category", category);
		model.addAttribute("foods", foods);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", maxPage);

		List<Category> categories = categoryDAO.listCategories();
		model.addAttribute("categories", categories);

		return "food/index";
	}

	@RequestMapping(value = "rateds", params = { "id_food" })
	public String rating(ModelMap model, @RequestParam(value = "id_food") int foodId) {
		Food food = foodDAO.getFood(foodId);
		if (food == null) {
			return "not_found";
		}

		List<Category> categories = categoryDAO.listCategories();

		model.addAttribute("categories", categories);
		model.addAttribute("food", food);

		return "food/rateds";
	}

	@RequestMapping(value = "rating", method = RequestMethod.POST, params = { "id" })
	public String rating(ModelMap model, HttpSession session, @ModelAttribute("userRating") Rated rating,
			@RequestParam("id") int id) {

		Account user = (Account) session.getAttribute("account");
		if (user == null) {
			System.out.println("user null rooi");
		}else {
			Rated rated = ratedDAO.getRated(user.getAccountId(), id);
			
			if(rated == null) {
				// insert
				rated = rating;
				rated.setRatedId(new RatedKey(user.getAccountId(), id));
				boolean added = ratedDAO.insert(rated);
				System.out.println(added ? "OK" : "Fail");
			}else {
				//update
				rated.setStar(rating.getStar());
				rated.setComment(rating.getComment());
				rated.setCmtTime(new Date());
				
				boolean updated = ratedDAO.update(rated);
				
				System.out.println(updated ? "OK update" : "Fail update");
			}
		}

		return "redirect:/food/index.htm?id=" + id;
	}
	
	@RequestMapping(value="rating", params= {"deleteId"})
	public String delete(ModelMap model, HttpSession session, @RequestParam("deleteId") int foodId) {
		
		Account user = (Account) session.getAttribute("account");
		if (user == null) {
			System.out.println("user null rooi");
		}else {
			Rated rated = ratedDAO.getRated(user.getAccountId(), foodId);
			
			if(rated != null) {
				// delete
				boolean deleted = ratedDAO.delete(rated);
				System.out.println(deleted ? "OK Delete" : "Fail delete");
				
			}
		}
		
		return "redirect:/food/index.htm?id=" + foodId;
	}
	
	@RequestMapping(value="cart", params= {"id_food"})
	public String addToCart(ModelMap model, HttpSession session, @RequestParam("id_food") int foodId) {
		Account user = (Account) session.getAttribute("account");
		if (user == null) {
			System.out.println("user null rooi");
		}else {
			Cart cart = new Cart();
			cart.setCartId(new CartKey(user.getAccountId(), foodId));
			cart.setQuantity(1);
			
			boolean added = cartDAO.insert(cart);
			System.out.println(added ? "OK Add" : "Fail Add");
		
		}
		return "redirect:/food/index.htm?id=" + foodId;
	}

}
