package service;

import dao.CategoryDao;
import model.Category;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@Stateless
public class CategoryService {

    @Inject
    private CategoryDao categoryDao;

    public Category addCategory(Category category) {
        return categoryDao.create(category);
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAllCategories();
    }
}
