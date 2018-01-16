package uy.com.netlabs.Controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import uy.com.netlabs.Controllers.ProductController;
import uy.com.netlabs.dao.ProductDao;
import uy.com.netlabs.model.Product;
import uy.com.netlabs.service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

public class ProductControllerImpl implements ProductController {

    @Autowired
    ProductService productService;

    public List<Product> listProducts() {
        return productService.listProducts();
    }

    public Product findById(int id) {
        return productService.findById(id);
    }

    public boolean exist(int id) {
        return productService.exist(id);
    }

    public Product createProduct() {
        return Product.createProduct();
    }

    public void save(Product product) {
        productService.save(product);
    }

    public double getFinalPrice(Product product) {
        return productService.getFinalPrice(product);
    }
}
