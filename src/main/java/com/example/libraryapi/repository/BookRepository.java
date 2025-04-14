package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	boolean deleteByTitle(String title);
}
