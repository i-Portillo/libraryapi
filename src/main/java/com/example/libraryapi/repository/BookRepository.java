package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
	
	private static final String FILE_PATH = "books.json";
	private final ObjectMapper mapper = new ObjectMapper();
	private List<Book> books;

	public BookRepository() {
		books = loadBooksFromFile();
	}

	private List<Book> loadBooksFromFile() {
		File file = new File(FILE_PATH);
		if (!file.exists()) return new ArrayList<>();

		try {
			return mapper.readValue(file, new TypeReference<List<Book>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void saveBooksToFile() {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), books);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Book> findAll() {
		return books;
	}

	public void addBook(Book book) {
		books.add(book);
		saveBooksToFile();
	}

	public boolean deleteBook(String title) {
		boolean removed = books.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
		if (removed) saveBooksToFile();
		return removed;
	}

	public Book findByTitle(String title) {
		return books.stream()
			.filter(b -> b.getTitle().equalsIgnoreCase(title))
			.findFirst()
			.orElse(null);
	}

}
