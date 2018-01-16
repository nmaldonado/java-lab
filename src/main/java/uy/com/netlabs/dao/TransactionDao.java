package uy.com.netlabs.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import uy.com.netlabs.model.Transaction;

import java.util.List;
import java.util.Set;



public interface TransactionDao {
    void save(Transaction transaction);
    void update(Transaction transaction);
    void deleteAll();
    List<Transaction> list();
    Transaction findById(int id);


}
