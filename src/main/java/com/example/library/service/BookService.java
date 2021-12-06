package com.example.library.service;

import com.example.library.domain.Author;
import com.example.library.domain.Book;
import com.example.library.dto.BookDto;
import com.example.library.mapper.AbstractMapper;
import com.example.library.mapper.AuthorMapper;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BookService extends AbstractService<Book, Integer,BookDto> {
    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public AuthorRepository authorRepository;
    @Autowired
    public BookMapper bookMapper;


    @Override
    protected JpaRepository<Book, Integer> getRepository() {
        return bookRepository;
    }

    @Override
    protected AbstractMapper<Book, BookDto> getMapper() {
        return bookMapper;
    }

    public List<BookDto> findByAuthorId(Integer authorId) {
        Author author = authorRepository.getOne(authorId);
        List<BookDto> bookDtoList = bookRepository.findByAuthorId(author).stream().map(bookMapper::mapObjectToDto).collect(toList());
        if (bookDtoList.isEmpty()) {
            return null;
        }
        return bookDtoList;
    }

    public List<BookDto> findByAuthorIdName(String authorName) {
        List<BookDto> bookDtoList = bookRepository.findBookByAuthorId_AuthorName(authorName).stream().map(bookMapper::mapObjectToDto).collect(toList());
        if (bookDtoList.isEmpty()) {
            return null;
        }
        return bookDtoList;
    }

    public BookDto findMaxSold(String authorName) {
        BookDto bookDto = bookMapper.mapObjectToDto(bookRepository.findTopByAuthorId_AuthorNameOrderBySoldAmountDesc(authorName));
        if (bookDto == null) {
            return null;
        }
        return bookDto;
    }

    public BookDto findMaxPublished(String authorName) {
        BookDto bookDto = bookMapper.mapObjectToDto(bookRepository.findTopByAuthorId_AuthorNameOrderByPublishedAmountDesc(authorName));
        if (bookDto == null) {
            return null;
        }
        return bookDto;
    }

    public List<BookDto> findTop3SoldBooks(String authorName) {
        List<BookDto> bookDtoList = bookRepository.findTop3ByAuthorId_AuthorNameContainingOrderBySoldAmountDesc(authorName).stream().map(bookMapper::mapObjectToDto).collect(toList());
        if (bookDtoList.isEmpty()) {
            return null;
        }
        return bookDtoList;
    }

    public List<BookDto> findTop3PublishedBooks(String authorName) {
        List<BookDto> bookDtoList = bookRepository.findTop3ByAuthorId_AuthorNameContainingOrderByPublishedAmountDesc(authorName).stream().map(bookMapper::mapObjectToDto).collect(toList());
        if (bookDtoList.isEmpty()) {
            return null;
        }
        return bookDtoList;
    }

    public List<BookDto> getMostSuccessBook(String authorName) {
        List<BookDto> bookDtoList = bookRepository.findAllByAuthorId_AuthorNameContaining(authorName).stream().map(bookMapper::mapObjectToDto).sorted(((o1, o2) -> (int) (o1.getSuccessBookRate() - o2.getSoldAmount()))).collect(toList());
        if (bookDtoList.isEmpty()) {
            return null;
        }
        return bookDtoList;


    }

}
