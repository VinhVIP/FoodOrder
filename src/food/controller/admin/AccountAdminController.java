package food.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import food.dao.AccountDAO;
import food.entity.Account;
import food.utils.Constants;

@Controller
@RequestMapping("admin/account")
public class AccountAdminController {

	@Autowired
	AccountDAO accountDAO;

	@RequestMapping()
	public String index() {
		return "redirect:/admin/account.htm?page=1";
	}

	@RequestMapping(params = { "page" })
	public String index(ModelMap model, @RequestParam("page") int page) {
		List<Account> accounts = accountDAO.listAccounts();

		int endIndex = Math.min(accounts.size(), page * Constants.APP);
		model.addAttribute("accounts", accounts.subList((page - 1) * Constants.APP, endIndex));

		int maxPage = Constants.getMaxPage(accounts.size(), Constants.APP);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", maxPage);

		return "admin/account/index";
	}
	
	@RequestMapping(value="locked")
	public String locked() {
		return "redirect:/admin/account/locked.htm?page=1";
	}
	
	@RequestMapping(value="locked", params = { "page" })
	public String locked(ModelMap model, @RequestParam("page") int page) {
		List<Account> accounts = accountDAO.listLockAccounts();

		int endIndex = Math.min(accounts.size(), page * Constants.APP);
		model.addAttribute("accounts", accounts.subList((page - 1) * Constants.APP, endIndex));

		int maxPage = Constants.getMaxPage(accounts.size(), Constants.APP);
		model.addAttribute("page", page);
		model.addAttribute("maxPage", maxPage);

		return "admin/account/locked";
	}

	@RequestMapping(value = "lock", params = { "id" })
	public String lock(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int accountId) {

		Account account = accountDAO.getAccount(accountId);
		if (account != null) {
			account.setStatus(1);
			accountDAO.update(account);
			reAttributes.addFlashAttribute("message", "Khóa tài khoản thành công!");
		} else {
			reAttributes.addFlashAttribute("msgError", "Khóa tài khoản thất bại!");
		}

		return "redirect:/admin/account.htm?page=1";
	}

	@RequestMapping(value = "unlock", params = { "id" })
	public String unlock(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int accountId) {

		Account account = accountDAO.getAccount(accountId);
		if (account != null) {
			account.setStatus(0);
			accountDAO.update(account);
			reAttributes.addFlashAttribute("message", "Mở khóa tài khoản thành công!");
		} else {
			reAttributes.addFlashAttribute("msgError", "Mở khóa tài khoản thất bại!");
		}

		return "redirect:/admin/account.htm?page=1";
	}
	
	@RequestMapping(value = "unlock2", params = { "id" })
	public String unlock2(ModelMap model, RedirectAttributes reAttributes, @RequestParam("id") int accountId) {

		Account account = accountDAO.getAccount(accountId);
		if (account != null) {
			account.setStatus(0);
			accountDAO.update(account);
			reAttributes.addFlashAttribute("message", "Mở khóa tài khoản thành công!");
		} else {
			reAttributes.addFlashAttribute("msgError", "Mở khóa tài khoản thất bại!");
		}

		return "redirect:/admin/account/locked.htm?page=1";
	}

}
