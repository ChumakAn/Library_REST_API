package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper extends AbstractMapper<Book, BookDto>{
    @Override
    public BookDto mapObjectToDto(Book book) {
        if (book == null) {
            return null;
        }
        BookDto.BookDtoBuilder bookDto = BookDto.builder();
        bookDto.id(book.getId());
        bookDto.bookName(book.getBookName());
        bookDto.authorIdName(book.getAuthorId().getAuthorName());
        bookDto.publishedAmount(book.getPublishedAmount());
        bookDto.soldAmount(book.getSoldAmount());
        bookDto.successBookRate(((book.getSoldAmount().doubleValue())/(book.getPublishedAmount().doubleValue())));
        return bookDto.build();
    }
}
