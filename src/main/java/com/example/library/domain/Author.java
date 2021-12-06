package com.example.library.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "author")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Author implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "author_name", length = 45)
    private String authorName;
    @Column(name = "birth_date", length = 10)
    private String birthDate;
    @Column(name = "phone", length = 15)
    private String phone;
    @Column(name = "email", length = 45)
    private String email;

    @OneToMany(mappedBy = "authorId", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author(Integer id, String authorName, String birthDate, String phone, String email) {
        this.id = id;
        this.authorName = authorName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }
}
