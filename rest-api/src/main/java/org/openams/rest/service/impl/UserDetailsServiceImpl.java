package org.openams.rest.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openams.rest.jpa.entity.Role;
import org.openams.rest.jpa.entity.User;
import org.openams.rest.jpa.entity.enums.UserStatus;
import org.openams.rest.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository repository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = Optional.ofNullable(repository.findOne(userName)).orElseThrow(() -> new UsernameNotFoundException("User Not Found !!!"));

		String password = user.getPassword();
		// additional information on the security object
		boolean enabled = user.getStatus() == UserStatus.ACTIVE;
		boolean accountNonExpired = enabled;
		boolean credentialsNonExpired = enabled;
		boolean accountNonLocked = !(user.getStatus() == UserStatus.LOCKED);

		Collection<GrantedAuthority> authorities = user.getRoles().stream()
				.map(Role::getName).map(role -> "ROLE_" + role)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(	
				userName, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		
		return securityUser;

	}

}
