package com.devsuperior.hruser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.hruser.entities.User;

// documentação do JPA
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface

public interface UserRepository extends JpaRepository<User, Long> 
{
	
	User findByEmail(String email);

}
