package managedbeans;

import model.Category;
import service.CategoryService;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

/**
 * Created by Eric on 11/12/2016.
 */
@ManagedBean
public class EditCategoryBean {

    @Inject
    private CategoryService categoryService;

    @Inject
    private QuestionService questionService;

    @ManagedProperty(value = "#{param.categoryname}")
    private String categoryname;

    @ManagedProperty(value = "#{param.questionId}")
    private Long questionId;

    private Category category;

    private String errorMessage;

    @PostConstruct
    public void init() {
        try {
            category = categoryService.getCategoryByName(categoryname);
        } catch (Exception ex) {
            category = new Category();
            errorMessage = ex.getMessage();
        }
    }

    public void deleteQuestion() {
        try {
            questionService.deleteQuestion(questionId);
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
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

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
