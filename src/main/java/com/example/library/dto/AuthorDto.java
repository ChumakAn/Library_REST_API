package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {
    private Integer id;
    private String authorName;
    private String birthDate;
    private String phone;
    private String email;
    private Integer numberOfBooks;
}
