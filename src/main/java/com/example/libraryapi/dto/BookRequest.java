package com.example.libraryapi.dto;

import java.util.List;

public class BookRequest {

	private String title;
	private String author;
	private List<String> genres;
	private int pageCount;
	private String summary;
	private CollectionData collectionData;

	public BookRequest() {}

	public BookRequest(String title, String author, List<String> genres, int pageCount, String summary, CollectionData collectionData) {
		this.title = title;
		this.author = author;
		this.genres = genres;
		this.pageCount = pageCount;
		this.summary = summary;
		this.collectionData = collectionData;
	}

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }

	public List<String> getGenres() { return genres; }
	public void setGenres(List<String> genres) { this.genres = genres; }

	public int getPageCount() { return pageCount; }
	public void setPageCount(int pageCount) { this.pageCount = pageCount; }

	public String getSummary() { return summary; }
	public void setSummary(String summary) { this.summary = summary; }

	public CollectionData getCollectionData() { return collectionData; }
	public void setCollectionData(CollectionData collectionData) { this.collectionData = collectionData; }
	
	public static class CollectionData {
		private String collectionName;
		private int position;

		public CollectionData(String collectionName, int position) {
			this.collectionName = collectionName;
			this.position = position;
		}

		public String getCollectionName() { return collectionName; }
		public void setCollectionName(String collectionName) { this.collectionName = collectionName; }

		public int getPosition() { return position; }
		public void setPosition(int position) { this.position = position; }
	}
}
