/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.security;

import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.bluespacetech.security.service.UserService;


/**
 * The Class SecurityConfig.
 *
 * @author pradeep
 * @author Sudhanshu Tripathy
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The authentication failure. */
	@Autowired
	private AuthenticationFailure authenticationFailure;

	/** The authentication success. */
	@Autowired
	private AuthenticationSuccess authenticationSuccess;

	/** The unauthorized handler. */
	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;

	/**
	 * Config auth builder.
	 *
	 * @param auth the auth
	 * @throws Exception the exception
	 */
	@Autowired
	public void configAuthBuilder(AuthenticationManagerBuilder auth) throws Exception {
		//System.out.println("Inside Config Auth Builder");
		auth.userDetailsService(userService).passwordEncoder(this.passwordEncoder());
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Multi part resolver.
	 *
	 * @return the multipart resolver
	 */
	@Bean
	public MultipartResolver multiPartResolver()
	{
		//return new CommonsMultipartResolver();
		return new StandardServletMultipartResolver();
	}
	
	 /**
 	 * Multipart config element.
 	 *
 	 * @return the multipart config element
 	 */
 	@Bean
	    public MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	        factory.setMaxFileSize("128MB");
	        factory.setMaxRequestSize("128MB");
	        return factory.createMultipartConfig();
	    }
	
	/*@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = 
	      new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages_en");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    messageSource.setDefaultEncoding("UTF-8");
	    messageSource.setCacheSeconds(0);
	    return messageSource;
	}*/
	 
	 /**
	 * Velocity engine.
	 *
	 * @return the velocity engine
	 * @throws Exception the exception
	 */
	@Bean
	 public VelocityEngine velocityEngine() throws Exception {
	     Properties properties = new Properties();
	     properties.setProperty("input.encoding", "UTF-8");
	     properties.setProperty("output.encoding", "UTF-8");
	     properties.setProperty("resource.loader", "class");
	     properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	     VelocityEngine velocityEngine = new VelocityEngine(properties);
	     return velocityEngine;
	 } 

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http
		.csrf().csrfTokenRepository(this.csrfTokenRepository()).and()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler).and().formLogin().successHandler(authenticationSuccess)
		.failureHandler(authenticationFailure).and().logout().permitAll().and().authorizeRequests()
		.antMatchers("/app/resources/**","/index.html", "/**/*.js", "/**/app/resources/css/**.css", "/**/app/resources/js/**.js",
						"/**/app/resources/fonts/*.*", "/**/app/resources/css/fonts/*.*", "/","/analytics/recentSummary","/new",
						"/new/register","/new/**","/about/**","/emails/unsubscribe?**","/emails/readMail?**")//.authenticated().
		.permitAll().anyRequest().authenticated()
		.and()
		//.csrf().disable();
		.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

		// http.antMatcher("/*/**").authorizeRequests().antMatchers("/*/**").authenticated().and().formLogin().and().csrf()
		// .disable();
		// http.antMatcher("/*/**").authorizeRequests().antMatchers("/*/**").permitAll().and().csrf().disable();
		
	}

	/**
	 * Csrf token repository.
	 *
	 * @return the csrf token repository
	 */
	private CsrfTokenRepository csrfTokenRepository() {
		final HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");
		return repository;
	}

}
