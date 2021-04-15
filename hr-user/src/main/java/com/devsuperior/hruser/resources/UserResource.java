package com.devsuperior.hruser.resources;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserResource 
{
	
	// habilita o log nesta classe
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserResource.class);	
	
	@Autowired
	private Environment env; // informações de contexto da aplicação	
	
	@Autowired
	private UserRepository repository;
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id)
	{
					
		// imprime a porta que está rodando
		logger.info("porta = " + env.getProperty("local.server.port"));
		logger.info("executou findById {}", id);
			
		User obj = repository.findById(id).get();
		
		return ResponseEntity.ok(obj);				
		
	}	
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email)
	{
					
		// imprime a porta que está rodando
		logger.info("porta = " + env.getProperty("local.server.port"));
		logger.info("executou findByEmail {}", email);
			
		User obj = repository.findByEmail(email);
		
		return ResponseEntity.ok(obj);				
		
	}		
	
	
}
