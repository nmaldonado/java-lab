package uy.com.netlabs.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uy.com.netlabs.dao.TransactionDao;
import uy.com.netlabs.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Transactional
@Repository
public class TransactionDaoImpl implements TransactionDao {



    @PersistenceContext
    private EntityManager entityManager;



    public void save(Transaction transaction) {
                    entityManager.persist(transaction);
    }

    public void update(Transaction transaction) {
        Transaction transaction1 = findById(transaction.getId());
        transaction1.setDate(transaction.getDate());
        transaction1.setTotalPrice(transaction.getTotalPrice());
        entityManager.flush();
    }


    @SuppressWarnings("unchecked")
    public List<Transaction> list() {
        String hql ="From Transaction as tr ORDER BY tr.id";
        return (List<Transaction>) entityManager.createQuery(hql).getResultList();
    }

    @SuppressWarnings("unchecked")
    public Transaction findById(int id) {
        return entityManager.find(Transaction.class, id);
    }

    public boolean transactionExists(Transaction transaction) {
        String hql="From Transaction as t where t.id=?";
        int count = entityManager.createQuery(hql).setParameter(1,transaction.getId()).getResultList().size();
        return count > 0 ? true : false;

    }

    @Override
    public void deleteAll() {

    }


}
