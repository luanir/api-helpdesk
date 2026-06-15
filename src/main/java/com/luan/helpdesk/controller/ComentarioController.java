package com.luan.helpdesk.controller;
import java.time.LocalDateTime;
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

import com.luan.helpdesk.dto.ComentarioRequestDTO;
import com.luan.helpdesk.dto.ComentarioResponseDTO;

import com.luan.helpdesk.entity.Comentario;
import com.luan.helpdesk.service.ComentarioService;


import jakarta.validation.Valid;


@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
	
	private final ComentarioService comentarioService;
	
	public ComentarioController (ComentarioService comentarioService) {
		this.comentarioService = comentarioService;
	}
	
	private ComentarioResponseDTO todto(Comentario comentario) {
	    return new ComentarioResponseDTO(
	            comentario.getId(),
	            comentario.getMensagem(),
	            comentario.getDataComentario(),
	            comentario.getChamado().getId(),
	            comentario.getUsuario().getId(),
	            comentario.getUsuario().getNome()
	    );
	}
	
	@PostMapping
	public ResponseEntity<ComentarioResponseDTO> create(@Valid @RequestBody @NonNull ComentarioRequestDTO dto) {
		Comentario comentario = comentarioService.create(dto);
		
		ComentarioResponseDTO response = todto(comentario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable @NonNull Long id){
		comentarioService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ComentarioResponseDTO>> findAll(){
		List<ComentarioResponseDTO> response = comentarioService.findAll()
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/datacomentario/{dataComentario}")
	public ResponseEntity<List<ComentarioResponseDTO>> findByDataComentario(@PathVariable @NonNull LocalDateTime dataComentario){
		List<ComentarioResponseDTO> response = comentarioService.findByDataComentario(dataComentario)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/chamado/{chamadoId}")
	public ResponseEntity<List<ComentarioResponseDTO>> findByChamadoId(@PathVariable @NonNull Long chamadoId){
		List<ComentarioResponseDTO> response = comentarioService.findByChamadoId(chamadoId)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<ComentarioResponseDTO>> findByUsuarioId(@PathVariable @NonNull Long usuarioId){
		List<ComentarioResponseDTO> response = comentarioService.findByUsuarioId(usuarioId)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ComentarioResponseDTO> updateAllInfo(
	        @PathVariable @NonNull Long id,
	        @Valid @RequestBody ComentarioRequestDTO dto) {

	    Comentario comentarioAtualizado = comentarioService.updateAllInfo(
	            id,
	            dto.getMensagem(),
	            dto.getChamadosId(),
	            dto.getUsuarioId()
	    );

	    return ResponseEntity.ok(todto(comentarioAtualizado));
	}
}
