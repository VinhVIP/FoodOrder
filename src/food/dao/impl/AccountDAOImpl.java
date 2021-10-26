package food.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import food.dao.AccountDAO;
import food.entity.Account;

@Transactional
public class AccountDAOImpl implements AccountDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Account getAccount(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Account WHERE accountId = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		Account account = (Account) query.uniqueResult();
		return account;
	}

}
