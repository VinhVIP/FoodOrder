package food.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue
	@Column(name = "id_food")
	private int foodId;

	@Column(name = "name")
	private String name;

	@Column(name = "detail")
	private String detail;

	@Column(name = "price")
	private int price;

	@Column(name = "images")
	private String images;

	@Column(name = "type")
	private int type;

	@Column(name = "status")
	private int status;

	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;
	
	@OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
	private Collection<Rated> rateds;

	@OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
	private Collection<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
	private Collection<Cart> carts;
	
	

	// Setter and getter
	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Collection<Rated> getRateds() {
		return rateds;
	}

	public void setRateds(Collection<Rated> rateds) {
		this.rateds = rateds;
	}

	public Collection<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Collection<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Collection<Cart> carts) {
		this.carts = carts;
	}

}
