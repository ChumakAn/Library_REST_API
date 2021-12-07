package com.example.library.mapper;

import com.example.library.domain.Author;
import com.example.library.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDto>{
    @Override
    public AuthorDto mapObjectToDto(Author author) {
        if(author == null){
        return null;
        }
        AuthorDto.AuthorDtoBuilder authorDto = AuthorDto.builder();
        authorDto.id(author.getId());
        authorDto.authorName(author.getAuthorName());
        authorDto.birthDate(author.getBirthDate());
        authorDto.phone(author.getPhone());
        authorDto.email(author.getEmail());
        authorDto.numberOfBooks(author.getBooks().size());
        return authorDto.build();
    }
}
