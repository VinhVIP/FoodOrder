package food.dao;

import java.util.List;

import food.entity.Coupons;

public interface CouponsDAO {
	
	List<Coupons> listCoupons();
	
	List<Coupons> listPublicCoupons();
	
	Coupons get(String id);
	
	boolean add(Coupons coupons);

	boolean update(Coupons coupons);

	boolean delete(Coupons coupons);
}
