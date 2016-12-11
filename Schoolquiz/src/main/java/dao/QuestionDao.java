package dao;

import model.Category;
import model.Question;

import java.util.List;

/**
 * Created by Eric on 11/12/2016.
 */
public interface QuestionDao {
    Question create(Question question) throws Exception;

    Question findById(Long id) throws Exception;

    Question findByQuestion(String question) throws Exception;

    List<Question> findAllQuestions() throws Exception;

    void delete(Long id) throws Exception;
}
