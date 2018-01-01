/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bluespacetech.security.model.VerificationToken; 
import com.bluespacetech.security.repository.VerificationTokenRepository;
import com.bluespacetech.security.service.UserService;
/**
 * @author pradeep
 *
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFailure authenticationFailure;

	@Autowired
	private AuthenticationSuccess authenticationSuccess;

	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;

	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(this.bCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	 public BCryptPasswordEncoder bCryptPasswordEncoder(){
		 return new BCryptPasswordEncoder();
	 }
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = 
	      new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages_en");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(0);
	    return messageSource;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().csrfTokenRepository(this.csrfTokenRepository()).and().exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler).and().formLogin().successHandler(authenticationSuccess)
		.failureHandler(authenticationFailure).and().logout().permitAll().and().authorizeRequests()
		.antMatchers("/index.html", "/**/*.js", "/**/app/resources/css/**.css", "/**/app/resources/js/**.js",
						"/**/app/resources/fonts/*.*", "/**/app/resources/css/fonts/*.*", "/").authenticated()
		.anyRequest().permitAll().and()
		//.csrf().csrfTokenRepository(csrfTokenRepository())
		.csrf().disable();
        //.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
		//.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

		// http.antMatcher("/*/**").authorizeRequests().antMatchers("/*/**").authenticated().and().formLogin().and().csrf()
		// .disable();
		// http.antMatcher("/*/**").authorizeRequests().antMatchers("/*/**").permitAll().and().csrf().disable();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }

	private CsrfTokenRepository csrfTokenRepository() {
		final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
