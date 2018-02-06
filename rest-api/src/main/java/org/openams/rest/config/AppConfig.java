package org.openams.rest.config;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.dozer.DozerBeanMapper;
import org.openams.rest.filter.CORSFilter;
import org.openams.rest.filter.TransactionLoggingFilter;
import org.openams.rest.utils.CacheBuilderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

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
	
	@Bean
    public FilterRegistrationBean<TransactionLoggingFilter> transactionLoggingFilterRegistrationBean() {
        FilterRegistrationBean<TransactionLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        TransactionLoggingFilter contextFilter = new TransactionLoggingFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE );
        return registrationBean;
    }
	
	@Bean
    public FilterRegistrationBean<CORSFilter> customCorsFilter(@Value("${cors.ac.allow.origin}") String allowOrigin, 
    														   @Value("${cors.ac.allow.methods}") String allowMethods, 
    														   @Value("${cors.ac.allow.maxAge}") String maxAge, 
    														   @Value("${cors.ac.allow.credentials}") String allowCredentials, 
    														   @Value("${cors.ac.allow.headers}") String allowHeaders, 
    														   @Value("${cors.ac.allow.exposeHeaders}") String exposeHeaders) {
													
        FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<>();
        CORSFilter corsFilter = new CORSFilter(allowOrigin, allowMethods, maxAge, allowCredentials, allowHeaders,exposeHeaders);
        registrationBean.setFilter(corsFilter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registrationBean;
    }

}
