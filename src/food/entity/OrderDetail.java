package food.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class OrderDetail {

	@EmbeddedId
	private OrderDetailKey orderDetailId;
		
	@Column(name="amount")
	private int amount;
	
	@Column(name="price")
	private int price;
	
	@ManyToOne
	@MapsId("id_food")
	@JoinColumn(name="id_food")
	private Food food;
	
	@ManyToOne
	@MapsId("id_order")
	@JoinColumn(name="id_order")
	private Order order;

	
	// Setter and getter
	public OrderDetailKey getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(OrderDetailKey orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
