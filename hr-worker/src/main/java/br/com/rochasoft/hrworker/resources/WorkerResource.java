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
		
		// imprime a porta que está rodando
		logger.info("porta = " + env.getProperty("local.server.port"));		
		
		Worker obj = repository.findById(id).get();
		
		return ResponseEntity.ok(obj);				
		
	}	
	
}
