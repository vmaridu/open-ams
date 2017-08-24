package org.openams.rest.utils;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtil {

	public static Optional<String> getPrincipal(){
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(c -> c.getAuthentication())
				.map(a -> (String) a.getPrincipal());
	}
}
