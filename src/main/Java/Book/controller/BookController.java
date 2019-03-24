package Book.controller;

import Book.entity.Book;
import Book.service.BookService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {


    @Autowired
    private BookService bookService;
    //查找所有书本
    @ApiOperation(value = "获取全部书籍", notes = "")
    @GetMapping("/Books")
    public ResponseEntity getAllBooks() {
        List<Book> bookByType = bookService.getAllBooks();
        if(bookByType==null){
            return new ResponseEntity((List<Book>) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookByType,HttpStatus.OK);
    }


    //按照类别查找书籍
    @ApiOperation(value = "查找书籍", notes = "通过type查找书籍")
    @ApiImplicitParam(name = "type", value = "书籍类型", required = true, dataType = "String",paramType = "path")
    @GetMapping("/Books/{type}")
    public ResponseEntity getBookByType(@PathVariable("type") String type ) {
        if(type.equals("24")) System.out.println(type);
        List<Book> bookByType = bookService.getBookByType(type);
        if(bookByType==null){
            return new ResponseEntity((List<Book>) null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookByType,HttpStatus.OK);
    }


    //按照id查找书籍
    @ApiOperation(value = "查询书籍", notes = "通过书籍id查询")
    @ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "int",paramType = "path")
    @GetMapping("/Book/{id}")
    public ResponseEntity getBookById(@PathVariable("id") int  id) {
        System.out.println(id);
        Book book = bookService.getBook(id);
        if (book == null) {
            return new ResponseEntity("Book Not Found for id" + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(book, HttpStatus.OK);
    }



    //上传书籍
    @ApiOperation(value = "创建书籍", notes = "通过book对象创建")
    @ApiImplicitParam(name = "book", value = "书籍实体类", required = true, dataType = "Book")
    @PostMapping("/Book")
    public ResponseEntity createBook(@RequestBody Book book) {
        if(bookService.getBook(book.getId())!=null){
            return new ResponseEntity("Book  already exist for id " + book.getId(), HttpStatus.FORBIDDEN);
        }
        bookService.addBook(book);

        return new ResponseEntity(book, HttpStatus.CREATED);
    }



    //更新book
    @ApiOperation(value = "更新书籍详细信息", notes = "根据url的id来指定更新对象，并根据传过来的book信息来更新")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "int",paramType = "path"),
            @ApiImplicitParam(name = "book", value = "书籍实体类", required = true, dataType = "book") })
    @PutMapping("/Book/{id}")
    public ResponseEntity updateBook(@PathVariable int id, @RequestBody Book book) {

        boolean b = bookService.updateBook(id,book);

        if (!b) {
            return new ResponseEntity("No Book found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(book, HttpStatus.CREATED);
    }

    //删除书籍
    @ApiOperation(value = "删除书籍", notes = "通过书籍id创建")
    @ApiImplicitParam(name = "id", value = "书籍id", required = true, dataType = "int",paramType = "path")
    @DeleteMapping("/Book/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {

        if (!bookService.deleteBook(id)) {
            return new ResponseEntity("No Book found for ID " + id, HttpStatus.NOT_FOUND);
        }
        //204
        return new ResponseEntity(id, HttpStatus.NO_CONTENT);

    }




}
