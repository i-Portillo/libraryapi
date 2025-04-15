package com.example.libraryapi.controller;

import com.example.libraryapi.dto.BookRequest;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Collection;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.repository.CollectionRepository;
import com.example.libraryapi.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
		bookService.addBook(bookRequest);
		return ResponseEntity.ok("Book added.");
	}

	@DeleteMapping("/{title}")
	public ResponseEntity<String> deleteBook(@PathVariable String title) {
		bookService.deleteBook(title);
		return ResponseEntity.ok("Book deleted.");
	}
}
