package com.softengzone.java.training.domain;

public class Bookmark {

	private Long id;
	private Account account;
	private String uri;
	private String description;
	
	@SuppressWarnings("unused")
	private Bookmark() {}
	
	public Bookmark(final Account account, final String uri, final String description) {
		this.account = account;
		this.uri = uri;
		this.description = description;
	}
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public Account getAccout() { return account; }
	public void setAccount(Account account) { this.account = account; }
	public String getUri() { return uri; }
	public void setUti(String uri) { this.uri = uri; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	
}
