package br.com.rochasoft.hrpayroll.services;

import org.springframework.stereotype.Service;

import br.com.rochasoft.hrpayroll.entities.Payment;

@Service
public class PaymentService 
{
	
	public Payment getPayment(Long workerId, int days)
	{
		
		return new Payment("Anderson", 200.0, days);
		
	}

}
