package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Author;
import io.nkh.hibernate.repositories.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jt on 8/28/21.
 */
//@Primary
@Component
public class AuthorDaoImpl implements AuthorDao {

    // EntityManagerFactory: JPA equivalent of session factory. It's a heavy obj to create
    private final EntityManagerFactory emf;

    public AuthorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        return getEntityManager().find(Author.class, id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        // String sql = "SELECT a FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name";
        // TypedQuery<Author> query = getEntityManager().createQuery(sql, Author.class);
        // query.setParameter("first_name", firstName);
        // query.setParameter("last_name", lastName);

        EntityManager em = getEntityManager();
        TypedQuery<Author> query = em.createNamedQuery("find_by_name", Author.class);

        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        Author author = query.getSingleResult();
        em.close();

        return author;
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {

        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Author> cq = cb.createQuery(Author.class);

            Root<Author> root = cq.from(Author.class);

            ParameterExpression<String> firstNameParam = cb.parameter(String.class);
            ParameterExpression<String> lastNameParam = cb.parameter(String.class);

            Predicate firstNamePred = cb.equal(root.get("firstName"), firstNameParam);
            Predicate lastNamePred = cb.equal(root.get("lastName"), lastNameParam);

            cq.select(root).where(cb.and(firstNamePred, lastNamePred));

            TypedQuery<Author> tq = em.createQuery(cq);
            // bind params
            tq.setParameter(firstNameParam, firstName);
            tq.setParameter(lastNameParam, lastName);

            return tq.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Author FindAuthorByNameNative(String firstName, String lastName) {

        EntityManager em = getEntityManager();

        try {

            Query q = em.createNativeQuery("SELECT * FROM author a WHERE a.first_name = ? AND a.last_name = ?",
                    Author.class);

            q.setParameter(1, firstName);
            q.setParameter(2, lastName);

            return (Author) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public Author saveNewAuthor(Author author) {

        EntityManager em = getEntityManager();
        // em.joinTransaction(); // will throws exc if no transaction is in progress
        em.getTransaction().begin();
        em.persist(author);
        em.flush(); // we tell Hibernate to update the DB
        em.getTransaction().commit();

        return author;
    }

    @Override
    public Author updateAuthor(Author author) {
        EntityManager em = getEntityManager();
        em.joinTransaction();
        em.merge(author);
        em.flush();
        em.clear(); // clear 1st level cache

        // Since em.flush() was executed, in the following, Hib will load DB to find Id rather than using 1st level
        // cache which is cleared
        return em.find(Author.class, author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Author author = em.find(Author.class, id);
        em.remove(author);
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public List<Author> listOfAuthorByLastNameSuchAs(String lastName) {

        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery("SELECT a FROM Author a WHERE a.lastName LIKE :last_name");
            query.setParameter("last_name", lastName + "%");
            List<Author> authors = query.getResultList();

            return authors;
        } finally {
            em.close();;
        }
    }

    @Override
    public List<Author> findAll() {
        EntityManager em = getEntityManager();

        try {
            TypedQuery<Author> typedQuery = em.createNamedQuery("author_find_all", Author.class);
            return typedQuery.getResultList();
        } finally {
            em.close();
        }
    }

    // EntityManager: JPA equivalent of session. It's lightweight
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
