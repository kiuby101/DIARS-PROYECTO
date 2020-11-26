package com.loli.demo.models.dao.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loli.demo.models.dao.IClienteDao;
import com.loli.demo.models.entity.Cliente;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IClienteDao clienteDao;
	

	@Autowired
	private JavaMailSender mailSender;
	
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteDao.findByEmail(username);
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(cliente.getRoles()));
		
		logger.info("Rol:".concat(cliente.getRoles()));
		
		
		if(cliente.getRoles().equals("ROLE_ADMIN")) {
			SendMail(cliente.getNombre(),cliente.getApellido());
		}
		
		
		
		
		return new User(cliente.getEmail(), cliente.getPassword(), true, true, true, true, authorities);
	}
	
	public void SendMail(String nombre,String apellido) {
		String fullname=nombre.concat("  ").concat(apellido);
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("luislolimott@gmail.com");
		message.setTo("luislolimott@gmail.com");
		
		String mailContent="El administrador "+fullname+" a ingresado al sistema";
		String mailSubject="Actividad por parte de un administrador del sistema";
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		
		mailSender.send(message);
		
	}
}
