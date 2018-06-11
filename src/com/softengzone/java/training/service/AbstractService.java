package com.softengzone.java.training.service;

import java.util.List;

public interface AbstractService<T, ID> {

	List<T> findAll();
	T findById(Long id);
	T save(T t);
	void delete(ID id);
	
}
