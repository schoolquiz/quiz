package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Eric on 10/12/2016.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String answer;

    @XmlTransient
    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
    }

    public Answer(String answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
