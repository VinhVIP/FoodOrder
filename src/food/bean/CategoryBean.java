package food.bean;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CategoryBean {
	
	@NotBlank(message = "Tên danh mục không được bỏ trống!")
	private String name;
	
	private CommonsMultipartFile logo;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CommonsMultipartFile getLogo() {
		return logo;
	}
	public void setLogo(CommonsMultipartFile logo) {
		this.logo = logo;
	}

}
