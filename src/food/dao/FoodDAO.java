package food.dao;

import java.util.List;

import food.entity.Food;

public interface FoodDAO {
	
	List<Food> listFoods();
	
	List<Food> listFoodsInCategory(int categoryId);
	
	List<Food> listFoodsInCategoryByPage(int categoryId, int page);
	
	List<Food> listNewestFood(int page);
	
	List<Food> listHighRatingFood(int page);
	
	List<Food> listMostBuyFood(int page);
	
	Food getFood(int id);
	
}
