package food.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

	@EmbeddedId
	private CartKey cartId;	
	
	@Column(name="quantity")
	private int quantity;
	
	
	@ManyToOne
	@MapsId("id_food")
	@JoinColumn(name="id_food")
	private Food food;
	
	@ManyToOne
	@MapsId("id_account")
	@JoinColumn(name="id_account")
	private Account account;

	
	// Setter and getter
	public CartKey getCartId() {
		return cartId;
	}

	public void setCartId(CartKey cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
