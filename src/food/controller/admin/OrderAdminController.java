package food.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import food.bean.AdminMailer;
import food.dao.OrderDAO;
import food.entity.Order;
import food.entity.OrderDetail;

@Controller
@RequestMapping(value = "/admin/order/")
public class OrderAdminController {
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	AdminMailer mailer;
	
	@RequestMapping(value = "index")
	public String index(ModelMap model,HttpServletRequest request) {
		List<Order> list = orderDAO.getOrders();
		//model.addAttribute("orders",list);
		
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
	
		pagedListHolder.setPageSize(10);
		
		model.addAttribute("pagedListHolder",pagedListHolder);
		
		return "admin/order/index";
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
		return "admin/order/detail";
	}
	
	@RequestMapping(value = "status", params = {"orderId","status"})
	public String updateStatus(ModelMap model, @RequestParam("orderId") int orderId, @RequestParam("status") int status, HttpSession session) {
		orderDAO.updateStatus(orderId, status);
		return "redirect:/admin/order/index.htm";
	}
	@RequestMapping(value = "statusDaGiao", params = {"orderId","status","email"})
	public String DaGiao(ModelMap model, @RequestParam("orderId") int orderId, @RequestParam("status") int status,@RequestParam("email") String email, HttpSession session) {
		String from = "vinhvipvl@gmail.com";
		
		orderDAO.updateStatus(orderId, status);
		mailer.sendmailer(from, email, "Food Order", "Đơn hàng của bạn đã giao thành công");
		
		return "redirect:/admin/order/index.htm";
	}
}
