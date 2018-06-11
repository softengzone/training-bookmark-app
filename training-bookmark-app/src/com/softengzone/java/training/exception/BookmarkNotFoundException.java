package com.softengzone.java.training.exception;

@SuppressWarnings("serial")
public class BookmarkNotFoundException extends RuntimeException {

	private static final String ERROR = "Bookmark not found";
	
	public BookmarkNotFoundException() {
		super(ERROR);
	}
	
	public BookmarkNotFoundException(String error) {
		super(error);
	}
	
}
