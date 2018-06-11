package com.softengzone.java.training.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.softengzone.java.training.domain.Bookmark;

public class BookmarkRepository implements AbstractRepository<Bookmark, Long> {
	
	private List<Bookmark> bookmarks; 
	
	public BookmarkRepository() {
		bookmarks = new ArrayList<>();
	}

	@Override
	public List<Bookmark> findAll() {
		return bookmarks;
	}
	
	@Override
	public Bookmark findById(Long id) {
		return bookmarks.stream().filter(b -> b.getId() == id).collect(Collectors.toList()).get(0);
	}

	@Override
	public Bookmark save(Bookmark bookmark) {
		bookmark.setId(bookmarks.size() + 1L);
		bookmarks.add(bookmark);
		return bookmark;
	}

	@Override
	public void delete(Long id) {
		bookmarks.removeIf(b -> (b.getId() == id));				
	}
	
	public List<Bookmark> findAllByAccountId(Long id) {
		return bookmarks.stream().filter(b -> (b.getAccout().getId() == id)).collect(Collectors.toList());
	}
	
	
}
