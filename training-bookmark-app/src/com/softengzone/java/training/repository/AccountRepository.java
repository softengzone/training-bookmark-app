package com.softengzone.java.training.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.exception.AccountNotFoundException;

public class AccountRepository implements AbstractRepository<Account, Long> {

	List<Account> accounts;
	
	public AccountRepository() {
		this.accounts = new ArrayList<>();
	}
	
	@Override
	public List<Account> findAll() {
		return accounts;
	}
	
	@Override
	public Account findById(Long id) {
		Optional<Account> account = accounts.stream().filter(a -> a.getId() == id).findFirst();
		account.orElseThrow(() -> new AccountNotFoundException());
		return account.get();
	}

	@Override
	public Account save(Account account) {
		account.setId(accounts.size() + 1L);
		accounts.add(account);
		return account;
	}

	@Override
	public void delete(Long id) {
		accounts.removeIf(a -> a.getId() == id);
	}
	
	public Set<Bookmark> findAllBookmarksByAccountId(Long id) {
		return findById(id).getBookmarks();
	}
	
			
}
