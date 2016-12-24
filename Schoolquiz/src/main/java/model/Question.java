package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findQuestionByQuestion", query = "SELECT q FROM Question q WHERE q.question = :question")
})
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String question;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;

    @XmlTransient
    @ManyToOne
    private Category category;

    @NotNull
    private String imageURL;

    public Question() {
        answers = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
