package com.example.libraryapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public class Book {

	public enum Genre {
		FANTASY,
		MYSTERY,
		YOUNG_ADULT,
		THRILLER,
		HORROR,
		ROMANCE,
		FICTION,
		SCIFI
	}

	private String title;
	private String author;

	@JsonDeserialize(contentUsing = GenreDeserializer.class)
	private List<Genre> genres;
	
	private int pageCount;
	private String summary;
	private Collection collection;

	public Book() {}

	public Book(String title, String author, List<Genre> genres, int pageCount, String summary, Collection collection) {
		this.title = title;
		this.author = author;
		this.genres = genres;
		this.pageCount = pageCount;
		this.summary = summary;
		this.collection = collection;
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }

	public List<Genre> getGenres() { return genres; }
	public void setGenres(List<Genre> genres) { this.genres = genres; }
	public void addGenre(Genre genre) {
		if (genres == null) {
			genres = new ArrayList<>();
		}
		if (!genres.contains(genre)) {
			genres.add(genre);
		}
	}
	public void removeGenre(Genre genre) { genres.remove(genre); }

	public int getPageCount() { return pageCount; }
	public void setPageCount(int pageCount) { this.pageCount = pageCount; }

	public String getSummary() { return summary; }
	public void setSummary(String summary) { this.summary = summary; }

	public Collection getCollection() { return collection; }
	public void setCollection(Collection collection) { this.collection = collection; }

}


