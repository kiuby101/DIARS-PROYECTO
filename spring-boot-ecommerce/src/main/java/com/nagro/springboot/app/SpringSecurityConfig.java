package com.nagro.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*Permitir acceso a cualquier usuario
		 * AntMatchers -> acepta todas las carpetas y rutas puestas dentro del controller 
		 * como ("/") ("/administrador/login") son ejemplos
		 * Cuando las rutas presentan /administrador/** -> estos puntos equivale a las subrutas
		*/
		http.authorizeRequests().antMatchers("/administracion/**","/css/**","/js/**","/img/**").permitAll()
		//.antMatchers("/administracion/listarProducto").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/administracion/login")
		.permitAll()
		.and()
		.logout().permitAll();
				
	}


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		//Creación de usuario
		
		PasswordEncoder encoder = passwordEncoder();
		
		UserBuilder users= User.builder().passwordEncoder(password ->{
			return encoder.encode(password);
		});
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
		.withUser(users.username("JOSE").password("12345").roles("USER"));
		
		
	}
	

}
