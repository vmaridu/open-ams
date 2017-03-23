package org.openams.rest.jpa.repository.custom.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryWrapper<T, K extends Serializable>  {

	private JpaRepository<T, K> repository;
	private Function<T, K> keyGetter;

	public RepositoryWrapper(JpaRepository<T, K> repository, Function<T, K> keyGetter){
		this.repository = repository;
		this.keyGetter = keyGetter;
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

	public Collection<T> getAll() {
		Collection<T> entities = repository.findAll();
		if (entities.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return entities;
	}

	public T get(K key) {
		return Optional.ofNullable(repository.findOne(key)).orElseThrow(() -> new EntityNotFoundException());
	}

	public void delete(K key) {
		if (key == null || !repository.exists(key)) {
			throw new EntityNotFoundException();
		}
		repository.delete(key);
	}

}
