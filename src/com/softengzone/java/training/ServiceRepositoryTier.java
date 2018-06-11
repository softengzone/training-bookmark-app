package com.softengzone.java.training;

import java.util.Arrays;

import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.repository.AccountRepository;
import com.softengzone.java.training.repository.BookmarkRepository;
import com.softengzone.java.training.service.AccountService;
import com.softengzone.java.training.service.BookmarkService;

public class ServiceRepositoryTier {
		
	private AccountService accountService;
	private BookmarkService bookmarkService;
	
	public ServiceRepositoryTier() {
		AccountRepository accountRepository = new AccountRepository(); 
		accountService = new AccountService(accountRepository);
		bookmarkService = new BookmarkService(new BookmarkRepository(), accountRepository);
		init();
	}
	
	private void init() {
		Arrays.asList(new String[] {"account_1", "account_2", "account_3", "account_4"})
			.forEach(
					a -> {						
						Account account = accountService.save(new Account(a, "password"));
						bookmarkService.save(new Bookmark(account, "http://step.com/s1", "Step 1"));
						bookmarkService.save(new Bookmark(account, "http://step.com/s2", "Step 2"));
						bookmarkService.save(new Bookmark(account, "http://step.com/s3", "Step 3"));
					});		
	}
	
	public AccountService getAccountService() {
		return accountService;
	}
	
	public BookmarkService getBookmarkService() {
		return bookmarkService;
	}
	
}
