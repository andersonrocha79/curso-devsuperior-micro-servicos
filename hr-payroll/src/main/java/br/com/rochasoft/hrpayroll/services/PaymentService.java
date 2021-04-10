package br.com.rochasoft.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rochasoft.hrpayroll.entities.Payment;
import br.com.rochasoft.hrpayroll.entities.Worker;

@Service
public class PaymentService 
{
	
	@Value("${hr-worker.host}")
	private String workerHost;
	
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

}
