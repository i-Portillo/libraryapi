package com.example.libraryapi.controller;

import com.example.libraryapi.model.Collection;
import com.example.libraryapi.repository.CollectionRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionController {
	
	private final CollectionRepository repository;

	public CollectionController(CollectionRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Collection> getCollections() {
		return repository.findAll();
	}

}
