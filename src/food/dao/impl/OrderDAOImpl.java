package food.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.OrderDAO;
import food.entity.Order;
import food.entity.OrderDetail;

@Transactional
public class OrderDAOImpl implements OrderDAO{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * Kiểm tra người dùng đã đặt món ăn này hay chưa (status = 2: đã nhận hàng)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean hasOrdered(int accountId, int foodId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderDetail WHERE order.account.accountId = :accountId AND food.foodId = :foodId AND order.status=2";
		Query query = session.createQuery(hql);
		query.setInteger("accountId", accountId);
		query.setInteger("foodId", foodId);
		List<OrderDetail> list = query.list();
		
		return list != null && list.size() > 0;
	}

	@Override
	public List<Order> getOrders(){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE status != 3 ORDER BY orderTime DESC";
		Query query = session.createQuery(hql);
		List<Order> list = new ArrayList<Order>();
		for(Object q:query.list()) {
			list.add((Order) q);
		}
		return list;
	}
	
	@Override
	public List<Order> getOrder(int accountId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE account.accountId = :accountId ORDER BY orderTime DESC";
		Query query = session.createQuery(hql);
		query.setParameter("accountId", accountId);
		List<Order> list = new ArrayList<Order>();
		for(Object q:query.list()) {
			list.add((Order) q);
		}
		return list;
	}
	
	@Override
	public List<OrderDetail> getOrdersDetail(int orderId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderDetail WHERE order.orderId = :orderId";
		Query query = session.createQuery(hql);
		query.setInteger("orderId", orderId);
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		for(Object q:query.list()) {
			list.add((OrderDetail) q);
		}
		return list;
	}
	@Override
	public boolean insertOrder(Order order) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(order);
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
	public boolean insertOrderDetail(OrderDetail orderDetail) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(orderDetail);
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
	public int updateStatus(int orderId, int status) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "UPDATE Order set status = :status "  + 
	             "WHERE orderId = :orderId";
		Query query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		query.setParameter("status", status);
		int result = query.executeUpdate();
		return result;
	}
}
