package food.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import food.dao.CartDAO;
import food.dao.OrderDAO;
import food.entity.Account;
import food.entity.Cart;
import food.entity.Coupons;
import food.entity.Order;
import food.entity.OrderDetail;
import food.entity.OrderDetailKey;

@Controller
@RequestMapping("/cart/")
public class CartController {
	@Autowired
	SessionFactory factory;

	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private OrderDAO orderDAO;

	@RequestMapping(value = "index")
	public String cart(ModelMap model, HttpSession session) {
		float total = 0;
		Account user = (Account) session.getAttribute("account");
		List<Cart> list = cartDAO.getCart(user.getAccountId());

		for (Cart c : list) {
			total += c.getQuantity() * c.getFood().getPrice();
		}

		model.addAttribute("cart", list);
		model.addAttribute("total", total);

		return "cart/index";
	}

	@RequestMapping(value = "delete/{id}.htm", method = RequestMethod.GET)
	public String delete(ModelMap model, HttpSession session, @PathVariable("id") String id) {
		Account user = (Account) session.getAttribute("account");
		cartDAO.delete(cartDAO.get(user.getAccountId(), Integer.parseInt(id)));
		List<Cart> list = cartDAO.getCart(user.getAccountId());

		model.addAttribute("cart", list);

		return "redirect:/cart/index.htm";
	}

	@RequestMapping(value = "qty/plus", params = { "id_food", "qty" })
	public String qtyPlus(ModelMap model, @RequestParam("id_food") int id, @RequestParam("qty") int qty) {
		cartDAO.updateQty(id, qty + 1);

		return "redirect:/cart/index.htm";
	}

	@RequestMapping(value = "qty/minus", params = { "id_food", "qty" })
	public String qtyMinus(ModelMap model, @RequestParam("id_food") int id, @RequestParam("qty") int qty) {
		cartDAO.updateQty(id, qty - 1);
		return "redirect:/cart/index.htm";
	}

	@RequestMapping(value = "discount", params = { "total" })
	public String btnDiscount(ModelMap model, @ModelAttribute("coupon") Coupons coupon, HttpSession session,
			HttpServletRequest request, @RequestParam("total") float total) {
		Account user = (Account) session.getAttribute("account");
		String voucher = request.getParameter("couponsId");

		Coupons coupons = cartDAO.getCoupon(voucher);

		if (coupons == null) {
			model.addAttribute("message", "Mã khuyến mãi không hợp lệ!");
		} else {
			System.out.println(coupons.getExpiredTime());

			if (coupons.getAmount() <= 0) {
				model.addAttribute("message", "Mã khuyến mãi đã hết!");
			} else if (new Date().after(coupons.getExpiredTime())) {
				model.addAttribute("message", "Mã khuyến mãi đã quá hạn sử dụng!");
			} else {
				if (coupons.getType() == 0) {
					total = Math.max(total-coupons.getValue(), 0);
				} else {
					total = total * (coupons.getValue() * 1.0f / 100);
				}
			}
		}

		model.addAttribute("total", total);
		model.addAttribute("coupon", coupons);
		model.addAttribute("cart", cartDAO.getCart(user.getAccountId()));

		return "cart/index";
	}

	@RequestMapping(value = "checkout", params = "coupon")
	public String checkout(ModelMap model, HttpSession session, @RequestParam("coupon") String couponId) {
		Account user = (Account) session.getAttribute("account");
		List<Cart> list = cartDAO.getCart(user.getAccountId());
		Coupons coupon = new Coupons();
		Order order = new Order();

		coupon = cartDAO.getCoupon(couponId);
		cartDAO.updateCoupon(coupon.getCouponsId() + "", coupon.getAmount() - 1);
		order.setCoupons(coupon);

		order.setAccount(user);

		Calendar c1 = Calendar.getInstance();
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		order.setOrderTime(out);

		c1.setTime(out);
		c1.add(Calendar.MINUTE, 30);
		order.setDeliveryTime(c1.getTime());
		order.setStatus(0);

		orderDAO.insertOrder(order);
		

		for (Cart c : list) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderDetailId(new OrderDetailKey(order.getOrderId(), c.getFood().getFoodId()));
			orderDetail.setFood(c.getFood());
			orderDetail.setOrder(order);
			orderDetail.setAmount(c.getQuantity());
			orderDetail.setPrice(c.getFood().getPrice());
			orderDAO.insertOrderDetail(orderDetail);
		}

		cartDAO.removeCart(user.getAccountId());

		return "redirect:/order/index.htm";
	}

	@RequestMapping(value = "checkout")
	public String checkout2(ModelMap model, HttpSession session) {
		Account user = (Account) session.getAttribute("account");
		List<Cart> list = cartDAO.getCart(user.getAccountId());
		Order order = new Order();

		order.setAccount(user);

		Calendar c1 = Calendar.getInstance();
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		order.setOrderTime(out);

		c1.setTime(out);
		c1.add(Calendar.MINUTE, 30);
		order.setDeliveryTime(c1.getTime());
		order.setStatus(0);

		orderDAO.insertOrder(order);

		for (Cart c : list) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrderDetailId(new OrderDetailKey(order.getOrderId(), c.getFood().getFoodId()));
			orderDetail.setFood(c.getFood());
			orderDetail.setOrder(order);
			orderDetail.setAmount(c.getQuantity());
			orderDetail.setPrice(c.getFood().getPrice());
			orderDAO.insertOrderDetail(orderDetail);
		}

		cartDAO.removeCart(user.getAccountId());

		return "redirect:/order/index.htm";
	}
}
