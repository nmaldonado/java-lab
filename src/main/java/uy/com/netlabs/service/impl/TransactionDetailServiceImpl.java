package uy.com.netlabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.netlabs.dao.TransactionDetailDao;
import uy.com.netlabs.exceptions.ErrorLowStockException;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.model.Transaction;
import uy.com.netlabs.model.TransactionDetail;
import uy.com.netlabs.service.TransactionDetailService;
import uy.com.netlabs.service.TransactionService;

import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Autowired
    private TransactionDetailDao transactionDetailDao;

    @Override
    public void registerTransactionDetail(TransactionDetail transactionDetail) {
        transactionDetailDao.save(transactionDetail);
    }

    @Override
    public TransactionDetail createTransactionDetail() {
        return transactionDetailDao.createTransactionDetail();
    }

    @Override
    public List<TransactionDetail> listAll() {
        return transactionDetailDao.list();
    }
}
