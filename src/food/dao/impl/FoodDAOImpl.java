package food.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.FoodDAO;
import food.entity.Food;
import food.entity.Rated;
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
		List<Food> list = session.createQuery("FROM Food ORDER BY foodId DESC").list();
		return list;
	}

	@Override
	public List<Food> listFoodsInCategory(int category) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Food> list = session.createQuery("FROM Food WHERE category.categoryId=" + category).list();
		return list;
	}

	public List<Food> listFoods(String keyword, int category, int filter, boolean getAll) {
		List<Food> list = new ArrayList<Food>();

		if (!getAll) {
			for (Food f : listFoods()) {
				if (f.getStatus() == 0)
					list.add(f);
			}
		} else {
			list = listFoods();
		}

		if (keyword != null && keyword.trim().length() > 0) {
			list = filterByKeyword(list, keyword);
		}

		if (category != -1) {
			list = filterByCategory(list, category);
		}

		if (filter != -1) {
			list = filterByCondition(list, filter);
		}

		return list;
	}

	public List<Food> filterByKeyword(List<Food> allFoods, String keyword) {
		List<Food> list = new ArrayList<Food>();

		for (Food food : allFoods) {
			if (food.getName().toLowerCase().contains(keyword.toLowerCase())) {
				list.add(food);
			}
		}

		return list;
	}

	public List<Food> filterByCategory(List<Food> allFoods, int category) {
		List<Food> list = new ArrayList<Food>();

		for (Food food : allFoods) {
			if (food.getCategory().getCategoryId() == category) {
				list.add(food);
			}
		}

		return list;
	}

	public List<Food> filterByCondition(List<Food> list, int filter) {

		switch (filter) {
		case Constants.FILTER_BY_NEWEST:
			break;
		case Constants.FILTER_BY_OLDEST:
			Collections.reverse(list);
			break;
		case Constants.FILTER_BY_RATING:
			list.sort(new Comparator<Food>() {
				@Override
				public int compare(Food a, Food b) {
					double ra = 0, rb = 0;

					if (a.getRateds() != null && a.getRateds().size() > 0) {
						for (Rated rated : a.getRateds()) {
							ra += rated.getStar();
						}
						ra /= a.getRateds().size();
					}

					if (b.getRateds() != null && b.getRateds().size() > 0) {
						for (Rated rated : b.getRateds()) {
							rb += rated.getStar();
						}

						rb /= b.getRateds().size();
					}

					if (ra < rb)
						return 1;
					else if (ra > rb)
						return -1;
					return 0;
				}
			});
			break;
		case Constants.FILTER_BY_POPULAR:
			list.sort(new Comparator<Food>() {
				@Override
				public int compare(Food a, Food b) {
					return b.getOrderDetails().size() - a.getOrderDetails().size();
				}
			});
			break;
		}

		return list;
	}

	@Override
	public Food getFood(int id) {
		Session session = sessionFactory.getCurrentSession();
		Food food = (Food) session.createQuery("FROM Food WHERE foodId=" + id).uniqueResult();
		return food;
	}

	@Override
	public boolean insert(Food food) {

		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(food);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean update(Food food) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(food);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean delete(Food food) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(food);
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
		} finally {
			session.close();
		}
		return false;
	}

}
