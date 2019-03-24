package Book.service.Impl;

import Book.dao.BookDao;
import Book.entity.Book;
import Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;



    @Override
    public boolean addBook(Book book) {
        return  bookDao.addBook(book);
    }

    @Override
    public boolean deleteBook(int id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public boolean updateBook(int id,Book book) {
        return bookDao.updateBook(id,book);
    }

    @Override
    public List<Book> getBookByType(String type) {
        return bookDao.getBookByType(type);
    }

    @Override
    public Book getBook(int id) {
        return bookDao.getBook(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}
