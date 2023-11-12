package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Author;
import io.nkh.hibernate.repositories.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 8/28/21.
 */
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

        String sql = "SELECT a FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name";
        TypedQuery<Author> query = getEntityManager().createQuery(sql, Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);

        return query.getSingleResult();
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

    }

    // EntityManager: JPA equivalent of session. It's lightweight
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
