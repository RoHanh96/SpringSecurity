package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("Vao password encoder");
		return new BCryptPasswordEncoder();
	}
	 
	 @Override
		protected void configure(HttpSecurity http) throws Exception{
			System.out.println("I am here");
			http.cors().and().csrf().disable().authorizeRequests()
					.antMatchers( "/register").permitAll()
					.antMatchers("/").hasRole("MEMBER")
					.antMatchers("/admin").hasRole("ADMIN")
					.and()
				.formLogin()
					.loginPage("/login_custom").permitAll()
					.usernameParameter("email")
					.passwordParameter("password")
					.defaultSuccessUrl("/").permitAll()
					.failureUrl("/login?error").permitAll()
					.and()
				.exceptionHandling()
					.accessDeniedPage("/403");
//			http.cors().and().csrf().disable();
//			http.headers().frameOptions().disable();
			super.configure(http);				
		}
	 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
}
