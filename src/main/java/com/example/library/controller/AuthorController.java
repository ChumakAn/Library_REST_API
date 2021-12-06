package com.example.library.controller;

import com.example.library.domain.Author;
import com.example.library.dto.AuthorDto;
import com.example.library.dto.MostSuccessfulAuthorDto;
import com.example.library.mapper.AbstractMapper;
import com.example.library.mapper.AuthorMapper;
import com.example.library.service.AbstractService;
import com.example.library.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/author")
@RestController
@AllArgsConstructor
public class AuthorController extends AbstractController<Author, AuthorDto, Integer>{
    private AuthorService authorService;
    private AuthorMapper authorMapper;
    @Override
    protected AbstractService<Author, Integer,AuthorDto> getService() {
        return authorService;
    }

    @Override
    protected AbstractMapper<Author, AuthorDto> getMapper() {
        return authorMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/most_successful_author")
    public @ResponseBody
    ResponseEntity<MostSuccessfulAuthorDto> getMostSuccessfulAuthor(){
        if (authorService.getMostSuccessfulAuthor() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authorService.getMostSuccessfulAuthor(), HttpStatus.OK);
    }
}
