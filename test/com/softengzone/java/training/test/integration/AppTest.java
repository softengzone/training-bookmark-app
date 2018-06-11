package com.softengzone.java.training.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.softengzone.java.training.repository.AccountRepository;
import com.softengzone.java.training.repository.BookmarkRepository;
import com.softengzone.java.training.service.AccountService;
import com.softengzone.java.training.service.BookmarkService;

public class AppTest {

	private AccountService accountService;
	private BookmarkService bookmarkService;
	
	@Before
	public void setUp() {
		AccountRepository accountRepository = new AccountRepository();
		BookmarkRepository bookmarkRepository = new BookmarkRepository();
		accountService = new AccountService(accountRepository);
		bookmarkService = new BookmarkService(bookmarkRepository, accountRepository);
	}
	
	@Test
	public void accountRespository_onInitRepositoryIsEmtpty() {
		assertThat(accountService.findAll().size(), equalTo(0));
	}
	
	@Test
	public void bookmarkRepository_onInitRespositoryIsEmpty() {

		assertThat(accountService.findAll().size(), equalTo(0));
	}
		
	
}
