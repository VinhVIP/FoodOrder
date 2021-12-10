package food.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import food.dao.OrderDAO;
import food.entity.Account;
import food.entity.Order;
import food.entity.OrderDetail;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {
	@Autowired
	OrderDAO orderDAO;

	@RequestMapping(value = "index")
	public String index(ModelMap model, HttpSession session, HttpServletRequest request) {
		Account user = (Account) session.getAttribute("account");
		List<Order> list = orderDAO.getOrder(user.getAccountId());
		
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(10);
		//model.addAttribute("orders", list);
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "order/index";
	}

	@RequestMapping(value = "detail/{id}.htm")
	public String detail(ModelMap model, @PathVariable("id") int id) {
		float total = 0;
		float discountValue = 0;
		float discountType = 0;
		List<OrderDetail> list = orderDAO.getOrdersDetail(id);
		if (list.get(0).getOrder().getCoupons() != null) {
			discountValue = list.get(0).getOrder().getCoupons().getValue();
			discountType = list.get(0).getOrder().getCoupons().getType();
		}

		for (OrderDetail o : list) {
			total += o.getAmount() * o.getPrice();
		}

		float finalPrice = total;
		if (discountType == 0)
			finalPrice = Math.max(finalPrice - discountValue, 0);
		else
			finalPrice *= (discountValue / 100);

		model.addAttribute("total", finalPrice);
		model.addAttribute("orderDetail", list);
		return "order/detail";
	}
	@RequestMapping(value = "status", params = {"orderId","status"})
	public String updateStatus(ModelMap model, @RequestParam("orderId") int orderId, @RequestParam("status") int status, HttpSession session) {
		orderDAO.updateStatus(orderId, status);
		return "redirect:/order/index.htm";
	}
}
