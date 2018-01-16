package uy.com.netlabs.Controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import uy.com.netlabs.Controllers.CategoryController;
import uy.com.netlabs.dao.CategoryDao;
import uy.com.netlabs.model.Category;

import java.util.List;
import java.util.Set;

@Controller
public class CategoryControllerImpl implements CategoryController{


    @Autowired
    private CategoryDao categoryDao;

    public void save(Category category) {
        categoryDao.save(category);
    }

    public Category createCategory() {
        Category category = new Category();
        return category;
    }

    public List<Category> listAll() {
        return categoryDao.list();
    }

    public void UpdateCategoryDiscount(int id, int discountPercentage) {

        categoryDao.updateDiscountPercentage(id,discountPercentage);
    }
}
