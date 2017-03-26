package org.openams.rest.utils;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;

public class CacheBuilderUtil {

	public static ConcurrentMap<?, ?> getExpirableConcurrentMap(long ttlSeconds) {
		return CacheBuilder.newBuilder().expireAfterWrite(ttlSeconds, TimeUnit.SECONDS).build().asMap();
	}
	
	public static ConcurrentMap<?, ?> getConcurrentMap() {
		return CacheBuilder.newBuilder().build().asMap();
	}

}
