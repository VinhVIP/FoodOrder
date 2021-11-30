package food.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import food.dao.AccountDAO;
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

	@Autowired
	private AccountDAO accountDAO;

	@RequestMapping("/{id}")
	public String food(ModelMap model, HttpSession session, @PathVariable(value = "id") int id) {
		Food food = foodDAO.getFood(id);
		
		Account user = (Account) session.getAttribute("account");
		int userId = user == null ? -1 : user.getAccountId();

		if (food == null || (food.getStatus() == 1 && userId != 1)) {
			return "not_found";
		}

		List<Category> list = categoryDAO.listCategories();
		List<Rated> rateds = ratedDAO.listRated(id);
		
		int[] countStar = new int[6];
		for (int i = 1; i < 6; i++) {
			countStar[i] = 0;
		}

		for (Rated rated : rateds) {
			countStar[rated.getStar()]++;
		}

		double avgStar = Constants.getAvgStar(food);

		List<Food> listFoodSameCategory = foodDAO.listFoodsInCategory(food.getCategory().getCategoryId());
		Collections.shuffle(listFoodSameCategory);
		listFoodSameCategory = listFoodSameCategory.subList(0, Math.min(3, listFoodSameCategory.size()));

		List<String> images = new ArrayList<String>();
		if (food.getImages() != null && food.getImages().trim().length() > 0) {
			for (String s : food.getImages().split("\\s+")) {
				images.add(s);
			}
		} else {
			images.add("resources/img/food.jpg");
		}
		model.addAttribute("images", images);

		model.addAttribute("categories", list);
		model.addAttribute("category", food.getCategory());
		model.addAttribute("food", food);
		model.addAttribute("avgStar", avgStar);
		model.addAttribute("avgStarString", String.format("%.1f", avgStar));
		model.addAttribute("countStar", countStar);
		model.addAttribute("listFoodSameCategory", listFoodSameCategory);
		model.addAttribute("rateds", rateds);

		System.out.println(user != null ? "not" : "null");

		if (user != null) {

			// Kiểm tra xem user đã từng đặt món ăn này chưa? nếu đặt rồi mới cho đánh giá
			boolean hasOrdered = orderDAO.hasOrdered(user.getAccountId(), id);

			System.out.println(hasOrdered);

			// Kiêm tra món ăn đã có trong giỏ hay chưa
			Cart cart = cartDAO.get(user.getAccountId(), id);

			model.addAttribute("hasOrdered", hasOrdered);
			model.addAttribute("addedToCart", cart != null);

			if (hasOrdered) {
				Rated rated = ratedDAO.getRated(user.getAccountId(), id);

				if (rated == null) {
					rated = new Rated();
				}

				model.addAttribute("userRating", rated);
			}
		}

		return "food/food";
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession session, @RequestParam Map<String, String> params) {
		int categoryId = -1, filter = -1, page = 1;
		String keyword = "";

		Category category = null;
		String title = "Danh sách món";

		if (params.containsKey("id_category")) {
			categoryId = Integer.parseInt(params.get("id_category"));
			if (categoryId != -1) {
				category = categoryDAO.getCategory(categoryId);
				title = "Danh mục: " + category.getName();
			}
		}
		if (params.containsKey("filter")) {
			filter = Integer.parseInt(params.get("filter"));
		}
		if (params.containsKey("keyword")) {
			keyword = params.get("keyword");
			if (keyword != null && keyword.trim().length() > 0) {
				title = "Tìm kiếm: " + keyword.trim();
			}
		}
		if (params.containsKey("page")) {
			page = Integer.parseInt(params.get("page"));
		}

		Account user = (Account) session.getAttribute("account");
		int userId = user == null ? -1 : user.getAccountId();

		List<Food> foods = foodDAO.listFoods(keyword, categoryId, filter, userId == 1);

		int startIndex = (page - 1) * Constants.FPP;
		int endIndex = Math.min(page * Constants.FPP, foods.size());

		model.addAttribute("categories", categoryDAO.listCategories());
		model.addAttribute("foods", foods.subList(startIndex, endIndex));
		model.addAttribute("maxPage", Constants.getMaxPage(foods.size(), Constants.FPP));

		model.addAttribute("id_category", categoryId);
		model.addAttribute("filter", filter);
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);

		model.addAttribute("title", title);

		model.addAttribute("url",
				String.format("food/index.htm?keyword=%s&id_category=%d&filter=%d&page=", keyword, categoryId, filter));

		if (categoryId != -1) {
			model.addAttribute("category", categoryDAO.getCategory(categoryId));
		}

		return "food/index";
	}

	@RequestMapping(value = "/{id}/rateds")
	public String rating(ModelMap model, @PathVariable(value = "id") int foodId,
			@RequestParam Map<String, String> params) {
		Food food = foodDAO.getFood(foodId);
		if (food == null) {
			return "not_found";
		}

		int page = 1, filter = 1;
		if (params.containsKey("page")) {
			page = Integer.parseInt(params.get("page"));
		}
		if (params.containsKey("filter")) {
			filter = Integer.parseInt(params.get("filter"));
		}

		if (page <= 0)
			return String.format("redirect:/food/%d/rateds.htm?page=1&filter=%d", foodId, filter);

		List<Rated> rateds = ratedDAO.listRated(food.getFoodId(), filter);

		int maxPage = Math.max(Constants.getMaxPage(rateds.size(), Constants.RPP), 1);

		if (page > maxPage)
			return String.format("redirect:/food/%d/rateds.htm?page=%d&filter=%d", foodId, maxPage, filter);

		model.addAttribute("categories", categoryDAO.listCategories());
		model.addAttribute("food", food);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("filter", filter);
		model.addAttribute("ratedSize", rateds.size());
		model.addAttribute("rateds", rateds == null ? new ArrayList<>()
				: rateds.subList((page - 1) * Constants.RPP, Math.min(page * Constants.RPP, rateds.size())));

		return "food/rateds";
	}

	@RequestMapping(value = "rating", method = RequestMethod.POST, params = { "id" })
	public String rating(ModelMap model, HttpSession session, @ModelAttribute("userRating") Rated rating,
			@RequestParam("id") int id) {

		Account user = (Account) session.getAttribute("account");
		if (user == null) {
			System.out.println("user null rooi");
		} else {
			Rated rated = ratedDAO.getRated(user.getAccountId(), id);

			if (rated == null) {
				// insert
				rated = rating;
				rated.setRatedId(new RatedKey(user.getAccountId(), id));
				boolean added = ratedDAO.insert(rated);
				System.out.println(added ? "OK" : "Fail");
			} else {
				// update
				rated.setStar(rating.getStar());
				rated.setComment(rating.getComment());
				rated.setCmtTime(new Date());

				boolean updated = ratedDAO.update(rated);

				System.out.println(updated ? "OK update" : "Fail update");
			}
		}

		return "redirect:/food/" + id + ".htm";
	}

	@RequestMapping(value = "rating", params = { "deleteId" })
	public String delete(ModelMap model, HttpSession session, @RequestParam("deleteId") int foodId) {

		Account user = (Account) session.getAttribute("account");
		if (user == null) {
			System.out.println("user null rooi");
		} else {
			Rated rated = ratedDAO.getRated(user.getAccountId(), foodId);

			if (rated != null) {
				// delete
				boolean deleted = ratedDAO.delete(rated);
				System.out.println(deleted ? "OK Delete" : "Fail delete");

			}
		}

		return "redirect:/food/" + foodId + ".htm";
	}

	@RequestMapping(value = "cart", params = { "id_food" })
	public String addToCart(ModelMap model, HttpSession session, @RequestParam("id_food") int foodId) {
		Account user = (Account) session.getAttribute("account");

		if (user == null) {
			System.out.println("user null rooi");

		} else {
			user = accountDAO.getAccount(user.getAccountId());

			Cart cart = cartDAO.get(user.getAccountId(), foodId);

			if (cart != null) {
				System.out.println("them roi, them lại làm gì nữa: " + cart.getQuantity());
			} else {
				cart = new Cart();
				cart.setCartId(new CartKey(user.getAccountId(), foodId));
				cart.setQuantity(1);

				boolean added = cartDAO.insert(cart);
				System.out.println(added ? "OK Add" : "Fail Add");
			}

		}
		return "redirect:/food/" + foodId + ".htm";
	}

}
