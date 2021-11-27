package food.dao;

import java.util.List;

import food.entity.Cart;
import food.entity.Coupons;

public interface CartDAO {

	boolean insert(Cart cart);

	boolean delete(Cart cart);

	Coupons getCoupon(String coupon);

	int updateQty(int foodId, int qty);

	Cart get(int accountId, int foodId);

	List<Cart> getCart(int accountId);

	int removeCart(int accountId);

	int updateCoupon(String coupon, int amount);

}
