package uy.com.netlabs.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uy.com.netlabs.dao.CategoryDao;
import uy.com.netlabs.model.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }


    @Override
    public List<Category> list(){
        String hql="FROM Category C ORDER BY C.name DESC";
        return (List<Category>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public boolean exists(int id) {
        String hql ="FROM Category C WHERE C.category_id=?";
        int count = entityManager.createQuery(hql).setParameter(1, id).getResultList().size();
        return count > 0 ? true : false;

    }

    @Override
    public void updateDiscountPercentage(int id, int percentage) {
        if (exists(id)){
            Category category1 = findById(id);
            category1.setDiscountPercentage(percentage);
            entityManager.flush();
        }
    }
}
