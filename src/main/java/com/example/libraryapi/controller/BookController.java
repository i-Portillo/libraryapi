package com.example.libraryapi.controller;

import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Collection;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.repository.CollectionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookRepository repository;
	private final CollectionRepository collectionRepository;

	public BookController(BookRepository bookRepository, CollectionRepository collectionRepository) {
		this.repository = bookRepository;
		this.collectionRepository = collectionRepository;
	}

	@GetMapping
	public List<Book> getBooks() {
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		if (book.getTitle() == null || book.getTitle().isEmpty()) {
			return ResponseEntity.badRequest().body("Error: Title is required.");
		}
		if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
			return ResponseEntity.badRequest().body("Error: Author is required.");
		}
		if (book.getGenres() == null || book.getGenres().isEmpty()) {
			return ResponseEntity.badRequest().body("Error: At least one genre is required.");
		}
		if (book.getPageCount() <= 0) {
			return ResponseEntity.badRequest().body("Error: Page count must be greater than zero.");
		}

		if (book.getCollection() != null) {
			createCollectionWithBook(book.getCollection().getName(), book);
		}

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

	private void createCollectionWithBook(String collectionName, Book book) {
		Collection collection = new Collection(collectionName);
		collection.addBook(book, 0);
		collectionRepository.addCollection(collection);
		book.setCollection(collection);
	}
}
