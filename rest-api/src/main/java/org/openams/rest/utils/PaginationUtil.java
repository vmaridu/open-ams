package org.openams.rest.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.model.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationUtil {
	
	private static final String POSITIVE_LOOK_AHEAD_SEPARATOR = "(?=(\\+|\\-))";

	public static Pageable getPageRequest(int pageIndex, int limit) {
		return getPageRequest(pageIndex, limit, null);
	}
	
	public static Pageable getPageRequest(int pageIndex, int limit, String sort) {
		
		if(StringUtils.isBlank(sort)){
			return new PageRequest(pageIndex, limit);
		}
		
		String[] fields = sort.split(POSITIVE_LOOK_AHEAD_SEPARATOR);
		List<Sort.Order> orders = Arrays.stream(fields)
				   .filter(field -> field.matches("(\\+|\\-)[a-zA-Z]*"))
				   .map(field -> field.trim())
				   .map(field -> new Sort.Order((field.charAt(0) == '+' ? Sort.Direction.ASC : Sort.Direction.DESC), field.substring(1)) )
				   .collect(Collectors.toList());
		return new PageRequest(pageIndex, limit,new Sort(orders));
	}
	
	public static <E,M> Page<M> toPageModel(org.springframework.data.domain.Page<E> page, Pageable pageRequest, 
			Function<E,M> mapper){
		
		Page<M> pagModel = new Page<>();
		
		pagModel.setPage(pageRequest.getPageNumber());
		pagModel.setLimit(pageRequest.getPageSize());
		
		pagModel.setTotal(page.getTotalElements());
		pagModel.setTotalPages(page.getTotalPages());
		
		if(page.hasPrevious()){
			Pageable previousPagable = page.previousPageable();
			pagModel.setPrvious("page="+ previousPagable.getPageNumber() + "&limit=" + pageRequest.getPageSize());
		}
		
		if(page.hasNext()){
			Pageable nextPagable = page.nextPageable();
			pagModel.setNext("page="+ nextPagable.getPageNumber() + "&limit=" + pageRequest.getPageSize());
		}
		
		Collection<M> content = new LinkedHashSet<>();
		if(page.hasContent()){
			page.getContent().stream().forEach(e -> content.add(mapper.apply(e)));
			pagModel.setSize(content.size());
		}
		pagModel.setContent(content);
		
		return pagModel;
	}

}
