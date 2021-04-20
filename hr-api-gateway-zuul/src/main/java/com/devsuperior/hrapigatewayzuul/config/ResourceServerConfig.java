package com.devsuperior.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	// rotas autorizadas para 'todos'
	private static final String[] PUBLIC   = { "/hr-oauth/oauth/token" };
	
	// rotas autorizadas para 'operador'
	private static final String[] OPERATOR = { "/hr-worker/**" };

	// rotas autorizadas para 'admin'
	private static final String[] ADMIN    = { "/hr-payroll/**", "/hr-user/**" };
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception 
	{
		
		resources.tokenStore(tokenStore);
		
	}

	@Override
	public void configure(HttpSecurity http) throws Exception 
	{
		
		http
		.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()             								// libera todos os caminhos
		.antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN")      // libera para operator ou admin no método GET
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated(); // qualquer outra rota, precisa estar autenticado
		
	}
	
	
	
	
	

}
