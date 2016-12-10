package dao;

import model.Category;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Eric on 10/12/2016.
 */
@Named
public class CategoryDaoJPA implements CategoryDao {

    @PersistenceContext(name = "SchoolQuizPU")
    private EntityManager entityManager;

    @Override
    public Category create(Category category) {
        entityManager.persist(category);
        return entityManager.find(Category.class, category.getId());
    }

    @Override
    public Category findByName(String name) {
        return (Category) entityManager.createNamedQuery("findCategoryByName").setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Category> findAllCategories() {
        return (List<Category>) entityManager.createNamedQuery("findAllCategories").getResultList();
    }
}
