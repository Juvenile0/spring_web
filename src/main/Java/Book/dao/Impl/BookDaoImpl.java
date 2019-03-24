package Book.dao.Impl;

import Book.dao.BookDao;
import Book.entity.Book;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addBook(Book book) {

        sessionFactory.getCurrentSession().save(book);

        return true;
    }

    @Override
    public boolean deleteBook(int id) {
        String hql = "delete Book where id = ? ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return  query.executeUpdate() >0;


    }

    @Override
    public boolean updateBook(int id,Book book) {
        String hql = "update Book set name = ? ,type = ?,description = ? where id =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,book.getName());
        query.setParameter(1,book.getType());
        query.setParameter(2,book.getDescription());
        query.setParameter(3,id);
        return query.executeUpdate()>0;


    }

    @Override
    public List<Book> getBookByType(String type) {
        String hql = "from Book where type = ? ";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,type);
        return query.list();


    }

    @Override
    public Book getBook(int id) {
        String hql = " from Book where id =?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        return (Book)query.uniqueResult();


    }

    @Override
    public List<Book> getAllBooks() {
        String hql = "from Book";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }
}
