package com.example.libraryapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GenreDeserializer extends JsonDeserializer<Book.Genre> {
	@Override
	public Book.Genre deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		try {
			String value = p.getText().toUpperCase().replace(" ", "_");
			return Book.Genre.valueOf(value);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid genre: " + p.getText());
		}
	}
}
