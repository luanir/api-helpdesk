package com.luan.helpdesk.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.helpdesk.dto.LoginRequestDTO;
import com.luan.helpdesk.dto.LoginResponseDTO;
import com.luan.helpdesk.entity.Usuario;
import com.luan.helpdesk.repository.UsuarioRepository;
import com.luan.helpdesk.security.JwtService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	private final UsuarioRepository userRepo;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	
	public AuthController (UsuarioRepository userRepo, JwtService jwtService, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.jwtService = jwtService;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
		System.out.println("Chegou no login!");
		Usuario usuario = userRepo.findByEmail(dto.getEmail())
				.orElseThrow(()-> new RuntimeException("Login inválido"));
		
		if(!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
			throw new RuntimeException("Login inválido");
		}
		String token = jwtService.generateToken(usuario.getEmail());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@GetMapping("/me")
	public ResponseEntity<?> me(Authentication authentication){
		if(authentication == null) {
			return ResponseEntity.status(401).body(Map.of("erro", "Token ausente ou inválido"));
		}
		
		return ResponseEntity.ok(Map.of(
				"email", authentication.getName()));
	}
}
