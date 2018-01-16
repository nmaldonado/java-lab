package uy.com.netlabs.dao;


import org.springframework.stereotype.Repository;
import uy.com.netlabs.model.Product;

import java.util.List;
import java.util.Set;


public interface ProductDao {

    void save(Product product);
    void updateProduct(Product product);
    void deleteAll();
    List<Product> list();
    Product findById(int id);
    boolean exist(int id);


}
