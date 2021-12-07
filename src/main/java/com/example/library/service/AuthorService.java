package com.example.library.service;

import com.example.library.domain.Author;
import com.example.library.domain.Book;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.BookDto;
import com.example.library.dto.MostSuccessfulAuthorDto;
import com.example.library.mapper.AbstractMapper;
import com.example.library.mapper.AuthorMapper;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService extends AbstractService<Author, Integer, AuthorDto> {
    @Autowired
    public AuthorRepository authorRepository;
    @Autowired
    public BookRepository bookRepository;
    @Autowired
    public BookMapper bookMapper;
    @Autowired
    public AuthorMapper authorMapper;

    @Override
    protected JpaRepository<Author, Integer> getRepository() {
        return authorRepository;
    }

    @Override
    protected AbstractMapper<Author, AuthorDto> getMapper() {
        return authorMapper;
    }

    public MostSuccessfulAuthorDto getMostSuccessfulAuthor() {
        List<Author> authorList = authorRepository.findAll();
        Double sumOfSuccessBookRate = 0.0;
        HashMap<Double, String> successResult = new HashMap<>();
        for (Author author : authorList) {
            List<BookDto> bookList = author.getBooks().stream().map(bookMapper::mapObjectToDto).collect(Collectors.toList());
            for (BookDto bookDto : bookList) {
                sumOfSuccessBookRate += bookDto.getSuccessBookRate();
                successResult.put((sumOfSuccessBookRate / author.getBooks().size()), author.getAuthorName());
            }
        }
        ArrayList<Double> successRatesList = new ArrayList<>(successResult.keySet());
        MostSuccessfulAuthorDto mostSuccessfulAuthor = new MostSuccessfulAuthorDto(successResult.get(Collections.max(successRatesList)), Collections.max(successRatesList));
        if (mostSuccessfulAuthor == null){
            return null;
        }
        return mostSuccessfulAuthor;
    }

}
