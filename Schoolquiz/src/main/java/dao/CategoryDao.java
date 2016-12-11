package dao;

import model.Category;

import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
public interface CategoryDao {
    Category create(Category category) throws Exception;

    Category findById(Long id) throws Exception;

    Category findByName(String name) throws Exception;

    List<Category> findAllCategories() throws Exception;

    void delete(Long id) throws Exception;
}
