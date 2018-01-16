package uy.com.netlabs.service;

import uy.com.netlabs.model.Product;
import uy.com.netlabs.model.Transaction;

import java.util.List;

/**
 * Created by nicolas on 15/01/18.
 */
public interface TransactionService {

    void save(Transaction transaction);
    Transaction registerTransaction(Product product, int quantity);
    List<Transaction> listTransactions();
}
