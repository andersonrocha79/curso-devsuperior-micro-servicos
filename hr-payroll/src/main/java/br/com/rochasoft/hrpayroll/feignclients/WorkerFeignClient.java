package br.com.rochasoft.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.rochasoft.hrpayroll.entities.Worker;

// interface com os métodos 'disponíveis' no projeto 'hr-worker'

@Component // componente gerenciado pelo spring (será injetado em outras classes)
@FeignClient(name = "hr-worker", url = "localhost:8001", path = "/workers")
public interface WorkerFeignClient 
{
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Worker> findById(@PathVariable Long id);	

}
