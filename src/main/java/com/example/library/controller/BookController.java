package com.example.library.controller;


import com.example.library.domain.Book;
import com.example.library.dto.BookDto;
import com.example.library.mapper.AbstractMapper;
import com.example.library.mapper.BookMapper;
import com.example.library.service.AbstractService;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/book")
@RestController
@AllArgsConstructor
public class BookController extends AbstractController<Book, BookDto, Integer> {
    public BookService bookService;
    public BookMapper bookMapper;

    @Override
    protected AbstractService<Book, Integer, BookDto> getService() {
        return bookService;
    }

    @Override
    protected AbstractMapper<Book, BookDto> getMapper() {
        return bookMapper;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/author_name/{authorName}")
//    public @ResponseBody
//    ResponseEntity<List<BookDto>> getBooksByAuthorName(@PathVariable String authorName) {
//        List<Book> books = getService().getAll();
//        List<BookDto> booksDto = new ArrayList<>();
//        for (Book book : books) {
//            if (getMapper().mapObjectToDto(book).getAuthorIdName().equals(authorName)) {
//                booksDto.add(getMapper().mapObjectToDto(book));
//            }
//
//        }
//        return new ResponseEntity<>(booksDto, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/author_id/{authorId}")
    public @ResponseBody
    ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable Integer authorId) {
        if (bookService.findByAuthorId(authorId) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findByAuthorId(authorId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find_by_author_name/{authorName}")
    public @ResponseBody ResponseEntity<List<BookDto>> getBooksByAuthorNames(@PathVariable String authorName){
        if (bookService.findByAuthorIdName(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findByAuthorIdName(authorName), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/find_max_sold/{authorName}")
    public @ResponseBody ResponseEntity<BookDto> getMaxSoldBook(@PathVariable String authorName){
        if (bookService.findMaxSold(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findMaxSold(authorName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find_max_published/{authorName}")
    public @ResponseBody ResponseEntity<BookDto> getMaxPublishedBook(@PathVariable String authorName){
        if (bookService.findMaxPublished(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findMaxPublished(authorName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find_top_sold/{authorName}")
    public @ResponseBody ResponseEntity<List<BookDto>> getTopSoldBooks(@PathVariable String authorName){
        if (bookService.findTop3SoldBooks(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findTop3SoldBooks(authorName), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/find_top_published/{authorName}")
    public @ResponseBody ResponseEntity<List<BookDto>> getTopPublishedBooks(@PathVariable String authorName){
        if (bookService.findTop3PublishedBooks(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.findTop3PublishedBooks(authorName), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/find_book_success/{authorName}")
    public @ResponseBody ResponseEntity<List<BookDto>> getMostSuccessBook(@PathVariable String authorName){
        if (bookService.getMostSuccessBook(authorName) == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookService.getMostSuccessBook(authorName), HttpStatus.OK);
    }
}


