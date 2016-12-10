package dao;

import model.Category;

import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
public interface CategoryDao {
    Category create(Category category);

    Category findByName(String name);

    List<Category> findAllCategories();
}
