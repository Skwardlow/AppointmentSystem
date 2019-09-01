package ru.eltex.project.simpleappointer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;
/**
 * WebSecurityConfig class adding and limit rights for users that asks
 * access to methods and web-pages
 * Initialize automatically when spring security is starting
 * extending the WebSecurityConfigurerAdapter base class and overriding individual methods
 * @author skwardlow
 * @version 1.0
 * @see org.springframework.security.config.annotation.SecurityConfigurer
 * @see WebSecurityConfigurerAdapter
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Datasource bean used for configuring
     * @see org.springframework.security.config.annotation.SecurityConfigurer
     * */
    @Autowired
    private DataSource dataSource;
    /**
     * This method is limit rights for users, that request something from server.
     * No method params expected. Its just...works.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/js/**", "/css/**", "/reg_user", "/contents/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/client").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/specialist").hasAnyAuthority("SPECIALIST", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
    /**
     * This method configuring default datasource
     * no params expected. running automatically
     * @param auth automatically generated
     * @throws Exception
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }
}