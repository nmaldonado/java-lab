package uy.com.netlabs.dao.impl;


import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uy.com.netlabs.dao.ProductDao;
import uy.com.netlabs.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class ProductDaoImpl  implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Product product){
        entityManager.persist(product);
    }

    public void updateProduct(Product product){
        Product product1 = findById(product.getId());
        product1.setName(product.getName());
        product1.setCategory(product1.getCategory());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getId());
        entityManager.flush();
    }

    public void delete(Product product){
        if(exist(product.getId()))
            entityManager.remove(findById(product.getId()));
    }

    @SuppressWarnings("unchecked")
    public List<Product> list() {
        String hql="FROM Product as p ORDER BY p.name DESC";
        return (List<Product>) entityManager.createQuery(hql).getResultList();

    }

    @SuppressWarnings("unchecked")
    public Product findById(int id){
        return entityManager.find(Product.class,id);

    }

    public void deleteAll(){

    }

    public boolean exist(int id) {
        String hql ="FROM Product as p WHERE p.id=?";
        int count = entityManager.createQuery(hql).setParameter(1,id).getResultList().size();
        return count > 0 ? true : false;
    }
}
