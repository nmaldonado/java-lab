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
import uy.com.netlabs.dao.TransactionDetailDao;
import uy.com.netlabs.model.TransactionDetail;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Transactional
@Repository
public class TransactionDetailDaoImpl implements TransactionDetailDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(TransactionDetail transactionDetail) {
        entityManager.persist(transactionDetail);
    }

    @Override
    public void update(TransactionDetail transactionDetail) {
        if(existTransactionDetail(transactionDetail.getId())){
            TransactionDetail transactionDetail1 = findByTransactionId(transactionDetail.getId());
            transactionDetail1.setDiscountApplied(transactionDetail.getDiscountApplied());
            transactionDetail1.setProduct(transactionDetail.getProduct());
            transactionDetail1.setQuantity(transactionDetail.getQuantity());
            entityManager.flush();
        }
    }

    @Override
    public List<TransactionDetail> list() {
        String hql ="FROM TransactionDetail TD ORDER BY TD.transaction_detail_id";
        return (List<TransactionDetail>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public TransactionDetail findByTransactionId(int transactionId) {
        return entityManager.find(TransactionDetail.class, transactionId);
    }

    @Override
    public boolean existTransactionDetail(int id) {
        String hql = "FROM TransactionDetail as T WHERE T.transaction_detail_id=?";
        int count = entityManager.createQuery(hql).setParameter(1,id).getResultList().size();
        return count > 0 ? true : false;
    }

    @Override
    public TransactionDetail createTransactionDetail() {
        TransactionDetail transactionDetail =  new TransactionDetail();
        return  transactionDetail;
    }
}
