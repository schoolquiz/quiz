package service;

import dao.CategoryDao;
import model.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@Stateless
public class CategoryService implements Serializable {

    @Inject
    private CategoryDao categoryDao;

    private Category constructCategory(Category category) {
        return category;
    }

    public Category addCategory(Category category) throws Exception {
        return categoryDao.create(category);
    }

    public List<Category> getAllCategories() throws Exception {
        return categoryDao.findAllCategories();
    }

    public Category getCategoryByName(String name) throws Exception  {
        return categoryDao.findByName(name);
    }

    public void deleteCategory(Long id) throws Exception {
        categoryDao.delete(id);
    }
}
