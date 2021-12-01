package food.bean;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class AccountBean {
	
	@NotBlank(message = "Họ và tên không được bỏ trống!")
	private String name;
	
	@NotBlank(message = "Email không được bỏ trống!")
	private String email;
	
	@NotBlank(message = "Số điện thoại không được bỏ trống!")
	private String phone;
	
	@NotBlank(message = "Địa chỉ liên hệ không được bỏ trống!")
	private String address;
	
	private MultipartFile avatar;

	@NotBlank(message = "Mật khẩu không được bỏ trống!")
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
