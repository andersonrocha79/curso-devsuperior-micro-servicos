package br.com.rochasoft.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rochasoft.hrworker.entities.Worker;
import br.com.rochasoft.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource 
{
	
	// habilita o log nesta classe
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerResource.class);
	
	@Autowired
	private Environment env; // informações de contexto da aplicação
	
	@Autowired
	private WorkerRepository repository;
	
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAll()
	{
		
		List<Worker> list = repository.findAll();
		
		return ResponseEntity.ok(list);				
		
	}
	

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id)
	{
			
		logger.info("inicio método findById");
		
		// imprime a porta que está rodando
		logger.info("porta = " + env.getProperty("local.server.port"));
		
		// simulação de timeout
		// se o método não responder em 1 segundo, já deve executar o método alternativo
		// habilitando as configurações no application.properts, vai permitir executar com um tempo maior, sem gerar erro
		/*
		try 
		{
			Thread.sleep(3000L);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		// simulação de erro ocorrido
		// quando ocorre um erro no método já executa o método alternativo
		/*
		int x = 1;
		if (x == 1)	throw new RuntimeException("Erro teste hystrix");
		*/
		
		Worker obj = repository.findById(id).get();
		
		logger.info("final método findById");
		
		return ResponseEntity.ok(obj);				
		
	}	
	
}
