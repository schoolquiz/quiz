package managedbeans;

import model.Category;
import service.CategoryService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Eric on 11/12/2016.
 */
@ManagedBean
public class AddCategoryBean {

    @Inject
    private CategoryService categoryService;

    private Category category;

    private String errorMessage;

    @PostConstruct
    public void init() {
        this.category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addCategory() {
        try {
            category = categoryService.addCategory(category);
            String contextpath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(contextpath);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }
}
