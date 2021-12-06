package com.example.library.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "book")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "book_name", length = 45)
    private String bookName;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author authorId;
    @Column(name = "published_amount")
    private Integer publishedAmount;
    @Column(name = "sold_amount")
    private Integer soldAmount;

}
