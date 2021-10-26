package food.dao;

import java.util.List;

import food.entity.Category;

public interface CategoryDAO {

	List<Category> listCategories();

	Category getCategory(int id);

}
