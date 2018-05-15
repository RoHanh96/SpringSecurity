package com.example.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findbyEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		Set<GrantedAuthority> grantedAuthorities =new HashSet<>();
		Set<Role> roles = user.getRoles();
		for(Role role: roles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

}
