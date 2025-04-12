package com.example.libraryapi.controller;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.repository.BookRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookRepository repository = new BookRepository();

	@GetMapping
	public List<Book> getBooks() {
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		try {
			repository.addBook(book);
			return ResponseEntity.ok("Book added!");
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body("Error: " + e.getMessage());
		}
	}

	@DeleteMapping("/{title}")
	public String deleteBook(@PathVariable String title) {
		boolean removed = repository.deleteBook(title);
		return removed ? "Book deleted." : "Book not found.";
	}
}
