package food.bean;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class FoodBean {

	@NotBlank(message = "Tên món ăn không được bỏ trống!")
	private String name;

	private String detail;
	
	private int price;
	
	private MultipartFile[] images;
	
	private int type;
	
	private int status;

	private int category;
	
	
	private String[] imagePath;
	
	
	
	
	public String[] getImagePath() {
		return imagePath;
	}

	public void setImagePath(String[] imagePath) {
		this.imagePath = imagePath;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
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

	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
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

}
