package uy.com.netlabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.netlabs.dao.CategoryDao;
import uy.com.netlabs.dao.impl.CategoryDaoImpl;
import uy.com.netlabs.model.Category;
import uy.com.netlabs.service.CategoryService;

import java.util.List;
import java.util.Set;

/**
 * Created by nicolas on 15/01/18.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDaoImpl categoryDao;

    @Override
    public List<Category> listAll() {
        return categoryDao.list();
    }

    @Override
    public void UpdateCategoryDiscount(int id, int percentage) {
        categoryDao.updateDiscountPercentage(id,percentage);
    }

    @Override
    public boolean exist(Category category) {
        return categoryDao.exists(category.getId());
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }
}
