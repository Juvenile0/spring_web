package Book.dao;

import Book.entity.Book;

import javax.persistence.Convert;
import java.util.List;

public interface BookDao {
    boolean addBook(Book book);
    boolean deleteBook(int id);
    boolean updateBook(int id,Book book);
    List<Book> getBookByType(String type);
    Book getBook(int id);


}
