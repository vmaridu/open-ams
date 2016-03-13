package org.openams.rest.service;

import java.util.Collection;


public interface BaseService<T,K> {

	public boolean exists(K k);

	public T create(T t);

	public T update(T t);

	public Collection<T> getAll();

	public T get(K k);

	public void delete(K k);

}
