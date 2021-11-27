package food.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.CartDAO;
import food.entity.Cart;
import food.entity.Coupons;

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
	public boolean delete(Cart cart) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(cart);
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

	@Override
	public List<Cart> getCart(int accountId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Cart WHERE account.accountId = :accountId";
		Query query = session.createQuery(hql);
		query.setInteger("accountId", accountId);
		List<Cart> list = new ArrayList<Cart>();
		//list =  session.createQuery(hql).list();
		for(Object q:query.list()) {
			list.add((Cart) q);
		}
		return list;
	}

	@Override
	public int updateQty(int foodId,int qty) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Cart set quantity = :qty " + "WHERE food.foodId = :foodId";
		Query query = session.createQuery(hql);
		query.setParameter("foodId", foodId);
		query.setParameter("qty", qty);
		int result = query.executeUpdate();
		return result;
	}
	@Override
	public Coupons getCoupon(String couponId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Coupons"+" WHERE couponsId = :couponId";
		Query query = session.createQuery(hql);
		query.setParameter("couponId", couponId);
		Coupons coupon = (Coupons) query.uniqueResult();
		return coupon;
	}
	
	@Override
	public int removeCart(int accountId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM Cart "  + 
	             "WHERE account.accountId = :accountId";
		Query query = session.createQuery(hql);
		query.setParameter("accountId", accountId);
		int result = query.executeUpdate();
		return result;
	}

	@Override
	public int updateCoupon(String couponId, int amount) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Coupons set amount = :amount " + "WHERE couponsId = :couponId";
		Query query = session.createQuery(hql);
		query.setParameter("couponId", couponId);
		query.setParameter("amount", amount);
		int result = query.executeUpdate();
		return result;
	}
}
