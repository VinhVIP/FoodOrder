package food.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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


}
