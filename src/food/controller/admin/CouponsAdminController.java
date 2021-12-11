package food.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.bean.CouponsBean;
import food.dao.CouponsDAO;
import food.entity.Coupons;

@Controller
@RequestMapping("admin/coupons")
public class CouponsAdminController {

	@Autowired
	private CouponsDAO couponsDAO;

	@RequestMapping()
	public String index(ModelMap model) {
		model.addAttribute("coupons", couponsDAO.listCoupons());

		return "admin/coupons/index";
	}

	@RequestMapping("add")
	public String addCounpons(ModelMap model) {
		model.addAttribute("couponsBean", new CouponsBean());
		return "admin/coupons/form";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addCounpons(ModelMap model, RedirectAttributes reAttributes,
			@Validated @ModelAttribute("couponsBean") CouponsBean cBean, BindingResult errors) {

		Coupons coupons = new Coupons();
		
		if(cBean.getCouponsId() == null || cBean.getCouponsId().trim().length() == 0) {
			errors.rejectValue("couponsId", "couponsBean", "Mã phiếu không được bỏ trống!");
			return "admin/coupons/form";
		}

		if (cBean.getValue() == null) {
			errors.rejectValue("value", "couponsBean", "Giá trị phiếu không được bỏ trống!");
			return "admin/coupons/form";
		}

		if (cBean.getType() == 1) {
			if (cBean.getValue() > 100) {
				errors.rejectValue("value", "couponsBean", "Giá trị phiếu không hợp lệ!");
				return "admin/coupons/form";
			}
		}

		if (cBean.getExpiredTime().trim().length() > 0) {
			try {
				coupons.setExpiredTime(new SimpleDateFormat("dd/MM/yyyy").parse(cBean.getExpiredTime()));
			} catch (ParseException e) {
				// e.printStackTrace();
				errors.rejectValue("expiredTime", "couponsBean", "Ngày không hợp lệ!");
				return "admin/coupons/form";
			}

		}

		coupons.setCouponsId(cBean.getCouponsId());
		coupons.setDetail(cBean.getDetail());
		coupons.setType(cBean.getType());
		coupons.setValue(cBean.getValue());
		coupons.setAmount(cBean.getAmount());
		coupons.setStatus(cBean.getStatus());

		Coupons cp = couponsDAO.get(coupons.getCouponsId());
		if (cp != null) {
			errors.rejectValue("couponsId", "couponsBean", "Mã phiếu đã tồn tại, vui lòng chọn mã khác");
			return "admin/coupons/form";
		}

		if (errors.hasErrors()) {
			model.addAttribute("couponsBean", cBean);
			return "admin/coupons/form";
		}

		boolean added = couponsDAO.add(coupons);
		if (added) {
			reAttributes.addFlashAttribute("message", "Thêm mới phiếu thành công!");
		} else {
			model.addAttribute("msgError", "Thêm mới thất bại!");
			return "admin/coupons/form";
		}

		return "redirect:/admin/coupons.htm";
	}

	@RequestMapping(value = "edit", params = { "id" })
	public String editCoupons(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") String couponsId) {
		Coupons cp = couponsDAO.get(couponsId);
		
		if(cp == null) {
			reAttributes.addFlashAttribute("msgError", "Phiếu không tồn tại!");
			return "redirect:/admin/coupons.htm";
		}
		
		CouponsBean cBean = new CouponsBean();
		cBean.setCouponsId(couponsId);
		cBean.setDetail(cp.getDetail());
		cBean.setAmount(cp.getAmount());
		cBean.setType(cp.getType());
		cBean.setStatus(cp.getStatus());
		cBean.setValue(cp.getValue());
		cBean.setExpiredTime(new SimpleDateFormat("dd/MM/yyyy").format(cp.getExpiredTime()));
		
		model.addAttribute("coupons", cp);
		model.addAttribute("couponsBean", cBean);
		
		return "admin/coupons/form";
	}

	@RequestMapping(value = "edit", params = { "id" }, method = RequestMethod.POST)
	public String addCounpons(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") String couponsId,
			@Validated @ModelAttribute("couponsBean") CouponsBean cBean, BindingResult errors) {
		
		System.out.println("Edit");
		
		Coupons coupons = couponsDAO.get(couponsId);
		cBean.setCouponsId(couponsId);
		
		if(cBean.getValue() == null) {
			errors.rejectValue("value", "couponsBean", "Giá trị phiếu không được bỏ trống!");
			return "admin/coupons/form";
		}
		
		System.out.println("1");
		
		if (cBean.getType() == 1) {
			if(cBean.getValue() > 100) {
				errors.rejectValue("value", "couponsBean", "Giá trị phiếu không hợp lệ!");
				return "admin/coupons/form";
			}
		}
		
		System.out.println("2");
		
		if (cBean.getExpiredTime().trim().length() > 0) {
			try {
				coupons.setExpiredTime(new SimpleDateFormat("dd/MM/yyyy").parse(cBean.getExpiredTime()));
			} catch (ParseException e) {
				//e.printStackTrace();
				errors.rejectValue("expiredTime", "couponsBean", "Ngày không hợp lệ!");
				return "admin/coupons/form";
			}

		}
		
		System.out.println("3");
		
		if (errors.hasErrors()) {
			model.addAttribute("couponsBean", cBean);
			return "admin/coupons/form";
		}
		
		coupons.setCouponsId(cBean.getCouponsId());
		coupons.setDetail(cBean.getDetail());
		coupons.setType(cBean.getType());
		coupons.setValue(cBean.getValue());
		coupons.setAmount(cBean.getAmount());
		coupons.setStatus(cBean.getStatus());
		
		System.out.println("4");
		
		boolean added = couponsDAO.update(coupons);
		if (added) {
			reAttributes.addFlashAttribute("message", "Chỉnh sửa phiếu thành công!");
		} else {
			model.addAttribute("msgError", "Chỉnh sửa thất bại!");
			System.out.println("That bai");
			return "admin/coupons/form";
		}

		return "redirect:/admin/coupons.htm";
	}
	
}
