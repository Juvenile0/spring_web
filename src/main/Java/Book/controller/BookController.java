package Book.controller;

import Book.entity.Book;
import Book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {


    @Autowired
    private BookService bookService;


    @GetMapping("/Books/{type}")
    public ResponseEntity<List<Book>> getBookByType(@PathVariable("type") String type ) {
        List<Book> bookByType = bookService.getBookByType(type);
        if(bookByType==null){
            return new ResponseEntity<List<Book>>((List<Book>) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookByType,HttpStatus.OK);
    }

    @GetMapping("/Book/{id}")
    public ResponseEntity getBookById(@PathVariable("id") int  id) {

        Book book = bookService.getBook(id);
        if (book == null) {
            return new ResponseEntity("Book Not Found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book) {

        bookService.addBook(book);

        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    //删除
    @DeleteMapping("/Book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {

        if (!bookService.deleteBook(id)) {
            return new ResponseEntity("No Book found for ID " + id, HttpStatus.NOT_FOUND);
        }
        //204
        return new ResponseEntity(id, HttpStatus.NO_CONTENT);

    }

    //更新book
    @PutMapping("/Book/{id}")
    public ResponseEntity updateBook(@PathVariable int id, @RequestBody Book book) {

        boolean b = bookService.updateBook(id,book);

        if (!b) {
            return new ResponseEntity("No Book found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(book, HttpStatus.CREATED);
    }

}
