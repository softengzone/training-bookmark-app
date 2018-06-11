package com.softengzone.java.training.service;

import java.util.List;
import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.repository.AccountRepository;
import com.softengzone.java.training.repository.BookmarkRepository;

public class BookmarkService implements AbstractService<Bookmark, Long> {
	
	private BookmarkRepository bookmarkRepository;
	private AccountRepository accountRepository;

	public BookmarkService(final BookmarkRepository bookmarkRepository, final AccountRepository accountRepository) {
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}
	
	@Override
	public List<Bookmark> findAll() {
		return bookmarkRepository.findAll();
	}

	@Override
	public Bookmark findById(Long id) {
		return bookmarkRepository.findById(id);
	}

	@Override
	public Bookmark save(Bookmark bookmark) {
		Account account = accountRepository.findById(bookmark.getAccout().getId());
		account.getBookmarks().add(bookmark);
		return bookmarkRepository.save(bookmark);
	}

	@Override
	public void delete(Long id) {
		Long accountId = findById(id).getAccout().getId();
		Account account = accountRepository.findById(accountId);
		account.getBookmarks().removeIf(b -> b.getId() == id);
		bookmarkRepository.delete(id);
	}

}
