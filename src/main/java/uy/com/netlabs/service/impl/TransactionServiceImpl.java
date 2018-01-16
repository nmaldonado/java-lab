package uy.com.netlabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.netlabs.dao.ProductDao;
import uy.com.netlabs.dao.TransactionDao;
import uy.com.netlabs.dao.TransactionDetailDao;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.model.Transaction;
import uy.com.netlabs.model.TransactionDetail;
import uy.com.netlabs.service.TransactionService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by nicolas on 15/01/18.
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionDetailDao transactionDetail;

    @Autowired
    private ProductDao productDao;


    @Override
    public Transaction registerTransaction(Product product, int quantity) {


        int discountToApply= product.getCategory().getDiscountPercentage(); //this must consume the GDC

        Transaction transaction = new Transaction();
        TransactionDetail transactionDetail1 = transactionDetail.createTransactionDetail();
        transactionDetail1.setDiscountApplied(discountToApply);
        transactionDetail1.setQuantity(quantity);
        transactionDetail1.setProduct(product);


        product.setStock(product.getStock()-quantity);


        transaction.addTransactionDetails(transactionDetail1);
        transaction.setDate(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        transaction.setTotalPrice(((product.getPrice()/100)*discountToApply)*quantity);

        transactionDao.save(transaction);

        transactionDetail1.setTransactionId(transaction.getId());

        transactionDetail.save(transactionDetail1);

        return transaction;
    }


    @Override
    public List<Transaction> listTransactions() {
        return transactionDao.list();
    }


    @Override
    public void save(Transaction transaction) {
        transactionDao.save(transaction);
    }
}
