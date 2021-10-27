package food.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import food.dao.CategoryDAO;
import food.entity.Category;

@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> listCategories() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Category> list = session.createQuery("FROM Category").list();
		return list;
	}

	@Override
	public Category getCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Category WHERE categoryId = :id";
		Query query = session.createQuery(hql);
		query.setInteger("id", id);
		Category category = (Category) query.uniqueResult();
		return category;
	}

	@Override
	public boolean insert(Category category) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
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
	public boolean update(Category category) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(category);
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
	public boolean delete(Category category) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(category);
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
	public Category getCategoryByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Category WHERE name = :name";
		Query query = session.createQuery(hql);
		query.setString("name", name);
		Category category = (Category) query.uniqueResult();
		return category;
	}

}
