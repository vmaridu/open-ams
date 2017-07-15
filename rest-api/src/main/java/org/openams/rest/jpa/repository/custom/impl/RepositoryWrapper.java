package org.openams.rest.jpa.repository.custom.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

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

	public T create(T t) {
		K key = keyGetter.apply(t);
		if (key != null && repository.exists(key)) {
			throw new EntityExistsException();
		}
		return repository.save(t);
	}

	public T update(T t) {
		K key = keyGetter.apply(t);
		if (key == null || !repository.exists(key)) {
			throw new EntityNotFoundException();
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
	

	public T findOne(K key) {
		return Optional.ofNullable(repository.findOne(key)).orElseThrow(() -> new EntityNotFoundException());
	}
	
	public T findOne(Predicate predicate) {
		return Optional.ofNullable(repository.findOne(predicate)).orElseThrow(() -> new EntityNotFoundException());
	}

	public void delete(K key) {
		if (key == null || !repository.exists(key)) {
			throw new EntityNotFoundException();
		}
		repository.delete(key);
	}

}
