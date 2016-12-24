package managedbeans;

import model.Category;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@ManagedBean
public class CategoryBean implements Serializable {

    @Inject
    private CategoryService categoryService;

    private List<Category> categories;

    private String errorMessage;

    @PostConstruct
    public void init() {
        try {
            categories = categoryService.getAllCategories();
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void deleteCategory() {
        try {
            String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("formId:categoryId");
            System.out.println("About to delete: " + value);
            Long categoryId = Long.parseLong(value);
            categoryService.deleteCategory(categoryId);
            refresh();
        } catch (Exception ex) {
            System.out.println("Invalid param");
        }
    }

    private void refresh() throws Exception {
        categories = categoryService.getAllCategories();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
