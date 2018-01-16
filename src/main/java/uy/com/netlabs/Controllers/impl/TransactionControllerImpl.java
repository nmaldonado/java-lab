package uy.com.netlabs.Controllers.impl;

import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uy.com.netlabs.Controllers.TransactionController;
import uy.com.netlabs.exceptions.ErrorLowStockException;
import uy.com.netlabs.exceptions.ErrorProductNotExistsException;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.model.Transaction;
import uy.com.netlabs.service.ProductService;
import uy.com.netlabs.service.TransactionDetailService;
import uy.com.netlabs.service.TransactionService;


import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
public class TransactionControllerImpl implements TransactionController {

    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value="/transaction",
            params = {"id", "quantity"},
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public    @ResponseBody
    Transaction registerTransaction(
            @RequestParam(value = "id") int idProducto,
            @RequestParam(value = "quantity") int quantity) throws ErrorLowStockException, ErrorProductNotExistsException {
        Transaction transaction=null;
        try {
            if (productService.exist(idProducto)) {
                Product product = productService.findById(idProducto);
                if (product.getStock() >= quantity) {
                    transaction = transactionService.registerTransaction(product, quantity);
                } else {
                    throw new ErrorLowStockException("Low stock error");
                }
            } else {
                throw new ErrorProductNotExistsException("Product not found");
            }
        }catch(ErrorLowStockException e){
            LOGGER.log(Level.FINE, e.toString(), e);
        } catch(ErrorProductNotExistsException e){
            LOGGER.log(Level.FINE, e.toString(), e);
        }
        return transaction;
    }


    @RequestMapping(value ="/transaction/list" ,method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Transaction> listTransactions() {
        return transactionService.listTransactions() ;
    }






}
