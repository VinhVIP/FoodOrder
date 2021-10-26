package food.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.RatedDAO;
import food.entity.Rated;

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
		}catch(Exception e) {
			e.printStackTrace();
			t.rollback();
		}finally {
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
	
	
}
