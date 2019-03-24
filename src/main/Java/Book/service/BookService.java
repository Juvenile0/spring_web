package Book.service;

import Book.entity.Book;

import java.util.List;

public interface BookService {

    boolean addBook(Book book);
    boolean deleteBook(int id);
    boolean updateBook(int id,Book book);
    List<Book> getBookByType(String  type);
    Book getBook(int id);

    List<Book> getAllBooks();
}
