package uy.com.netlabs.dao;

import org.springframework.stereotype.Repository;
import uy.com.netlabs.model.TransactionDetail;

import java.util.List;
import java.util.Set;


public interface TransactionDetailDao {

    void save(TransactionDetail transactionDetail);
    void update(TransactionDetail transactionDetail);
    List<TransactionDetail> list();
    TransactionDetail findByTransactionId(int transactionId);
    TransactionDetail createTransactionDetail();
    boolean existTransactionDetail(int id);
}
