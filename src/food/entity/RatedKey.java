package food.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RatedKey implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="id_food")
	private int foodId;
	
	@Column(name="id_account")
	private int accountId;

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public RatedKey(int accountId, int foodId) {
		super();
		this.foodId = foodId;
		this.accountId = accountId;
	}

	public RatedKey() {
		super();
	}
	
}
