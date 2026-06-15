package com.luan.helpdesk.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
//import java.util.Map;
import java.util.List;

import com.luan.helpdesk.service.*;
import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.entity.*;
import com.luan.helpdesk.dto.*;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService userService;
	
	public UsuarioController(UsuarioService userService) {
		this.userService = userService;
	}
	
	private UsuarioResponseDTO todto(Usuario usuario) {
		return new UsuarioResponseDTO(usuario.getId(),
				usuario.getNome(),
				usuario.getEmail(),
				usuario.getRole());
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody @NonNull UsuarioRequestDTO dto) {
		Usuario usuario = userService.create(dto);
		
		UsuarioResponseDTO response = todto(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> findAll(){
		List<UsuarioResponseDTO> response = userService.findAll()
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable @NonNull Long id){
		Usuario usuario = userService.findById(id);
		
		return ResponseEntity.ok(todto(usuario));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Void>deleteById(@PathVariable @NonNull Long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioResponseDTO> findByEmail(@PathVariable @NonNull String email){
		Usuario usuario = userService.findByEmail(email)
				.orElseThrow(()-> new RuntimeException ("Usuario não encontrado!"));
		
		return ResponseEntity.ok(todto(usuario));
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioResponseDTO>> findByNome(@PathVariable @NonNull String nome){
		List<UsuarioResponseDTO> response = userService.findByNome(nome)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<UsuarioResponseDTO>> findByRole(@PathVariable Role role){
		List<UsuarioResponseDTO> response = userService.findByRole(role)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}") //muda tudo
	public ResponseEntity<UsuarioResponseDTO> updateAllInfo(@PathVariable @NonNull Long id,@Valid @RequestBody UsuarioRequestDTO dto){
		 Usuario usuarioAtualizado = userService.updateAllInfo(
		            id,
		            dto.getNome(),
		            dto.getEmail(),
		            dto.getSenha(),
		            dto.getRole()
		    );


		    return ResponseEntity.ok(todto(usuarioAtualizado));
	}
	
}
