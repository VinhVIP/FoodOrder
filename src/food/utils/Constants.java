package food.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import food.entity.Account;
import food.entity.Cart;
import food.entity.Food;
import food.entity.Rated;
import org.apache.commons.codec.digest.DigestUtils;

public class Constants {

	public static final int FPP = 6; // Foods Per Page
	public static final int RPP = 2; // Ratings Per Page
	public static final int APP = 3; // Ratings Per Page

	public static final int FILTER_BY_NEWEST = 1;
	public static final int FILTER_BY_OLDEST = 2;
	public static final int FILTER_BY_RATING = 3;
	public static final int FILTER_BY_POPULAR = 4;
	
	public static final int RATE_FILTER_BY_NEWEST = 1;
	public static final int RATE_FILTER_HIGHEST = 2;
	public static final int RATE_FILTER_5_STAR = 3;
	public static final int RATE_FILTER_4_STAR = 4;
	public static final int RATE_FILTER_3_STAR = 5;
	public static final int RATE_FILTER_2_STAR = 6;
	public static final int RATE_FILTER_1_STAR = 7;
	
	public static HashMap<Character, Character> mapChar;
	static {
		mapChar = new HashMap<>();
		initMap();
	}
	
	public static String md5(String data) {
		return DigestUtils.md5Hex(data);
	}
	
	public static boolean isSameMD5(String data1, String data2) {
		return md5(data1).equals(md5(data2));
	}
	
	public static String randomCode(int len) {
		Random r = new Random();
		String s = "";
		for(int i=0; i<len; i++) {
			s += Math.abs(r.nextInt()%10);
		}
		return s;
	}

	/**
	 * 
	 * @param size
	 * @param RPP  Records Per Page: Số record trên mỗi trang
	 * @return Trả về trang có số thứ tự lớn nhất
	 */
	public static int getMaxPage(int size, int RPP) {
		return size / RPP + (size % RPP != 0 ? 1 : 0);
	}

	public static String getBanner(String images) {
		String firstImage = "";
		if (images == null || images.trim().length() == 0) {
			firstImage = "resources/img/food.jpg";
		} else {
			firstImage = images.split("\\s+")[0];
		}
		return firstImage;
	}

	public static String getImageAt(String images, int index) {
		if (images == null || images.trim().length() == 0)
			return "";
		String[] s = images.split("\\s+");
		if (index >= s.length)
			return "";
		return s[index];
	}

	public static double getAvgStar(Food food) {
		double avgStar = 0;
		for (Rated rated : food.getRateds()) {
			avgStar += rated.getStar();
		}
		if (food.getRateds().size() > 0)
			avgStar /= food.getRateds().size();
		return avgStar;
	}

	public static String getAvgStarString(Food food) {
		return String.format("%.1f", getAvgStar(food));
	}

	public static String getShortDetail(String detail) {
		if (detail.length() > 80) {
			detail = detail.substring(0, 80);
			detail += "...";
		}
		return detail;
	}

	public static boolean hasOrderedFood(Account account, int foodId) {
		if (account == null)
			return false;
		for (Cart c : account.getCarts()) {
			if (c.getFood().getFoodId() == foodId) {
				return true;
			}
		}
		return false;
	}

	public static int adminPanelIndex(String url) {
		String[] path = { "admin/category", "admin/food", "admin/mail", "admin/order", "admin/coupons",
				"admin/account" };
		for (int i = 0; i < path.length; i++) {
			if (url.toLowerCase().contains(path[i].toLowerCase())) {
				return i;
			}
		}
		return -1;
	}
	
	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String currentTime = df.format(new Date());
		
		return currentTime;
	}

	public static String rewriteFileName(String name) {
		String res = "";
		char c;
		for (int i = 0; i < name.length(); i++) {
			c = name.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.') {
				res += c;
			} else if (mapChar.containsKey(c)) {
				res += mapChar.get(c);
			} else {
				res += '_';
			}
		}
		return res;
	}

	public static void initMap() {
		mapChar.put('á', 'a');
		mapChar.put('à', 'a');
		mapChar.put('ã', 'a');
		mapChar.put('ạ', 'a');
		
		mapChar.put('ă', 'a');
		mapChar.put('ắ', 'a');
		mapChar.put('ằ', 'a');
		mapChar.put('ẳ', 'a');
		mapChar.put('ẵ', 'a');
		mapChar.put('ặ', 'a');

		mapChar.put('â', 'a');
		mapChar.put('ấ', 'a');
		mapChar.put('ầ', 'a');
		mapChar.put('ẩ', 'a');
		mapChar.put('ẫ', 'a');
		mapChar.put('ậ', 'a');

		mapChar.put('đ', 'd');

		mapChar.put('é', 'e');
		mapChar.put('è', 'e');
		mapChar.put('ẻ', 'e');
		mapChar.put('ẽ', 'e');
		mapChar.put('ẹ', 'e');

		mapChar.put('ê', 'e');
		mapChar.put('ế', 'e');
		mapChar.put('ề', 'e');
		mapChar.put('ể', 'e');
		mapChar.put('ễ', 'e');
		mapChar.put('ệ', 'e');

		mapChar.put('í', 'i');
		mapChar.put('ì', 'i');
		mapChar.put('ỉ', 'i');
		mapChar.put('ĩ', 'i');
		mapChar.put('ị', 'i');

		mapChar.put('ó', 'o');
		mapChar.put('ò', 'o');
		mapChar.put('ỏ', 'o');
		mapChar.put('õ', 'o');
		mapChar.put('ọ', 'o');

		mapChar.put('ô', 'o');
		mapChar.put('ố', 'o');
		mapChar.put('ồ', 'o');
		mapChar.put('ổ', 'o');
		mapChar.put('ỗ', 'o');
		mapChar.put('ộ', 'o');

		mapChar.put('ơ', 'o');
		mapChar.put('ớ', 'o');
		mapChar.put('ờ', 'o');
		mapChar.put('ở', 'o');
		mapChar.put('ỡ', 'o');
		mapChar.put('ợ', 'o');

		mapChar.put('ú', 'u');
		mapChar.put('ù', 'u');
		mapChar.put('ủ', 'u');
		mapChar.put('ũ', 'u');
		mapChar.put('ụ', 'u');

		mapChar.put('ư', 'u');
		mapChar.put('ứ', 'u');
		mapChar.put('ừ', 'u');
		mapChar.put('ử', 'u');
		mapChar.put('ữ', 'u');
		mapChar.put('ự', 'u');
	}

}
