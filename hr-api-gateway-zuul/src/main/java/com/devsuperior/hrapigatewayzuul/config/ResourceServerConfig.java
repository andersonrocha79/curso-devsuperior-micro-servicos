package com.devsuperior.hrapigatewayzuul.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
	private static final String[] ADMIN    = { "/hr-payroll/**", "/hr-user/**", "/actuator/**", "/hr-worker/actuator/**", "/hr-oauth/actuator/**" }; // tudo que vier do 'actuator', o admin pode executar
	
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
		
		// configura o 'cors' para permitir o acesso de outros dominios
		http.cors().configurationSource(corsConfigurationSource());
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource()
	{
		
		// Configurando as permissões de acesso a este servidor por servidores de outros domínios
		// Por padrão fica tudo 'bloqueado'
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("*"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		// indica que todos os caminhos seguirão as configurações definidas no 'corsConfig'
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter()
	{
		
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
		
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return bean;
		
	}
	
	
	
	

}
