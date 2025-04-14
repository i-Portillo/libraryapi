package com.example.libraryapi.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Entity
public class Collection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ElementCollection
	@CollectionTable(name = "collection_entries", joinColumns = @JoinColumn(name = "collection_id"))
	private List<Entry> entries = new ArrayList<>();

	public Collection() {}

	public Collection(String name) {
		this.name = name;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public List<Entry> getEntries() { return entries; }
	public void setEntries(List<Entry> entries) { this.entries = entries; }

	public void addBook(Book book, int position) {
		Optional<Entry> existingAtPosition = entries.stream()
			.filter(e -> e.getPosition() == position)
			.findFirst();
		if (existingAtPosition.isPresent()) {
			throw new RuntimeException("Position " + position + " is already occupied by " + existingAtPosition.get().getBookId());
		}
		entries.add(new Entry(book.getId(), position));
	}

	public void removeBook(Long bookId) {
		entries.removeIf(entry -> entry.getBookId().equals(bookId));
	}

	public void removeBookByPosition(int position) {
		entries.removeIf(entry -> entry.getPosition() == position);
	}

}
