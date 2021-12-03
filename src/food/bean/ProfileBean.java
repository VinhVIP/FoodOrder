package food.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ProfileBean {
	
	@NotBlank(message = "Họ và tên không được bỏ trống!")
	private String name;
	
	@NotBlank(message = "Email không được bỏ trống!")
	@Email(message = "Email không đúng định dạng!")
	private String email;
	
	@NotBlank(message = "Số điện thoại không được bỏ trống!")
	private String phone;
	
	@NotBlank(message = "Địa chỉ liên hệ không được bỏ trống!")
	private String address;
	
	private MultipartFile avatar;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
	
}
