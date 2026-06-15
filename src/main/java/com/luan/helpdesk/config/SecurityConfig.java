package com.luan.helpdesk.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.luan.helpdesk.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {
private final JwtAuthFilter jwtAuthFilter;
	
	public SecurityConfig (JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.csrf(csrf -> csrf.disable())
		.sessionManagement(sess -> 
		sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers("/", "/index.html", "/favicon.ico").permitAll()
				
				//Usuario
				.requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
				.requestMatchers("/usuarios/**").hasRole("ADMIN")
				//.requestMatchers(HttpMethod.POST,"/usuarios/**").permitAll()
				
				
				//Chamados
				.requestMatchers(HttpMethod.GET, "/chamados/**").permitAll()
				.requestMatchers("/chamados/**").hasAnyRole("TECNICO", "ADMIN")
				
				//Comentários
				.requestMatchers("/comentarios/**").permitAll()
				
				//Categorias
				.requestMatchers(HttpMethod.GET, "/categorias/**").permitAll()
				.requestMatchers("/categorias/**").hasAnyRole("TECNICO", "ADMIN")
				
				
				//Autenticação
				.requestMatchers("/auth/me").authenticated()
				.requestMatchers("/auth/login").permitAll()
				.anyRequest().authenticated()
				)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

			
		return http.build();
	}

	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("*"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setExposedHeaders(List.of("Authorization"));
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
