package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "findCategoryByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
        @NamedQuery(name = "findAllCategories", query = "SELECT c FROM Category c")
})
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

    @NotNull
    private String imageURL;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
