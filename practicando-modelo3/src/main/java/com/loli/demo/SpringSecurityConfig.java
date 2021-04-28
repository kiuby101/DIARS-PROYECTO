package com.loli.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.loli.demo.models.dao.service.JpaUserDetailsService;
import com.loli.demo.security.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JpaUserDetailsService userdetails;
	

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		    return new MySimpleUrlAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/carrito/**","/css/**","/js/**","/img/**","/cliente/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").successHandler(myAuthenticationSuccessHandler())
        .permitAll()
        .and()
        .logout().logoutSuccessUrl("/carrito/home").permitAll();
	}

	@Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

     @Autowired
     public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
         builder.userDetailsService(userdetails).passwordEncoder(PasswordEncoder());
         
         
     }
     
     
	
	
}
