package uy.com.netlabs.service;

import uy.com.netlabs.model.Category;

import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */
public interface CategoryService {


    List<Category> listAll();
    void UpdateCategoryDiscount(int id, int discountPercentage);
    void save(Category category);
    boolean exist(Category category);
}
