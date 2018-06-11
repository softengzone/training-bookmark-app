package com.softengzone.java.training.test.utils;

import java.util.Optional;

import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.service.AbstractService;
import com.softengzone.java.training.service.AccountService;
import com.softengzone.java.training.service.BookmarkService;

public class TestUtility {

	public static Account saveAccount(final AccountService accountService, final Account account) {
		return accountService.save(account);
	}
		
	public static Bookmark saveBookmark(final BookmarkService bookmarkService, final AccountService accountService, Bookmark bookmark) {
		return bookmarkService.save(bookmark);
	}
		
	public static int getRepositorySize(AbstractService<?, ?> service) {
		return service.findAll().size();
	}
	
}
