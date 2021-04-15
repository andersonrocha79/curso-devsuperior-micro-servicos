package com.devsuperior.hroauth.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.devsuperior.hroauth.entities.User;

//componente gerenciado pelo spring
@Component 
// classe de acesso ao microservico 'hr-user', no caminho '/users'
@FeignClient(name="hr-user", path = "/users")
public interface UserFeignClient 
{
	
	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);	
	

}
