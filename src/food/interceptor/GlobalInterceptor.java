package food.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import food.dao.AccountDAO;
import food.entity.Account;

public class GlobalInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	AccountDAO accountDAO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Account user = (Account) session.getAttribute("account");
		if (user != null) {
			request.setAttribute("user", accountDAO.getAccount(user.getAccountId()));
			if(user.getStatus() == 1) {
				session.removeAttribute("account");
				response.sendRedirect(request.getContextPath() + "/home.htm");
				return false;
			}
		}

		return true;
	}

}
