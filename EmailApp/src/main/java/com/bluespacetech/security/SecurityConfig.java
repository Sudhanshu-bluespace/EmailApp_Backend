package com.bluespacetech.security;

import com.bluespacetech.security.service.UserService;
import java.util.Properties;
import javax.servlet.MultipartConfigElement;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationFailure authenticationFailure;
    @Autowired
    private AuthenticationSuccess authenticationSuccess;
    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MultipartResolver multiPartResolver()
    {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement()
    {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128MB");
        factory.setMaxRequestSize("128MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public VelocityEngine velocityEngine() throws Exception
    {
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine velocityEngine = new VelocityEngine(properties);
        return velocityEngine;
    }

    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().csrfTokenRepository(csrfTokenRepository()).and().exceptionHandling()
                .authenticationEntryPoint(this.unauthorizedHandler).and().formLogin()
                .successHandler(this.authenticationSuccess).failureHandler(this.authenticationFailure).and().logout()
                .permitAll().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, new String[] { "/products/downloadFile" }).permitAll()
                .antMatchers(HttpMethod.GET, new String[] { "/track/readMail" }).permitAll()
                .antMatchers(new String[] { "/app/resources/**", "/index.html", "/**/*.js",
                        "/**/app/resources/css/**.css", "/**/app/resources/js/**.js", "/**/app/resources/fonts/*.*",
                        "/**/app/resources/css/fonts/*.*", "/", "/analytics/recentSummary", "/new", "/new/register",
                        "/new/**", "/about/**", "/track/**" })
                .permitAll().anyRequest().authenticated().and().csrf().disable();
    }

    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
