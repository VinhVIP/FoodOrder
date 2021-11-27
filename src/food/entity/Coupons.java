package food.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="coupons")
public class Coupons {

	@Id
	@Column(name="id_coupons")
	private String couponsId;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="type")
	private int type;
	
	@Column(name="value")
	private int value;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="expired_time")
	private Date expiredTime;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="status")
	private int status;
	
	
	@OneToMany(mappedBy = "coupons", fetch = FetchType.EAGER)
	private Collection<Order> orders;

	
	// Setter and getter
	public String getCouponsId() {
		return couponsId;
	}

	public void setCouponsId(String couponsId) {
		this.couponsId = couponsId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}
	
}
