package uy.com.netlabs.dao;

import org.springframework.stereotype.Repository;
import uy.com.netlabs.model.Category;

import java.util.List;
import java.util.Set;



public interface CategoryDao {
    void save(Category category);
    void updateDiscountPercentage(int id, int percentage );
    List<Category> list();
    Category findById(int id);
    boolean exists(int id);



}
