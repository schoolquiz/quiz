package service;

import dao.QuestionDao;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Eric on 11/12/2016.
 */
@Stateless
public class QuestionService {

    @Inject
    private QuestionDao questionDao;

    public void deleteQuestion(Long id) throws Exception {
        questionDao.delete(id);
    }
}
