package uy.com.netlabs.Controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import uy.com.netlabs.Controllers.TransactionDetailController;
import uy.com.netlabs.dao.TransactionDetailDao;
import uy.com.netlabs.model.TransactionDetail;

import java.util.Set;

@Controller
public class TransactionDetailControllerImpl implements TransactionDetailController {

    @Autowired
    TransactionDetailDao transactionDetailDao;


    public Set<TransactionDetail> listAll() {
        return null;
    }


    public void registerTransactionDetail(TransactionDetail transactionDetail) {

        transactionDetailDao.save(transactionDetail);
    }

    public TransactionDetail createTransactionDetail() {
        return TransactionDetail.createTransactionDetail();
    }
}
