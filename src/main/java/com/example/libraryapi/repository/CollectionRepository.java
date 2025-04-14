package com.example.libraryapi.repository;

import com.example.libraryapi.model.Collection;
import com.example.libraryapi.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class CollectionRepository {

	private static final String FILE_PATH = "collections.json";
	private final ObjectMapper mapper = new ObjectMapper();
	private List<Collection> collections;

	public CollectionRepository() {
		collections = loadCollectionsFromFile();
	}

	private List<Collection> loadCollectionsFromFile() {
		File file = new File(FILE_PATH);
		if (!file.exists()) return new ArrayList<>();

		try {
			return mapper.readValue(file, new TypeReference<List<Collection>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void saveCollectionsToFile() {
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), collections);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Collection> findAll() {
		return collections;
	}

	public void addCollection(Collection collection) {
		collections.add(collection);
		saveCollectionsToFile();
	}

	public void updateCollection(Collection collection) {
		for (int i = 0; i < collections.size(); i++) {
			if (collections.get(i).getId().equals(collection.getId())) {
				collections.set(i, collection);
				saveCollectionsToFile();
				return;
			}
		}
		throw new RuntimeException("Collection with ID " + collection.getId() + " not found.");
	}

	public void addBookToCollection(String collectionName, Book book, int position) {
		Collection collection = findByName(collectionName);
		if (collection != null) {
			collection.addBook(book, position);
			saveCollectionsToFile();
		} else {
			throw new RuntimeException("Collection " + collectionName + " not found.");
		}
	}

	public boolean deleteCollection(String name) {
		boolean removed = collections.removeIf(c -> c.getName().equalsIgnoreCase(name));
		if (removed) saveCollectionsToFile();
		return removed;
	}

	public Collection findByName(String name) {
		return collections.stream()
			.filter(c -> c.getName().equalsIgnoreCase(name))
			.findFirst()
			.orElse(null);
	}
	
}
