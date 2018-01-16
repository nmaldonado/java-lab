package uy.com.netlabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.netlabs.dao.impl.ProductDaoImpl;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */

@Service
public class ProductServiceImpl implements ProductService {



    @Autowired
    ProductDaoImpl productDao;

    @Override
    public List<Product> listProducts() {
        return productDao.list() ;
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public boolean exist(int id) {
        return productDao.exist(id);
    }


    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void save(Product product) {

    }

    @Override
    public double getFinalPrice(Product product) {
        int discounPercentage = product.getCategory().getDiscountPercentage();
        Double finalPrice = ((product.getPrice()/100)*discounPercentage);
        new BigDecimal(finalPrice.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return finalPrice;
    }
}
