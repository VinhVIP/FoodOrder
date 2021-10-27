package food.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.CartDAO;
import food.entity.Cart;

@Transactional
public class CartDAOImpl implements CartDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insert(Cart cart) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(cart);
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
	public Cart get(int accountId, int foodId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Cart WHERE account.accountId = :accountId AND food.foodId = :foodId";
		Query query = session.createQuery(hql);
		query.setInteger("accountId", accountId);
		query.setInteger("foodId", foodId);
		Cart cart = (Cart) query.uniqueResult();
		return cart;
	}

}
