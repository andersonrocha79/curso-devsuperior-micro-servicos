package br.com.rochasoft.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rochasoft.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> 
{
	
	

}
