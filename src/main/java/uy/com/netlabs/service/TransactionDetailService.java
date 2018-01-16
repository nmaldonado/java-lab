package uy.com.netlabs.service;

import uy.com.netlabs.model.TransactionDetail;

import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */
public interface TransactionDetailService {

    void registerTransactionDetail(TransactionDetail transactionDetail);
    TransactionDetail createTransactionDetail();
    List<TransactionDetail> listAll();
}
