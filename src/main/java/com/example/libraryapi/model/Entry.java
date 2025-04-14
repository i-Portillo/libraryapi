package com.example.libraryapi.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Entry {

	private Long bookId;
	private int position;

	public Entry() {}

	public Entry(Long bookId, int position) {
		this.bookId = bookId;
		this.position = position;
	}

	public Long getBookId() { return bookId; }
	public void setBookId(Long bookId) { this.bookId = bookId; }

	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }
	
}
