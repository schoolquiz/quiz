package dao;

import model.Category;
import model.Question;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Eric on 11/12/2016.
 */
@Named
public class QuestionJPA implements QuestionDao {

    @PersistenceContext(name = "SchoolQuizPU")
    private EntityManager entityManager;

    @Override
    public Question create(Question question) throws Exception{
        String errorMessage = "Could not add question to the database";
        try {
            findByQuestion(question.getQuestion());
            throw new IllegalArgumentException("Question already exists");
        } catch(NoResultException ex) {
            try {
                entityManager.persist(question);
                return entityManager.find(Question.class, question.getId());
            } catch(Exception exception) {
                throw new Exception(errorMessage, exception);
            }
        } catch(IllegalArgumentException ex) {
            throw new Exception(ex.getMessage(), ex);
        } catch(Exception ex) {
            throw new Exception(errorMessage, ex);
        }
    }

    @Override
    public Question findById(Long id) throws Exception {
        try {
            return entityManager.find(Question.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find category in database", ex);
        }
    }


    @Override
    public Question findByQuestion(String question) throws Exception {
        try {
            return (Question) entityManager.createNamedQuery("findQuestionByQuestion").setParameter("question", question).getSingleResult();
        } catch(NoResultException ex) {
            throw new NoResultException("Question " + question + "does not exist");
        }
    }

    @Override
    public List<Question> findAllQuestions() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Question> c = qb.createQuery(Question.class);
            c.from(Question.class);

            TypedQuery<Question> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not find questions in database", ex);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            Question question = findById(id);
            if (question == null) {
                throw new IllegalArgumentException("Question does not exist");
            }
            entityManager.remove(question);
        } catch (IllegalArgumentException ex) {
            throw new Exception(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception("Could not delete question from database", ex);
        }
    }
}
