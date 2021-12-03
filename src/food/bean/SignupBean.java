package food.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class SignupBean {
	@NotBlank(message = "Họ và tên không được bỏ trống!")
	private String name;
	
	@NotBlank(message = "Họ và tên không được bỏ trống!")
	@Email(message = "Email không đúng định dạng!")
	private String email;
	
	@NotBlank(message = "Số điện thoại không được bỏ trống!")
	private String phone;
	
	@NotBlank(message = "Địa chỉ liên hệ không được bỏ trống!")
	private String address;
}
