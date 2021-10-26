package food.dao;

import food.entity.Rated;

public interface RatedDAO {

	boolean insert(Rated rated);
	
	boolean update(Rated rated);
	
	boolean delete(Rated rated);
	
	Rated getRated(int accountId, int foodId);
	
}
