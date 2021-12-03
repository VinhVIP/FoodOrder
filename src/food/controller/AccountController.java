package food.controller;

import java.util.Base64;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import food.bean.AccountBean;
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

	@Autowired
	HttpServletRequest request;

	@Autowired
	@Qualifier("rootFile")
	private UploadFile rootFile;

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
			@RequestParam("email") String email, @RequestParam("pw") String pw,
			@RequestParam(value = "rm", defaultValue = "false") boolean rm) {

		boolean isEmail = false;
		if (email.contains("@"))
			isEmail = true;

		Account user = null;

		if (isEmail) {
			user = accountDAO.findByEmail(email);
		} else {
			user = accountDAO.findByPhone(email);
		}

		if (user == null) {
			model.addAttribute("message", "Email/SĐT không chính xác!");
		} else if (user.getStatus() == 1) {
			model.addAttribute("message", "Tài khoản đã bị khoá, vui lòng liên hệ admin để mở khoá");
		} else if (!Constants.md5(pw).equals(user.getPassword())) {
			model.addAttribute("message", "Mật khẩu không hợp lệ!");
		} else {
			System.out.println(pw);
			session.setAttribute("account", user);

			// Ghi nho tai khoan bang cookie
			if (rm == true) {
				Cookie ckemail = this.create("email", user.getEmail(), 30);
				Cookie ckpass = this.create("pass", pw, 30);

				response.addCookie(ckemail);
				response.addCookie(ckpass);

			} else {
				this.delete("email");
				this.delete("pass");
			}
			return "redirect:/home.htm";
		}
		return "account/signin";
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(ModelMap model, HttpSession session) {

		AccountBean aBean = new AccountBean();
		model.addAttribute("user", aBean);
		return "account/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(ModelMap model, RedirectAttributes reAttributes, HttpSession session,
			@Validated @ModelAttribute("user") AccountBean aBean, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("user", aBean);
			return "account/signup";
		}

		Account exists = accountDAO.findByEmail(aBean.getEmail());
		if (exists != null) {
			errors.rejectValue("email", "user", "Email này đã được đăng ký!");
//			model.addAttribute("accountBean", aBean);
			return "account/signup";
		}

		exists = accountDAO.findByPhone(aBean.getPhone());
		if (exists != null) {
			errors.rejectValue("phone", "user", "SĐT này đã được đăng ký!");
//			model.addAttribute("accountBean", aBean);
			return "account/signup";
		}

		Account user = new Account();

		user.setName(aBean.getName());
		user.setEmail(aBean.getEmail());
		user.setPhone(aBean.getPhone());
		user.setAddress(aBean.getAddress());
		user.setPassword(Constants.md5(aBean.getPassword()));
		user.setAvatar("resources/img/avatar/avatar.jpg");
		user.setCreatedTime(new Date());
		user.setStatus(0);

		boolean add = accountDAO.insert(user);
		System.out.println(add ? "OK" : "Ko");
		if (add) {
			model.addAttribute("message", "Đăng ký thành công!");
		} else {
			model.addAttribute("message", "Đăng ký thất bại");
		}
		model.addAttribute("accountBean", aBean);
		return "account/signup";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.GET)
	public String forgot(ModelMap model) {
		return "account/forgot";
	}

	@RequestMapping(value = "forgot", method = RequestMethod.POST)
	public String forgot(ModelMap model, RedirectAttributes reAttributes, @RequestParam("email") String email,
			HttpServletRequest request, HttpServletResponse response, HttpSession ss) {
		String captcha = ss.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");

		boolean verify = false;
		if (captcha.equals(verifyCaptcha)) {
			verify = true;
		} else {
			verify = false;
		}
		if (!verify) {
			model.addAttribute("message", "Vui lòng nhập Captcha");
			return "account/forgot";
		} else {
			Account user = accountDAO.findByEmail(email);
			if (user == null) {
				model.addAttribute("message", "Email chưa đăng ký");
			} else {

				String code = Constants.randomCode(6);

				ss.setAttribute(email, code);

				String from = "npnhanh19@gmail.com";
				String to = email;
				String subject = "Forgot Password";
				String body = "Your code is " + code;

				mailer.sendmailer(from, to, subject, body);
//				String url = request.getRequestURI().toString().replace("forgot", "signin");
				model.addAttribute("message", "Mã xác nhận đã được gửi đến hộp thư của bạn");

				model.addAttribute("email", email);
				return "account/code";
			}
		}
		return "account/forgot";
	}

	@RequestMapping(value = "change", method = RequestMethod.GET)
	public String change(ModelMap model) {
		return "account/change";
	}

	@RequestMapping(value = "change", method = RequestMethod.POST)
	public String change(ModelMap model, @RequestParam("email") String email, @RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1, @RequestParam("pw2") String pw2) {

		Account user = accountDAO.findByEmail(email);
		if (user == null) {
			model.addAttribute("message", "Vui lòng nhập Email hợp lệ!");
		} else if (Constants.isSameMD5(pw, user.getPassword())) {
			model.addAttribute("message", "Mật khẩu không đúng!");
		} else {
			if (!pw1.equals(pw2)) {
				model.addAttribute("message", "Xác nhận mật khẩu không khớp!");
			} else {
				user.setPassword(Constants.md5(pw1));
				accountDAO.update(user);
				String url = request.getServletContext().getContextPath() + "/home.htm";
				model.addAttribute("message",
						"Đổi mật khẩu thành công! <br/>" + "<a href='" + url + "'>Quay về trang chủ</a>");
			}
		}
		return "account/change";
	}

	@RequestMapping(value = "change_forgot", method = RequestMethod.GET)
	public String changeForgot(ModelMap model) {
//		model.addAttribute("email", "");
		return "account/change_forgot";
	}

	@RequestMapping("code")
	public String code(ModelMap model) {
		return "account/code";
	}

	@RequestMapping(value = "code", method = RequestMethod.POST)
	public String code(ModelMap model, RedirectAttributes reAttributes, HttpSession session,
			@RequestParam("email") String email, @RequestParam("code") String code) {
		String ranCode = (String) session.getAttribute(email);
		if (ranCode == null || ranCode.length() == 0) {
			System.out.println("random code không tồn tại!");
			return "redirect:/home.htm";
		}
		System.out.println(ranCode + " - " + code);
		if (ranCode.equals(code)) {
			model.addAttribute("email", email);
			return "account/change_forgot";
		}

		model.addAttribute("email", email);
		model.addAttribute("message", "Mã code không chính xác!");
		return "account/code";
	}

	@RequestMapping(value = "change_forgot", method = RequestMethod.POST)
	public String changeForgot(ModelMap model, @RequestParam("email") String email, @RequestParam("pw1") String pw1,
			@RequestParam("pw2") String pw2) {

		Account user = accountDAO.findByEmail(email);
		if (user == null) {
			model.addAttribute("message", "Vui lòng nhập Email hợp lệ!");
		} else {
			if (!pw1.equals(pw2)) {
				model.addAttribute("message", "Xác nhận mật khẩu không khớp!");
			} else {
				user.setPassword(Constants.md5(pw1));
				accountDAO.update(user);
				String url = request.getServletContext().getContextPath() + "/account/signin.htm";
				model.addAttribute("message",
						"Đổi mật khẩu thành công. " + "Click <a href='" + url + "'>SignIn</a> để đăng nhập");
			}
		}
		model.addAttribute("email", email);
		return "account/change_forgot";
	}
}
