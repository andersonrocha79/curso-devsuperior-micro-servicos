package br.com.rochasoft.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rochasoft.hrpayroll.entities.Payment;
import br.com.rochasoft.hrpayroll.entities.Worker;
import br.com.rochasoft.hrpayroll.feignclients.WorkerFeignClient;

@Service
public class PaymentService 
{
	
	@Autowired
	private WorkerFeignClient workerFeignClient;
	
	public Payment getPayment(Long workerId, int days)
	{
			
		Worker worker = workerFeignClient.findById(workerId).getBody();
				
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
	
	
	/*
	 * exemplo inicial com 'RestTemplate'
	 * 
	@Value("${hr-worker.host}")
	private String workerHost;
	 * 
	@Autowired
	private RestTemplate restTemplate;
	
	public Payment getPayment(Long workerId, int days)
	{
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", Long.toString(workerId));
		
		String req    = workerHost + "/workers/{id}";
		
		Worker worker = restTemplate.getForObject(req, Worker.class, uriVariables);
				
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
		
	}
	*/

}
