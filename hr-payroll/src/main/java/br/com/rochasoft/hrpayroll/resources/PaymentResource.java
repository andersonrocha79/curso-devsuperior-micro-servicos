package br.com.rochasoft.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.rochasoft.hrpayroll.entities.Payment;
import br.com.rochasoft.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource 
{
	
	@Autowired
	private PaymentService service;
	
	
	@HystrixCommand(fallbackMethod = "getPaymentAlternative") // habilita o método alternativo
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> getPayment(@PathVariable Long    workerId,
			                                  @PathVariable Integer days )
	{
		
		Payment payment = service.getPayment(workerId, days);
		return ResponseEntity.ok(payment);
			
	}
	
	public ResponseEntity<Payment> getPaymentAlternative(Long    workerId,
			                                             Integer days )
	{
		
		// implementação alternativa, caso o método getPayment gere problemas (fora do ar)		
		Payment payment = new Payment("Executou pagamento Alternativo", 400.0, days);
		return ResponseEntity.ok(payment);
			
	}
	
}
