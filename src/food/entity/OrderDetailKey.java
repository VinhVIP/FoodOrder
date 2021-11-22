package food.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="id_order")
	private int orderId;
	
	@Column(name="id_food")
	private int foodId;
	
	public OrderDetailKey() {
		
	}
	
	public OrderDetailKey(int orderId, int foodId) {
		super();
		this.orderId = orderId;
		this.foodId = foodId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	
}
