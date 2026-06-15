package com.luan.helpdesk.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.helpdesk.dto.CategoriaRequestDTO;
import com.luan.helpdesk.dto.CategoriaResponseDTO;
import com.luan.helpdesk.entity.Categoria;
import com.luan.helpdesk.service.*;
@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	private final CategoriaService categoriaService;
	
	public CategoriaController (CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	private CategoriaResponseDTO todto(Categoria categoria) {
		return new CategoriaResponseDTO (categoria.getId(),
				categoria.getNome(),
				categoria.getDescricao()
				);
	}
	
	
	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> create (@Valid @RequestBody @NonNull CategoriaRequestDTO dto) {
		Categoria categoria = categoriaService.create(dto);
		
		CategoriaResponseDTO response = todto(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaResponseDTO>> findAll(){
		List<CategoriaResponseDTO> response = categoriaService.findAll()
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<CategoriaResponseDTO>> findByNome(@PathVariable @NonNull String nome){
		List<CategoriaResponseDTO> response = categoriaService.findByNome(nome)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable @NonNull Long id){
		Categoria categoria = categoriaService.findById(id);
		
		return ResponseEntity.ok(todto(categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		categoriaService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaResponseDTO> updateAllInfo(@PathVariable @NonNull Long id, @Valid @RequestBody CategoriaRequestDTO dto) {
		Categoria categoriaAtualizado = categoriaService.updateAllInfo(id,
				dto.getNome(),
				dto.getDescricao()
				);
		
		return ResponseEntity.ok(todto(categoriaAtualizado));
	}
}
