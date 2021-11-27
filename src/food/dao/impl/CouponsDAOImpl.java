package food.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.CouponsDAO;
import food.entity.Coupons;

@Transactional
public class CouponsDAOImpl implements CouponsDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Coupons> listCoupons() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Coupons> list = session.createQuery("FROM Coupons").list();
		return list;
	}

	@Override
	public boolean add(Coupons coupons) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(coupons);
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
	public boolean update(Coupons coupons) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(coupons);
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
	public boolean delete(Coupons coupons) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(coupons);
			t.commit();
			return true;
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}

		return false;
	}

	@Override
	public Coupons get(String id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Coupons WHERE couponsId = :couponsId";
		Query query = session.createQuery(hql);
		query.setString("couponsId", id);
		Coupons coupons = (Coupons) query.uniqueResult();
		return coupons;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Coupons> listPublicCoupons() {
		Session session = sessionFactory.getCurrentSession();
		List<Coupons> list = session.createQuery("FROM Coupons WHERE status = 0").list();
		return list;
	}

}
