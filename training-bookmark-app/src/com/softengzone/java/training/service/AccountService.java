package com.softengzone.java.training.service;

import java.util.List;
import java.util.Set;

import com.softengzone.java.training.domain.Account;
import com.softengzone.java.training.domain.Bookmark;
import com.softengzone.java.training.repository.AccountRepository;

public class AccountService implements AbstractService<Account, Long> {

	private AccountRepository accountRepository;
	
	public AccountService(final AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account findById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(Long id) {
		accountRepository.delete(id);
	}
	
	public Set<Bookmark> findAllBookmarksByAccountId(Long id) {
		return accountRepository.findAllBookmarksByAccountId(id);
	}

}
