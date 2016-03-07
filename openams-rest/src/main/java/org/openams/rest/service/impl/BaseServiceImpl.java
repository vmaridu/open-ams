package org.openams.rest.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.openams.rest.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

//TODO:Use reflection to get ID
public abstract class BaseServiceImpl<T, K extends Serializable> implements BaseService<T, K> {

	public abstract JpaRepository<T, K> getRepository();

	@Override
	public T create(T t, K k) {
		if (k != null && getRepository().exists(k)) {
			throw new EntityExistsException();
		}
		return getRepository().save(t);
	}

	@Override
	public T update(T t, K k) {
		if (k == null || !getRepository().exists(k)) {
			throw new EntityNotFoundException();
		}
		return getRepository().save(t);
	}

	@Override
	public Collection<T> getAll() {
		Collection<T> entities = getRepository().findAll();
		if (entities.isEmpty()) {
			throw new EntityNotFoundException();
		}
		return entities;
	}

	@Override
	public T get(K k) {
		return Optional.ofNullable(getRepository().findOne(k)).orElseThrow(() -> new EntityNotFoundException());
	}

	@Override
	public void delete(K k) {
		if (k == null || !getRepository().exists(k)) {
			throw new EntityNotFoundException();
		}
		getRepository().delete(k);
	}

}
