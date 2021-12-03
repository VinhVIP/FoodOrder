package food.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.bean.ProfileBean;
import food.bean.UploadFile;
import food.dao.AccountDAO;
import food.dao.CouponsDAO;
import food.entity.Account;

@Controller
@RequestMapping("account")
public class ProfileController {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired CouponsDAO couponsDAO;
	
	@Autowired
	@Qualifier("avatarFile")
	private UploadFile upFile;

	@Autowired
	@Qualifier("rootFile")
	private UploadFile rootFile;

	@RequestMapping("profile")
	public String profile(ModelMap model, RedirectAttributes reAttributes, HttpSession session) {
		Account user = (Account) session.getAttribute("account");
		
		if(user == null) {
			return "redirect:/home.htm";
		}

		ProfileBean accountBean = new ProfileBean();
		accountBean.setName(user.getName());
		accountBean.setEmail(user.getEmail());
		accountBean.setPhone(user.getPhone());
		accountBean.setAddress(user.getAddress());

		model.addAttribute("accountBean", accountBean);

		return "account/profile";
	}

	@RequestMapping(value = "profile", method = RequestMethod.POST)
	public String editProfile(ModelMap model, RedirectAttributes reAttributes,
			HttpSession session,
			@Validated @ModelAttribute("accountBean") ProfileBean aBean,
			BindingResult errors) {
		
		if(errors.hasErrors()) {
			model.addAttribute("accountBean", aBean);
			return "account/profile";
		}

		Account user = (Account) session.getAttribute("account");
		
		user.setName(aBean.getName());
		user.setEmail(aBean.getEmail());
		user.setPhone(aBean.getPhone());
		user.setAddress(aBean.getAddress());
		
		
		if(aBean.getAvatar().isEmpty()) {
			// Nếu k chọn ảnh mới thì k cập nhật avatar
		}else {
			// Xóa avatar cũ
			File file = new File(rootFile.getBasePath() + File.separator + user.getAvatar());
			if (file.exists())
				file.delete();
			
			String avatarPath = upFile.getBasePath() + File.separator + user.getAccountId() + ".jpg";
			user.setAvatar("resources/img/avatar/" + user.getAccountId() + ".jpg");
			
			try {
				aBean.getAvatar().transferTo(new File(avatarPath));
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		boolean updated = accountDAO.update(user);
		if(updated) {
			System.out.println("OK");
//			reAttributes.addFlashAttribute("message", "Cập nhật thông tin cá nhân thành công!");
			model.addAttribute("message", "Cập nhật thông tin cá nhân thành công!");
		}else {
			System.out.println("No");
//			reAttributes.addFlashAttribute("message", "Cập nhật thất bại, xin vui lòng thử lại sau!");
			model.addAttribute("message", "Cập nhật thất bại, xin vui lòng thử lại sau!");
		}
		
//		reAttributes.addFlashAttribute("accountBean", aBean);
		
		model.addAttribute("accountBean", aBean);
		return "account/profile";
//		return "redirect:/account/profile.htm";
	}
	
	@RequestMapping("coupons")
	public String coupons(ModelMap model, HttpSession session) {
		model.addAttribute("coupons", couponsDAO.listPublicCoupons());
		
		return "account/coupons";
	}
}
