package food.controller;

import java.io.File;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.bean.AdminMailer;
import food.bean.UploadFile;
import food.dao.AccountDAO;
import food.entity.Account;
import food.utils.Constants;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountDAO accountDAO;

	@Autowired
	private AdminMailer mailer;

	@Autowired
	@Qualifier("avatarFile")
	private UploadFile upFile;

	public Cookie create(String name, String value, int days) {
		String encodedValue = Base64.getEncoder().encodeToString(value.getBytes());
		Cookie cookie = new Cookie(name, encodedValue);
		cookie.setMaxAge(days * 24 * 60 * 60);
		cookie.setPath("/");
		return cookie;

	}

	public Cookie read(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(name)) {
					String decodedvalue = new String(Base64.getDecoder().decode(cookie.getValue()));
					cookie.setValue(decodedvalue);
					return cookie;
				}
			}
		}
		return null;
	}

	public void delete(String name) {
		this.create(name, "", 0);
	}

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public String signin(ModelMap model, HttpServletRequest request) {
		Cookie ckemail = this.read(request, "email");
		Cookie ckpw = this.read(request, "pass");
		if (ckemail != null) {
			String uemail = ckemail.getValue();
			String pwd = ckpw.getValue();

			model.addAttribute("email", uemail);
			model.addAttribute("pwd", pwd);
		}
		return "account/signin";
	}

	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String signin(ModelMap model, HttpServletResponse response, HttpSession session,
			@RequestParam("email") String email, @RequestParam("password") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {

		Account user = accountDAO.findByEmail(email);
		if (user == null) {
			model.addAttribute("message", "Tên đăng nhập không hợp lệ!");
		} else if (pw.equals(user.getPassword())) {
			model.addAttribute("message", "Mật khẩu không hợp lệ!");
		} else if (user.getStatus() == 1) {
			model.addAttribute("message", "Tài khoản đã bị khoá, vui lòng liên hệ admin để mở khoá");
		} else {
			model.addAttribute("message", "Đăng nhập thành công");

			// lưu là account nha
			session.setAttribute("account", user);

			// Ghi nho tai khoan bang cookie
			if (rm == true) {
				Cookie ckemail = this.create("email", user.getEmail(), 30);
				Cookie ckpass = this.create("pass", user.getPassword(), 30);

				response.addCookie(ckemail);
				response.addCookie(ckpass);

			} else {
				this.delete("email");
				this.delete("pass");
			}
		}
		return "account/signin";
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(ModelMap model) {
		Account user = new Account();
		model.addAttribute("user", user);
		return "account/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(ModelMap model, RedirectAttributes reAttributes, @ModelAttribute("user") Account user,
			@RequestParam("avatar") MultipartFile img) {
		String images = "";
		if (img.isEmpty()) {
			user.setAvatar("avatar.jpg");
		} else {
			String nameFormat = Constants.getCurrentTime() + "_" + Constants.rewriteFileName(img.getOriginalFilename());

			String logoPath = upFile.getBasePath() + File.separator + nameFormat;
			images += "resources/img/avatar/" + nameFormat + " ";

			try {
				img.transferTo(new File(logoPath));
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		user.setAvatar(images.trim());

		boolean added = accountDAO.insert(user);
		if (added) {
			reAttributes.addFlashAttribute("message", "Đăng ký thành công!");
		} else {
			model.addAttribute("msgError", "Đăng ký thất bại!");
			return "account/signup";
		}

		return "redirect:/account/signup";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.GET)
	public String forgot(ModelMap model) {
		return "account/forgot";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.POST)
	public String forgot(ModelMap model, @RequestParam("email") String email) {
		Account user = new Account();
		if (!email.equals(user.getEmail())) {
			model.addAttribute("message", "Email chưa đăng ký");
		} else {

			String from = "vinhvipit@gmail.com";
			String to = email;
			String subject = "Forgot Password";
			String body = "Your password is " + user.getPassword();

			mailer.sendmailer(from, to, subject, body);

			model.addAttribute("message", "Mật khẩu của bạn đã được gửi đến hộp thư đến của bạn");
		}

		return "redirect:/account/signin";
	}

	@RequestMapping(value = "change", method = RequestMethod.GET)
	public String change(ModelMap model) {
		return "account/change";
	}

	@RequestMapping(value = "change", method = RequestMethod.POST)
	public String change(ModelMap model, @RequestParam("email") String email, @RequestParam("password") String pw,
			@RequestParam("password1") String pw1, @RequestParam("password1") String pw2) {
		Account user = accountDAO.findByEmail(email);
		if (!pw1.equals(pw2)) {
			model.addAttribute("message", "Mật khẩu không giống nhau!");
		} else {
			if (user == null) {
				model.addAttribute("message", "Tên đăng nhập không hợp lệ!");
			} else if (pw.equals(user.getPassword())) {
				model.addAttribute("message", "Mật khẩu không hợp lệ!");
			} else {
				user.setPassword(pw1);
				accountDAO.update(user);
				model.addAttribute("message", "Đổi mật khẩu thành công!");
			}
		}
		return "redirect:/account/signin";
	}
}
