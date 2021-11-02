package food.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import food.bean.AdminMailer;
import food.dao.AccountDAO;
import food.entity.Account;

@Controller
@RequestMapping("admin/mail")
public class MailAdminController {

	@Autowired
	AdminMailer mailer;
	
	@Autowired
	AccountDAO accountDAO;

	@RequestMapping()
	public String form() {
		return "admin/mail/form";
	}

	@RequestMapping(value = "send", method = RequestMethod.POST)
	public String send(ModelMap model, @RequestParam("subject") String subject, @RequestParam("body") String body) {
		try {
			String from = "vinhvipit@gmail.com";

			mailer.send(from, getAllUserEmail(), subject, body);
			model.addAttribute("message", "Gửi mail thành công");
		} catch (Exception e) {
			model.addAttribute("message", "Gửi mail thất bại");
		}

		return "admin/mail/form";
	}
	
	private String[] getAllUserEmail() {
		List<Account> list = accountDAO.listAccounts();
		String[] emails = new String[list.size()];
		for(int i=0; i<list.size(); i++) {
			emails[i] = list.get(i).getEmail();
		}
		return emails;
	}
}
