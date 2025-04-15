package com.example.libraryapi.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
public class Book {

	public enum Genre {
		FICTION, NON_FICTION, MYSTERY, FANTASY, SCIENCE_FICTION, BIOGRAPHY, HISTORY, ROMANCE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@ElementCollection(targetClass = Genre.class)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "book_genres", joinColumns = @JoinColumn(name = "book_id"))
	@Column(name = "genre")
	@JsonDeserialize(contentUsing = GenreDeserializer.class)
	private List<Genre> genres = new ArrayList<>();
	
	private int pageCount;
	private String summary;

	@ManyToOne
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

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }

	public List<Genre> getGenres() { return genres; }
	public void setGenres(List<Genre> genres) { this.genres = genres; }
	public void addGenre(Genre genre) {
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


