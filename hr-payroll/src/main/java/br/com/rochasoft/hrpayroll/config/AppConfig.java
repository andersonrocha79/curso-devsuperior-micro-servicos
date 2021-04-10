package br.com.rochasoft.hrpayroll.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig 
{
	
	// gera uma instância única de 'RestTemplate' (singleton)
	// cria um componente a partir de um método
	@Bean
	public RestTemplate restTemplate()
	{
		
		return new RestTemplate();
		
	}
	

}
