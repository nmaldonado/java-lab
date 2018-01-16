package uy.com.netlabs.service;

import uy.com.netlabs.model.Product;

import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */
public interface ProductService {


    List<Product> listProducts();

    Product findById(int id);

    boolean exist(int id);

    void updateProduct(Product product);

    void save(Product product);

    double getFinalPrice(Product product);
}
