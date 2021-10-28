package food.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import food.dao.OrderDAO;
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

}
