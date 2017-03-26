package org.openams.rest.utils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.function.Function;

import org.openams.rest.model.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtil {

	public static Pageable getPageRequest(int pageIndex, int limit) {
		return new PageRequest(pageIndex, limit);
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
