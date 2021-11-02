package food.dao;

import java.util.List;

import food.entity.Food;

public interface FoodDAO {
	
	/**
	 * Lấy tất cả món ăn
	 */
	List<Food> listFoods();
	
	List<Food> listFoods(String keyword, int category, int filter, boolean getAll);
	
	List<Food> listFoodsInCategory(int category);
	
	Food getFood(int id);
	
	boolean insert(Food food);
	
	boolean update(Food food);
	
	boolean delete(Food food);
	
}
