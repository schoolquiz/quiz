package managedbeans;

import model.Category;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@ManagedBean
public class CategoryBean {

    @Inject
    private CategoryService categoryService;

    private List<Category> categories;

    @PostConstruct
    public void init() {
        categories = categoryService.getAllCategories();
    }

    public List<Category> getCategories() {
        return categories;
    }
}
