package org.openams.rest.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.openams.rest.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseServiceImpl<T, K extends Serializable> implements BaseService<T, K> {

	private JpaRepository<T, K> repository;
	private Function<T, K> keyGetter;

	protected BaseServiceImpl(JpaRepository<T, K> repository, Function<T, K> keyGetter){
		this.repository = repository;
		this.keyGetter = keyGetter;
	}

	@Override
	public boolean exists(K key){
		return repository.exists(key);
	}

	@Override
	public T create(T t) {
		K key = keyGetter.apply(t);
		if (key != null && repository.exists(key)) {
			throw new EntityExistsException();
		}
		return repository.save(t);
	}

	@Override
	public T update(T t) {
		K key = keyGetter.apply(t);
		if (key == null || !repository.exists(key)) {
			throw new EntityNotFoundException();
		}
		return repository.save(t);
	}

	@Override
	public Collection<T> getAll() {
		Collection<T> entities = repository.findAll();
		if (entities.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return entities;
	}

	@Override
	public T get(K key) {
		return Optional.ofNullable(repository.findOne(key)).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public void delete(K key) {
		if (key == null || !repository.exists(key)) {
			throw new EntityNotFoundException();
		}
		repository.delete(key);
	}

}
