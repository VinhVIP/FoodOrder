package food.utils;

import food.entity.Food;
import food.entity.Rated;

public class Constants {

	public static final int FPP = 6; // Foods Per Page

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

}
