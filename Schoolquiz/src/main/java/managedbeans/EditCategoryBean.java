package managedbeans;

import model.Category;
import model.Question;
import service.CategoryService;
import service.QuestionService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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

    private String categoryname;

    private Long questionId;

    private Category category;

    private Question question;

    private String errorMessage;

    private String[] answers;

    @PostConstruct
    public void init() {
        categoryname = String.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryname"));
        answers = new String[1];
        question = new Question();
        try {
            category = categoryService.getCategoryByName(categoryname);
        } catch (Exception ex) {
            category = new Category();
            errorMessage = ex.getMessage();
        }
    }

    public void addQuestion() {
        Question constructedQuestion = questionService.construct(question, answers);
        constructedQuestion.setCategory(category);
        try {
            question = questionService.addQuestion(constructedQuestion);
            refresh();
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    public void deleteQuestion() {
        try {
            questionId = new Long(Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("questionId")));
            questionService.deleteQuestion(questionId);
            refresh();
        } catch (Exception ex) {
            errorMessage = ex.getMessage();
        }
    }

    private void refresh() throws Exception {
        answers = new String[1];
        question = new Question();
        category = categoryService.getCategoryByName(categoryname);
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
