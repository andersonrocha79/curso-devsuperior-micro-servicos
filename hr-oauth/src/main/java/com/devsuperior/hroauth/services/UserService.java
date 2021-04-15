package com.devsuperior.hroauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.hroauth.entities.User;
import com.devsuperior.hroauth.feignClients.UserFeignClient;

@Service // componente do spring
public class UserService 
{
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	// este método executa o 'findByEmail' do 'userFeign', que está configurado
	// para executar o método 'http' 'get' no micro serviço 'hr-user' e trazer o usuário por 'email'
	public User findByEmail(String email)
	{
				
		User user = userFeignClient.findByEmail(email).getBody();
		
		if (user == null)
		{
			logger.error("e-mail não encontrado {}", email);
			throw new IllegalArgumentException("e-mail não encontrado");
		}
		
		logger.error("e-mail encontrado {}", email);
		return user;
		
	}

}
