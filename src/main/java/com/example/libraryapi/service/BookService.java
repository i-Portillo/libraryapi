package com.example.libraryapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.libraryapi.dto.BookRequest;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.Collection;
import com.example.libraryapi.repository.BookRepository;
import com.example.libraryapi.repository.CollectionRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final CollectionRepository collectionRepository;

	public BookService(BookRepository bookRepository, CollectionRepository collectionRepository) {
		this.bookRepository = bookRepository;
		this.collectionRepository = collectionRepository;
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	public void addBook(BookRequest bookRequest) {
		Collection collection = null;
		if (bookRequest.getCollectionData() != null) {
			collection = collectionRepository.findByName(bookRequest.getCollectionData().getCollectionName());
			if (collection == null) {
				collection = new Collection(bookRequest.getCollectionData().getCollectionName());
				collectionRepository.save(collection);
			}
		}

		List<Book.Genre> genres = bookRequest.getGenres().stream()
			.map(genre -> Book.Genre.valueOf(genre.toUpperCase().replace(" ", "_")))
			.toList();

		Book book = new Book(
			bookRequest.getTitle(),
			bookRequest.getAuthor(),
			genres,
			bookRequest.getPageCount(),
			bookRequest.getSummary(),
			collection
		);
		bookRepository.save(book);

		if (collection != null) {
			collection.addBook(book, bookRequest.getCollectionData().getPosition());
			collectionRepository.save(collection);
		}
	}

	@Transactional
	public void deleteBook(String title) {
		// Find the book by title
		Book book = bookRepository.findByTitle(title);
		if (book == null) {
			throw new IllegalArgumentException("Book not found with title: " + title);
		}

		// Remove the book's entry from the collection
		Collection collection = book.getCollection();
		if (collection != null) {
			// Remove the entry referencing the book
			collection.removeBook(book.getId());
			collectionRepository.save(collection);

			// If the collection has no remaining entries, delete it
			if (collection.getEntries().isEmpty()) {
				collectionRepository.delete(collection);
			}
		}

		// Delete the book
		bookRepository.delete(book);
	}
}
