package com.softengzone.java.training.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.repository.AccountRepository;
import com.softengzone.java.training.repository.BookmarkRepository;
import com.softengzone.java.training.service.AccountService;
import com.softengzone.java.training.service.BookmarkService;
import com.softengzone.java.training.test.utils.TestUtility;

public class BookmarkServiceTest {

	private BookmarkService bookmarkService;
	private AccountService accountService;
	private Account account;
	private static final String uri1 = "http://step.com/step1";
	private static final String uri2 = "http://step.com/step2";
	
	@Before
	public void setUp() {
		AccountRepository accountRepository = new AccountRepository();
		BookmarkRepository bookmarkRepository = new BookmarkRepository();
		bookmarkService = new BookmarkService(bookmarkRepository, accountRepository);
		accountService = new AccountService(accountRepository);
		account = accountService.save(new Account("username", "password"));
	}
	
	@Test
	public void testOnInitRespositoryIsEmpty() {
		assertThat(bookmarkService.findAll().size(), equalTo(0));
	}
	
	/**
	 * When saving a new bookmark, it should exist in bookmark repository and also be added to the list of bookmarks 
	 * for given account
	 */
	@Test
	public void testSaveOneBookmark_BookmarkSavedAndAddedToList() {	
		Bookmark bookmark = TestUtility.saveBookmark(bookmarkService, accountService, new Bookmark(account, uri1, "Step 1"));
		// bookmark repository should contain 1 bookmark
		assertThat(TestUtility.getRepositorySize(bookmarkService), equalTo(1));
		
		// validate if the new saved bookmark exists
		assertThat(bookmarkService.findById(bookmark.getId()).getUri(), equalTo(uri1));
		
		// validate if the new saved bookmark has been added to the list of bookmarks for given account
		assertTrue(accountService.findAllBookmarksByAccountId(account.getId()).contains(bookmark));
	}
	
	@Test
	public void testFindBookmarksById_BookmarkReturned() {		
		Bookmark bookmark1 = TestUtility.saveBookmark(bookmarkService, accountService, new Bookmark(account, uri1, "Step 1"));
		Bookmark bookmark2 = TestUtility.saveBookmark(bookmarkService, accountService, new Bookmark(account, uri2, "Step 2"));
		
		assertNotNull(bookmark1);
		assertNotNull(bookmark2);
		assertThat(bookmarkService.findAll().size(), equalTo(2));
		assertThat(bookmarkService.findById(bookmark1.getId()).getUri(), equalTo(uri1));
		assertThat(bookmarkService.findById(bookmark2.getId()).getUri(), equalTo(uri2));
	}
	
	@Test
	public void testDeleteBookmarkById_BookmarkDeleted() {
		int bookmarkRepoSize = TestUtility.getRepositorySize(bookmarkService);
		
		Bookmark bookmark1 = TestUtility.saveBookmark(bookmarkService, accountService, new Bookmark(account, uri1, "Step 1"));
		assertNotNull(bookmark1);
		Bookmark bookmark2 = TestUtility.saveBookmark(bookmarkService, accountService, new Bookmark(account, uri2, "Step 2"));
		assertNotNull(bookmark2);
		
		bookmarkRepoSize = bookmarkRepoSize + 2;		
		assertThat(TestUtility.getRepositorySize(bookmarkService), equalTo(bookmarkRepoSize));
		assertThat(bookmarkService.findById(bookmark1.getId()).getUri(), equalTo(uri1));		
		assertTrue(accountService.findAllBookmarksByAccountId(bookmark1.getAccout().getId()).contains(bookmark1));
		
		bookmarkService.delete(bookmark1.getId());
		assertThat(TestUtility.getRepositorySize(bookmarkService), equalTo(bookmarkRepoSize - 1));
		assertFalse(accountService.findAllBookmarksByAccountId(bookmark1.getAccout().getId()).contains(bookmark1));
		accountService.findAllBookmarksByAccountId(account.getId()).forEach(b -> System.out.print(b.getUri()));
	}
	
	
}
