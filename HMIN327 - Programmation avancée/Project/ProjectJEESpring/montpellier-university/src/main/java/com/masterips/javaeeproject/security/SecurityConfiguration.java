package com.masterips.javaeeproject.security;

import javax.sql.DataSource;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;
    

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}123")
//			.roles("ADMIN");
//		
//		auth.inMemoryAuthentication()
//		.withUser("amir")
//		.roles("USER").password("{noop}123");
		
		
	    auth.jdbcAuthentication()
	    .dataSource(dataSource)
	    .usersByUsernameQuery("select email as principal, password as credentials, active from users where email=?")
	    .authoritiesByUsernameQuery("select email as principal, role as role from users_roles where email=?")
	    .rolePrefix("ROLE_")
	    .passwordEncoder(new BCryptPasswordEncoder());

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login");
		http.authorizeRequests()
		.antMatchers("/profile")		//"/celebrities/add", "/modify"
		.hasRole("ADMIN");
		
		http.authorizeRequests()
		.antMatchers("/profile")		// "/json", "/celebrities"
		.hasRole("USER");
		
		http.exceptionHandling().accessDeniedPage("/403");
	}
    
    
	
	
	

}
