package food.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="account")
public class Account {

	@Id
	@GeneratedValue
	@Column(name="id_account")
	private int accountId;
	
	@Column(name="email")
	private String email;

	@Column(name="phone")
	private String phone;
	
	@Column(name="name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="address")
	private String address;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="status")
	private int status;
	
	@Column(name="password")
	private String password;
	
	@OneToMany(mappedBy = "account", fetch=FetchType.EAGER)
	private Collection<Rated> rateds;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private Collection<Order> orders;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private Collection<Cart> carts;

	
	// Setter and getter
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rated> getRateds() {
		return rateds;
	}

	public void setRateds(Collection<Rated> rateds) {
		this.rateds = rateds;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Cart> carts) {
		this.carts = carts;
	}


	
	

}
