package food.dao;

import java.util.List;

import food.entity.Category;

public interface CategoryDAO {

	List<Category> listCategories();

	Category getCategory(int id);
	
	boolean insert(Category category);
	
	boolean update(Category category);
	
	boolean delete(Category category);
	
	Category getCategoryByName(String name);

}
