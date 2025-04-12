package com.example.libraryapi.model;

import java.util.List;

public class Collection {
	List<Entry> entries;

	public Collection() {}

	public Collection(List<Entry> entries) {
		this.entries = entries;
	}
}

class Entry {
	Book book;
	float position;

	public Entry() {}

	public Entry(Book book, float position) {
		this.book = book;
		this.position = position;
	}
}
