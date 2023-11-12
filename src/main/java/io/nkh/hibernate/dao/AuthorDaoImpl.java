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
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    // EntityManager: JPA equivalent of session. It's lightweight
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
