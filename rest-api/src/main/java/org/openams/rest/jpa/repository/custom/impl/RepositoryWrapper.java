package org.openams.rest.jpa.repository.custom.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import org.openams.rest.exception.ResourceExistsException;
import org.openams.rest.exception.ResourceNotFoundException;
import org.openams.rest.jpa.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

//TODO:Throw more descriptive errors
public class RepositoryWrapper<T, K extends Serializable>  {

	private BaseRepository<T, K> repository;
	private Function<T, K> keyGetter;

	public RepositoryWrapper(BaseRepository<T, K> repository, Function<T, K> keyGetter){
		this.repository = repository;
		this.keyGetter = keyGetter;
	}
	
	
	public BaseRepository<T, K> getRepository(){
		return repository;
	}
	
	public boolean exists(K key){
		return repository.exists(key);
	}

	public T create(T t) throws ResourceExistsException{
		K key = keyGetter.apply(t);
		if (key != null && repository.exists(key)) {
			throw new ResourceExistsException("Resource with ID (" + key + ") already exists");
		}
		return repository.save(t);
	}

	public T update(T t) throws ResourceNotFoundException {
		K key = keyGetter.apply(t);
		if (key == null || !repository.exists(key)) {
			throw new ResourceNotFoundException("Resource with ID (" + key + ") not found");
		}
		return repository.save(t);
	}

	public Collection<T> findAll() {
		return repository.findAll();
	}
	
	public Iterable<T> findAll(Predicate predicate) {
		return repository.findAll(predicate);
	}
	
	public Page<T> findAll(Pageable pagable) {
		return repository.findAll(pagable);
	}
	
	public Page<T> findAll(Predicate predicate , Pageable pagable) {
		return repository.findAll(predicate, pagable);
	}
	

	public T findOne(K key) throws ResourceNotFoundException {
		return Optional.ofNullable(repository.findOne(key)).orElseThrow(() -> new ResourceNotFoundException("Resource with ID (" + key + ") not found"));
	}
	
	public T findOne(Predicate predicate) throws ResourceNotFoundException {
		return Optional.ofNullable(repository.findOne(predicate)).orElseThrow(() -> new ResourceNotFoundException("Resource with ID predicate not found"));
	}

	public void delete(K key) throws ResourceNotFoundException {
		if (key == null || !repository.exists(key)) {
			throw new ResourceNotFoundException("Resource with ID (" + key + ") not found");
		}
		repository.delete(key);
	}

}
