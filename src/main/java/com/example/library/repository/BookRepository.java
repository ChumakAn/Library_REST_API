package com.example.library.repository;

import com.example.library.domain.Author;
import com.example.library.domain.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

List<Book> findByAuthorId(Author authorId);
List<Book> findBookByAuthorId_AuthorName(String authorId_authorName);
Book findTopByAuthorId_AuthorNameOrderBySoldAmountDesc(String authorName);
Book findTopByAuthorId_AuthorNameOrderByPublishedAmountDesc(String authorName);
List<Book> findTop3ByAuthorId_AuthorNameContainingOrderBySoldAmountDesc(String authorName);
List<Book> findTop3ByAuthorId_AuthorNameContainingOrderByPublishedAmountDesc(String authorName);
List<Book> findAllByAuthorId_AuthorNameContaining(String authorName);


}
