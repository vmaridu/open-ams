package org.openams.rest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.dozer.DozerBeanMapper;
import org.openams.rest.utils.CacheBuilderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class AppConfig {

	@Bean(name = "mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList("dozer-mapping.xml");
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}
	
	@Bean
	public ConcurrentMap<?,?> getExpirableCache(@Value("${expirableCacheTtl}") long expirableCacheTtl){
		return CacheBuilderUtil.getExpirableConcurrentMap(expirableCacheTtl * 60);
	}
	
	@Bean
	public ConcurrentMap<?,?> getEternalCache(){
		return CacheBuilderUtil.getConcurrentMap();
	}
	
	@Bean
	public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
		return (ConcurrentMapCacheManager cacheManager) -> {
			cacheManager.setCacheNames(Arrays.asList("eternalCache", "expirableCache"));
		};
	}

}
