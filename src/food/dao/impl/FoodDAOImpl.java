package food.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import food.dao.FoodDAO;
import food.entity.Food;
import food.utils.Constants;

@Transactional
public class FoodDAOImpl implements FoodDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Food> listFoods() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Food> list = session.createQuery("FROM Food").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Food> listFoodsInCategory(int categoryId) {
		Session session = sessionFactory.getCurrentSession();
		List<Food> list = session.createQuery("FROM Food WHERE category.categoryId=" + categoryId).list();
		return list;
	}

	@Override
	public Food getFood(int id) {
		Session session = sessionFactory.getCurrentSession();
		Food food = (Food) session.createQuery("FROM Food WHERE foodId=" + id).uniqueResult();
		return food;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Food> listFoodsInCategoryByPage(int categoryId, int page) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Food WHERE category.categoryId = :categoryId";
		Query query = session.createQuery(hql);
		query.setInteger("categoryId", categoryId);

		query.setFirstResult((page - 1) * Constants.FPP);
		query.setMaxResults(Constants.FPP);

		List<Food> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Food> listNewestFood(int page) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Food ORDER BY foodId DESC";
		Query query = session.createQuery(hql);

		query.setFirstResult((page - 1) * Constants.FPP);
		query.setMaxResults(Constants.FPP);

		List<Food> list = query.list();
		return list;
	}

	@Override
	public List<Food> listHighRatingFood(int page) {
		return null;
	}

	@Override
	public List<Food> listMostBuyFood(int page) {
		// TODO Auto-generated method stub
		return null;
	}

}