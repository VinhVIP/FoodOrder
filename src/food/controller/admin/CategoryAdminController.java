package food.controller.admin;

import java.io.File;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.bean.CategoryBean;
import food.bean.UploadFile;
import food.dao.CategoryDAO;
import food.entity.Category;
import food.utils.Constants;

@Controller
@RequestMapping("admin/category")
public class CategoryAdminController {
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("categoryFile")
	private UploadFile upFile;

	@Autowired
	@Qualifier("rootFile")
	private UploadFile rootFile;

	@RequestMapping()
	public String indexCategory(ModelMap model) {

		model.addAttribute("categories", categoryDAO.listCategories());
		return "admin/category/index";
	}

	/**
	 * Điều hướng đến form Thêm danh mục
	 */
	@RequestMapping(value = "add")
	public String addCategory(ModelMap model) {
		model.addAttribute("categoryBean", new CategoryBean());
		return "admin/category/form";
	}

	/**
	 * Xử lý Thêm danh mục
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String insertCategory(ModelMap model, RedirectAttributes reAttributes,
			@Validated @ModelAttribute("categoryBean") CategoryBean cBean, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("categoryBean", cBean);
			return "admin/category/form";
		}

		Category c = new Category();
		c.setName(cBean.getName());

		Category sameName = categoryDAO.getCategoryByName(cBean.getName());
		if (sameName == null) {

			if (cBean.getLogo().isEmpty()) {
				c.setLogo("resources/img/category/icon.png");
			} else {

				String nameFormat = Constants.getCurrentTime() + "_"
						+ Constants.rewriteFileName(cBean.getLogo().getOriginalFilename());

				String logoPath = upFile.getBasePath() + File.separator + nameFormat;
				c.setLogo("resources/img/category/" + nameFormat);

				try {
					cBean.getLogo().transferTo(new File(logoPath));
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			boolean added = categoryDAO.insert(c);
			if (added) {
				reAttributes.addFlashAttribute("message", "Thêm mới danh mục thành công!");
			} else {
				model.addAttribute("msgError", "Thêm mới thất bại!");
				return "admin/category/form";
			}

		} else {
			errors.rejectValue("name", "categoryBean", "Tên danh mục đã tồn tại!");
			return "admin/category/form";
		}

		reAttributes.addFlashAttribute("categories", categoryDAO.listCategories());
		return "redirect:/admin/category.htm";
	}

	/**
	 * Điều hướng đến form Chỉnh sửa danh mục
	 */
	@RequestMapping(value = "edit", params = { "id" })
	public String formEditCategory(ModelMap model, RedirectAttributes reAttributes,
			@RequestParam("id") int categoryId) {
		Category c = categoryDAO.getCategory(categoryId);

		if (c == null) {
			reAttributes.addFlashAttribute("msgError", "Danh mục không tồn tại!");
			reAttributes.addFlashAttribute("categories", categoryDAO.listCategories());
			return "redirect:/admin/category.htm";
		}

		CategoryBean cBean = new CategoryBean();
		cBean.setName(c.getName());

		model.addAttribute("category", c);
		model.addAttribute("categoryBean", cBean);

		return "admin/category/form";
	}

	/**
	 * Xử lý Chỉnh sửa danh mục
	 */
	@RequestMapping(value = "edit", params = { "id" }, method = RequestMethod.POST)
	public String editCategory(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int categoryId,
			@Validated @ModelAttribute("categoryBean") CategoryBean cBean, BindingResult errors) {

		Category c = categoryDAO.getCategory(categoryId);

		if (errors.hasErrors()) {
			model.addAttribute("categoryBean", cBean);
			model.addAttribute("category", c);
			return "admin/category/form";
		}

		if (!cBean.getName().equalsIgnoreCase(c.getName())) {
			Category sameName = categoryDAO.getCategoryByName(cBean.getName());
			if (sameName != null) {
				errors.rejectValue("msgError", "categoryBean", "Tên danh mục đã tồn tại!");
				model.addAttribute("category", c);
				return "admin/category/form";
			}
		}

		c.setName(cBean.getName());

		if (cBean.getLogo().isEmpty()) {
			// c.setLogo("resources/img/icon.png");
		} else {
			// Xóa hình ảnh cũ
			File file = new File(rootFile.getBasePath() + File.separator + c.getLogo());
			if (file.exists())
				file.delete();
			
			String nameFormat = Constants.getCurrentTime() + "_"
					+ Constants.rewriteFileName(cBean.getLogo().getOriginalFilename());

			String logoPath = upFile.getBasePath() + File.separator + nameFormat;
			c.setLogo("resources/img/category/" + nameFormat);

			try {
				cBean.getLogo().transferTo(new File(logoPath));
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		boolean updated = categoryDAO.update(c);
		if (updated) {
			reAttributes.addFlashAttribute("message", "Chỉnh sửa danh mục thành công!");
		} else {
			reAttributes.addFlashAttribute("msgError", "Chỉnh sửa thất bại!");
		}

		reAttributes.addFlashAttribute("categories", categoryDAO.listCategories());
		return "redirect:/admin/category.htm";
	}

	/**
	 * Xử lý Xóa danh mục
	 */
	@RequestMapping(params = { "delete" })
	public String deleteCategory(RedirectAttributes reAttributes, HttpSession session,
			@RequestParam("delete") int categoryId) {
		Category c = categoryDAO.getCategory(categoryId);

		if (c != null) {
			if (c.getFoods().size() > 0) {
				reAttributes.addFlashAttribute("msgError", "Đã có món trong danh mục nên không thể xóa!");
			} else {
				boolean deleted = categoryDAO.delete(c);
				if (deleted) {
					reAttributes.addFlashAttribute("message", "Xóa thành công!");
					File file = new File(rootFile.getBasePath() + File.separator + c.getLogo());
					if (file.exists())
						file.delete();
				} else
					reAttributes.addFlashAttribute("msgError", "Xóa thất bại!");
			}

		} else {
			reAttributes.addFlashAttribute("msgError", "Không tìm thấy danh mục cần xóa!");
		}

		reAttributes.addFlashAttribute("categories", categoryDAO.listCategories());
		return "redirect:/admin/category.htm";
	}
}
