package service;

import dao.QuestionDao;
import model.Answer;
import model.Question;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Eric on 11/12/2016.
 */
@Stateless
public class QuestionService {

    @Inject
    private QuestionDao questionDao;

    public Question construct(Question question, String[] answers) {
        ArrayList<Answer> tempAnswers = new ArrayList<>();
        for (String answer : answers) {
            Answer ans = new Answer(answer, question);
            tempAnswers.add(ans);
        }

        question.setAnswers(tempAnswers);
        return question;
    }

    public Question addQuestion(Question question) throws Exception {
        return questionDao.create(question);
    }

    public void deleteQuestion(Long id) throws Exception {
        questionDao.delete(id);
    }
}
