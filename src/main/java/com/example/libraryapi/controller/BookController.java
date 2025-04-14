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
		if (book.getCollection() != null) {
			Collection collection = collectionRepository.findByName(book.getCollection().getName());
			if (collection == null) {
				collection = book.getCollection();
				collectionRepository.save(book.getCollection());
			}
			book.setCollection(collection);
		}
		repository.save(book);
		return ResponseEntity.ok("Book added.");
	}

	@DeleteMapping("/{title}")
	public String deleteBook(@PathVariable String title) {
		repository.deleteByTitle(title);
		return "Book deleted.";
	}
}
