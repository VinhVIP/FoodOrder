package food.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue
	@Column(name="id_order")
	private int orderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_time")
	private Date orderTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delivery_time")
	private Date deliveryTime;
	
	@Column(name="status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="id_account")
	private Account account;

	@ManyToOne
	@JoinColumn(name="id_coupons")
	private Coupons coupons;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;

	
	// Setter and getter
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	
}
