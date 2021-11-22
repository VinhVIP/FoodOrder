package food.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String index(ModelMap model, HttpSession session) {
		Account user = (Account) session.getAttribute("account");
		List<Order> list = orderDAO.getOrder(user.getAccountId());
		model.addAttribute("orders",list);
		
		return "order/index";
	}
	
	@RequestMapping(value = "detail/{id}.htm")
	public String detail(ModelMap model, @PathVariable("id") int id) {
		float total = 0;
		float discount = 0;
		List<OrderDetail> list = orderDAO.getOrdersDetail(id);
		if(list.get(0).getOrder().getCoupons() != null) {
			discount = list.get(0).getOrder().getCoupons().getValue();
		}
		
		for (OrderDetail o : list) {
			total += o.getAmount() * o.getPrice();
		}
		model.addAttribute("total",total-discount);
		model.addAttribute("orderDetail",list);
		return "order/detail";
	}
}
