package food.dao;

public interface OrderDAO {
	boolean hasOrdered(int accountId, int foodId);
}
