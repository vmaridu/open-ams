package org.openams.rest.controller.resolver;

import static org.openams.rest.utils.Constants.DEFAULT_LIMIT;
import static org.openams.rest.utils.Constants.DEFAULT_PAGE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openams.rest.utils.PaginationUtil;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PagableArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Pageable.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Map<String, String[]> params = webRequest.getParameterMap();
		int pageIndex = getIntValue(params, "page", DEFAULT_PAGE);
		int limit = getIntValue(params, "limit", DEFAULT_LIMIT);
		String sort = getValue(params, "sort");
		return PaginationUtil.getPageRequest(pageIndex, limit, sort);
	}

	private int getIntValue(Map<String, String[]> params, String key, int defaultValue) {
		String value = getValue(params, key);
		return (value == null || !StringUtils.isNumeric(value)) ? defaultValue : Integer.parseInt(value);
	}

	private String getValue(Map<String, String[]> params, String key) {
		String[] values = params.get(key);
		return (values != null && values.length > 0) ? values[0] : null;
	}

}
