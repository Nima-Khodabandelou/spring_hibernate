package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoHibernate implements BookDao {

    private final EntityManagerFactory emf;

    public BookDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {

        EntityManager em = getEntityManager();

        try {

            String hql = "SELECT b FROM Book b ORDER BY b.title"
                    + pageable.getSort().getOrderFor("title").getDirection().name();

            TypedQuery query = em.createQuery(hql, Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(Math.toIntExact(pageable.getPageSize()));

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {

        EntityManager em = getEntityManager();

        try {

            TypedQuery query = em.createQuery("SELECT b FROM Book b", Book.class);
            query.setFirstResult(Math.toIntExact(pageable.getOffset()));
            query.setMaxResults(Math.toIntExact(pageable.getPageSize()));

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {
        return null;
    }

    @Override
    public List<Book> findAllBooks() {
        return null;
    }

    @Override
    public Book findByIsbn(String isbn) {

        EntityManager em = getEntityManager();

        try {
            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
            query.setParameter("isbn", isbn);

            Book book = query.getSingleResult();
            return book;
        } finally {
            em.close();
        }
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        EntityManager em = getEntityManager();
        // em.joinTransaction(); // will throws exc if no transaction is in progress
        em.getTransaction().begin();
        em.persist(book);
        em.flush(); // we tell Hibernate to update the DB
        em.getTransaction().commit();

        return book;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }

    private EntityManager getEntityManager() {
        return  emf.createEntityManager();
    }
}
