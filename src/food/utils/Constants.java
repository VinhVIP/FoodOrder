package food.utils;

public class Constants {

	public static final int FPP = 4; // Foods Per Page

	/**
	 * 
	 * @param size
	 * @param RPP  Records Per Page: Số record trên mỗi trang
	 * @return Trả về trang có số thứ tự lớn nhất
	 */
	public static int getMaxPage(int size, int RPP) {
		return size / RPP + (size % RPP != 0 ? 1 : 0);
	}

}
