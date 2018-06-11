package com.softengzone.java.training.repository;

import java.util.List;

public interface AbstractRepository<T, ID> {
	
	List<T> findAll();
	T findById(Long id);
	T save(T t);
	void delete(ID id);

}
