package com.softengzone.java.training.test.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.exception.AccountNotFoundException;
import com.softengzone.java.training.repository.AccountRepository;
import com.softengzone.java.training.service.AccountService;
import com.softengzone.java.training.test.utils.TestUtility;

public class AccountServiceTest {

	private AccountService accountService;
	
	@Before
	public void setUp() {
		AccountRepository accountRepository = new AccountRepository();
		accountService = new AccountService(accountRepository);
	}
	
	@Test
	public void testAccountServiceIsInitialized() {
		assertNotNull(accountService);
	}
	
	/**
	 * On load the account repository should have no elements
	 */
	@Test
	public void testWhenServiceInitialized_noElementsInRepository() {
		assertThat(accountService.findAll().size(), equalTo(0));
	}
	
	/**
	 * After saving one account, the account repository should have one more element.
	 * The new saved element should exist in account repository.
	 */
	@Test
	public void testSaveOneAccount_accountSaved() {
		String username = "_username";
		String password = "_password";
		int repoSize = accountService.findAll().size();
		
		TestUtility.saveAccount(accountService, new Account(username, password));
		
		// check if the new created account exists in repository
		Account accFoundInRepo = accountService.findAll().stream()
				.filter(a -> a.getUsername().equalsIgnoreCase(username))
				.filter(a -> a.getPassword().equalsIgnoreCase(password))
				.collect(Collectors.toList()).get(0);
		assertNotNull(accFoundInRepo);
		assertThat(accFoundInRepo.getUsername(), equalTo(username));
		assertThat(accFoundInRepo.getPassword(), equalTo(password));
		
		// check if the repository has one more element
		assertThat(accountService.findAll().size(), equalTo(repoSize + 1));
	}
	
	/**
	 * FindAll method should return all accounts currently being stored in the account repository 
	 */
	@Test
	public void testSearchForAccountWithGivenId_AccountFound() {
		Account account1 = TestUtility.saveAccount(accountService, new Account("username_1", "password_1"));
		Account account2 = TestUtility.saveAccount(accountService, new Account("username_2", "password_2"));
		Account account3 = TestUtility.saveAccount(accountService, new Account("username_3", "password_3"));
		
		// repository size should be 3
		assertThat(TestUtility.getRepositorySize(accountService), equalTo(3));
		
		// find account1
		assertThat(accountService.findById(account1.getId()).getId(), equalTo(account1.getId()));
		// find account2
		assertThat(accountService.findById(account2.getId()).getId(), equalTo(account2.getId()));
		// find account3
		assertThat(accountService.findById(account3.getId()).getId(), equalTo(account3.getId()));
	}
	
	@Test(expected = AccountNotFoundException.class)
	public void testSearchForAccountWithGivenId_AccountNotFound() {
		Optional<Account> account = Optional.ofNullable(accountService.findById(100L));
		account.orElseThrow(() -> new AccountNotFoundException());
	}
	
	@Test
	public void testFindAllAccounts_AllAccountsReturned() {
		for (int i=0; i < 100; i++) {
			TestUtility.saveAccount(accountService, new Account("username_"+i, "password_"+i));
		}
		assertThat(TestUtility.getRepositorySize(accountService), equalTo(100));
	}
	
	@Test
	public void testDeleteOneAccount_AccountIsNotAvailable() {
		Account account1 = TestUtility.saveAccount(accountService, new Account("username_1", "password_1"));
		assertThat(TestUtility.getRepositorySize(accountService), equalTo(1));
		accountService.delete(account1.getId());
		assertThat(TestUtility.getRepositorySize(accountService), equalTo(0));
	}
	

}
