package food.dao.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.RatedDAO;
import food.entity.Rated;
import food.utils.Constants;

@Transactional
public class RatedDAOImpl implements RatedDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insert(Rated rated) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(rated);
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
	public Rated getRated(int accountId, int foodId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Rated WHERE food.foodId = :foodId AND account.accountId = :accountId";
		Query query = session.createQuery(hql);
		query.setInteger("foodId", foodId);
		query.setInteger("accountId", accountId);
		Rated rated = (Rated) query.uniqueResult();
		return rated;
	}

	@Override
	public boolean update(Rated rated) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(rated);
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
	public boolean delete(Rated rated) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(rated);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rated> listRated(int foodId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Rated WHERE food.foodId = :foodId ORDER BY cmtTime DESC";
		Query query = session.createQuery(hql);
		query.setInteger("foodId", foodId);
		List<Rated> list = query.list();
		return list;
	}

	@Override
	public List<Rated> listRated(int foodId, int rateFilter) {
		List<Rated> list = listRated(foodId);

		switch (rateFilter) {
		case Constants.RATE_FILTER_BY_NEWEST:
			break;
		case Constants.RATE_FILTER_HIGHEST:
			list.sort(new Comparator<Rated>() {
				@Override
				public int compare(Rated a, Rated b) {
					return b.getStar() - a.getStar();
				}
			});
			break;
		case Constants.RATE_FILTER_5_STAR:
			list = filterByStar(list, 5);
			break;
		case Constants.RATE_FILTER_4_STAR:
			list = filterByStar(list, 4);
			break;
		case Constants.RATE_FILTER_3_STAR:
			list = filterByStar(list, 3);
			break;
		case Constants.RATE_FILTER_2_STAR:
			list = filterByStar(list, 2);
			break;
		case Constants.RATE_FILTER_1_STAR:
			list = filterByStar(list, 1);
			break;
		}

		return list;
	}

	private List<Rated> filterByStar(List<Rated> list, int star) {
		return list.stream().filter(rate -> rate.getStar() == star).collect(Collectors.toList());
	}

}
