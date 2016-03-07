package org.openams.rest.service;

import java.util.Collection;


public interface BaseService<T,K> {

	public T create(T t, K k);

	public T update(T t, K k);

	public Collection<T> getAll();

	public T get(K k);

	public void delete(K k);

}
