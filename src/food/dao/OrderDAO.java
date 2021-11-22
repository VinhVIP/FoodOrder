package food.dao;

import java.util.List;

import food.entity.Order;
import food.entity.OrderDetail;

public interface OrderDAO {
	boolean hasOrdered(int accountId, int foodId);
	
	List<Order> getOrders();
	
	List<Order> getOrder(int accountId);
	
	List<OrderDetail> getOrdersDetail(int orderId);
	
	boolean insertOrder(Order order);
	
	boolean insertOrderDetail(OrderDetail orderDetail);
	
	int updateStatus(int orderId, int status);
}
