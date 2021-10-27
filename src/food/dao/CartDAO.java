package food.dao;

import food.entity.Cart;

public interface CartDAO {
	
	boolean insert(Cart cart);
	
	Cart get(int accountId, int foodId);

}
