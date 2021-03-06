package dao;

import model.Category;

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
 * Created by Eric on 10/12/2016.
 */
@Named
public class CategoryDaoJPA implements CategoryDao {

    @PersistenceContext(name = "SchoolQuizPU")
    private EntityManager entityManager;

    @Override
    public Category create(Category category) throws Exception{
        String errorMessage = "Could not add category to the database";
        try {
            findByName(category.getName());
            throw new IllegalArgumentException("Category already exists");
        } catch(NoResultException ex) {
            try {
                entityManager.persist(category);
                return entityManager.find(Category.class, category.getId());
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
    public Category findById(Long id) throws Exception {
        try {
            return entityManager.find(Category.class, id);
        } catch (Exception ex) {
            throw new Exception("Could not find category in database", ex);
        }
    }


    @Override
    public Category findByName(String name) throws Exception {
        try {
            return (Category) entityManager.createNamedQuery("findCategoryByName").setParameter("name", name).getSingleResult();
        } catch(NoResultException ex) {
            throw new NoResultException("Category " + name + "does not exist");
        }

    }

    @Override
    public List<Category> findAllCategories() throws Exception {
        try {
            CriteriaBuilder qb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Category> c = qb.createQuery(Category.class);
            c.from(Category.class);

            TypedQuery<Category> query = entityManager.createQuery(c);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception("Could not find categories in database", ex);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            Category category = findById(id);
            if (category == null) {
                throw new IllegalArgumentException("Category does not exist");
            }
            entityManager.remove(category);
        } catch (IllegalArgumentException ex) {
            throw new Exception(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new Exception("Could not delete category from database", ex);
        }
    }
}
