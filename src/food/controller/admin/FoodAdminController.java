package food.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.bean.FoodBean;
import food.bean.UploadFile;
import food.dao.CategoryDAO;
import food.dao.FoodDAO;
import food.entity.Food;
import food.utils.Constants;

@Controller
@RequestMapping("admin/food")
public class FoodAdminController {

	@Autowired
	private FoodDAO foodDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("foodFile")
	private UploadFile upFile;

	@Autowired
	@Qualifier("rootFile")
	private UploadFile rootFile;

	@RequestMapping()
	public String index() {
		return "redirect:/admin/food.htm?page=1";
	}

	@RequestMapping(params = { "page" })
	public String index(ModelMap model, @RequestParam("page") int page) {
		List<Food> foods = foodDAO.listFoods("", -1, Constants.FILTER_BY_NEWEST, true);

		int endIndex = Math.min(foods.size(), page * Constants.FPP);
		model.addAttribute("foods", foods.subList((page - 1) * Constants.FPP, endIndex));

		int maxPage = Constants.getMaxPage(foods.size(), Constants.FPP);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", maxPage);

		return "admin/food/index";
	}

	@RequestMapping("add")
	public String addFood(ModelMap model) {
		model.addAttribute("foodBean", new FoodBean());
		model.addAttribute("categories", categoryDAO.listCategories());
		return "admin/food/form";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addFood(ModelMap model, RedirectAttributes reAttributes,
			@Validated @ModelAttribute("foodBean") FoodBean foodBean, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("foodBean", foodBean);
			model.addAttribute("categories", categoryDAO.listCategories());
			return "admin/food/form";
		}

		Food food = new Food();
		food.setName(foodBean.getName());
		food.setPrice(foodBean.getPrice());
		food.setDetail(foodBean.getDetail());
		food.setType(foodBean.getType());
		food.setStatus(foodBean.getStatus());
		food.setCategory(categoryDAO.getCategory(foodBean.getCategory()));

		String images = "";

		for (MultipartFile img : foodBean.getImages()) {
			if (!img.isEmpty()) {
				String nameFormat = Constants.getCurrentTime() + "_"
						+ Constants.rewriteFileName(img.getOriginalFilename());

				String logoPath = upFile.getBasePath() + File.separator + nameFormat;
				images += "resources/img/food/" + nameFormat + " ";

				try {
					img.transferTo(new File(logoPath));
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		food.setImages(images.trim());

		boolean added = foodDAO.insert(food);
		if (added) {
			reAttributes.addFlashAttribute("message", "Thêm mới món ăn thành công!");
		} else {
			model.addAttribute("msgError", "Thêm mới thất bại!");
			return "admin/food/form";
		}

		return "redirect:/admin/food.htm?page=1";
	}

	@RequestMapping(value = "edit", params = { "id" })
	public String editFood(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int foodId) {
		Food food = foodDAO.getFood(foodId);

		if (food == null) {
			reAttributes.addFlashAttribute("msgError", "Món ăn không tồn tại!");
			return "redirect:/admin/food.htm?page=1";
		}

		FoodBean foodBean = new FoodBean();
		foodBean.setName(food.getName());
		foodBean.setCategory(food.getCategory().getCategoryId());
		foodBean.setPrice(food.getPrice());
		foodBean.setDetail(food.getDetail());
		foodBean.setType(food.getType());
		foodBean.setStatus(food.getStatus());

		model.addAttribute("food", food);
		model.addAttribute("foodBean", foodBean);
		model.addAttribute("categories", categoryDAO.listCategories());

		return "admin/food/form";
	}

	/**
	 * Xử lý Chỉnh sửa món ăn
	 */
	@RequestMapping(value = "edit", params = { "id" }, method = RequestMethod.POST)
	public String editCategory(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int foodId,
			@Validated @ModelAttribute("foodBean") FoodBean foodBean, BindingResult errors) {

		Food food = foodDAO.getFood(foodId);

		if (errors.hasErrors()) {
			model.addAttribute("food", food);
			model.addAttribute("foodBean", foodBean);
			model.addAttribute("categories", categoryDAO.listCategories());
			return "admin/food/form";
		}

		food.setName(foodBean.getName());
		food.setPrice(foodBean.getPrice());
		food.setDetail(foodBean.getDetail());
		food.setType(foodBean.getType());
		food.setStatus(foodBean.getStatus());
		food.setCategory(categoryDAO.getCategory(foodBean.getCategory()));

		List<String> listImages = new ArrayList<>();
		String[] imageInDBPath = { "", "", "" };

		if (food.getImages() != null) {
			String[] imageList = food.getImages().trim().split("\\s+");
			for (int i = 0; i < imageList.length; i++) {
				imageInDBPath[i] = imageList[i];
			}
		}

		for (int i = 0; i < foodBean.getImages().length; i++) {
			MultipartFile img = foodBean.getImages()[i];
			String imagePath = foodBean.getImagePath()[i];

			if (img.isEmpty()) {
				// Nếu k có hình ảnh nào được tải lên

				// Kiểm tra xem trong db có hình ảnh nào khác không
				// Có thì lưu lại vào listImages
				if (imagePath.trim().length() > 0)
					listImages.add(imagePath);
			} else {
				// Nếu có tải lên hình ảnh mới

				// Xóa hình ảnh cũ nếu có
				if (imageInDBPath[i].trim().length() > 0) {
					File file = new File(rootFile.getBasePath() + File.separator + imageInDBPath[i]);
					if (file.exists())
						file.delete();
				}

				String nameFormat = Constants.getCurrentTime() + "_"
						+ Constants.rewriteFileName(img.getOriginalFilename());

				String logoPath = upFile.getBasePath() + File.separator + nameFormat;

				listImages.add("resources/img/food/" + nameFormat);

				try {
					img.transferTo(new File(logoPath));
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		String images = "";
		for (String s : listImages) {
			images += s + " ";
		}

		food.setImages(images.trim());

		boolean added = foodDAO.update(food);
		if (added) {
			reAttributes.addFlashAttribute("message",
					"Chỉnh sửa món ăn thành công! <a href='food/" + foodId + ".htm'>Xem kết quả</a>");
		} else {
			model.addAttribute("msgError", "Chỉnh sửa món ăn thất bại!");
			return "admin/food/form";
		}

		return "redirect:/admin/food.htm?page=1";
	}

	/**
	 * Xử lý Xóa món ăn
	 */
	@RequestMapping(params = { "delete" })
	public String deleteCategory(RedirectAttributes reAttributes, HttpSession session,
			@RequestParam("delete") int foodId) {
		Food food = foodDAO.getFood(foodId);

		if (food != null) {
			boolean deleted = foodDAO.delete(food);
			if (deleted) {
				// Xóa hình ảnh cũ

				if (food.getImages() != null && food.getImages().trim().length() > 0) {
					String[] imageList = food.getImages().split("\\s+");
					for (String fileName : imageList) {
						File file = new File(rootFile.getBasePath() + File.separator + fileName);
						if (file.exists())
							file.delete();
					}
				}

				reAttributes.addFlashAttribute("message", "Xóa thành công!");
			} else
				reAttributes.addFlashAttribute("msgError", "Xóa thất bại!");

		} else {
			reAttributes.addFlashAttribute("msgError", "Không tìm thấy món ăn cần xóa!");
		}

		return "redirect:/admin/food.htm?page=1";
	}

}
