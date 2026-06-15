package com.luan.helpdesk.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luan.helpdesk.dto.ChamadoRequestDTO;
import com.luan.helpdesk.dto.ChamadoResponseDTO;
import com.luan.helpdesk.entity.Chamado;
import com.luan.helpdesk.enums.PrioridadeChamado;
import com.luan.helpdesk.enums.StatusChamado;
import com.luan.helpdesk.service.ChamadoService;

import jakarta.validation.Valid;

@CrossOrigin (origins = "*")
@RestController
@RequestMapping("/chamados")
public class ChamadoController {


private final ChamadoService chamadoService;
	
	public ChamadoController (ChamadoService chamadoService) {
		this.chamadoService = chamadoService;
		
	}
	
	private ChamadoResponseDTO todto(Chamado chamado) {
		return new ChamadoResponseDTO( chamado.getId(),
	            chamado.getTitulo(),
	            chamado.getDescricao(),
	            chamado.getStatus(),
	            chamado.getPrioridade(),
	            chamado.getDataAbertura(),
	            chamado.getDataFechamento(),
	            chamado.getCliente().getId(),
	            chamado.getCliente().getNome(),
	            chamado.getTecnico().getId(),
	            chamado.getTecnico().getNome(),
	            chamado.getCategoria().getId(),
	            chamado.getCategoria().getNome());
				
	}
	
	@PostMapping
	public ResponseEntity<ChamadoResponseDTO> create(@Valid @RequestBody @NonNull ChamadoRequestDTO dto){
		Chamado chamado = chamadoService.create(dto);
		
		ChamadoResponseDTO response = todto(chamado);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
				
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadoResponseDTO>> findAll(){
		List<ChamadoResponseDTO> response = chamadoService.findAll()
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ChamadoResponseDTO> findById(@PathVariable @NonNull Long id){
		Chamado chamado = chamadoService.findById(id);
		
		return ResponseEntity.ok(todto(chamado));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByTitulo(@PathVariable @NonNull String titulo){
		List<ChamadoResponseDTO> response = chamadoService.findByTitulo(titulo)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/tecnico/{tecnicoId}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByTecnicoId(@PathVariable @NonNull Long tecnicoId){
		List<ChamadoResponseDTO> response = chamadoService.findByTecnicoId(tecnicoId)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/cliente/{clienteId}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByClienteId(@PathVariable @NonNull Long clienteId){
		List<ChamadoResponseDTO> response = chamadoService.findByClienteId(clienteId)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/categoria/{categoriaId}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByCategoriaId(@PathVariable @NonNull Long categoriaId){
		List<ChamadoResponseDTO> response = chamadoService.findByCategoriaId(categoriaId)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/prioridade/{prioridade}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByPrioridade(@PathVariable @NonNull PrioridadeChamado prioridade){
		List<ChamadoResponseDTO> response = chamadoService.findByPrioridade(prioridade)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByStatus(@PathVariable @NonNull StatusChamado status){
		List<ChamadoResponseDTO> response = chamadoService.findByStatus(status)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/dataabertura/{dataAbertura}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByDataAbertura(@PathVariable @NonNull LocalDateTime dataAbertura){
		List<ChamadoResponseDTO> response = chamadoService.findByDataAbertura(dataAbertura)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/datafechamento/{dataFechamento}")
	public ResponseEntity<List<ChamadoResponseDTO>> findByDataFechamento(@PathVariable @NonNull LocalDateTime dataFechamento){
		List<ChamadoResponseDTO> response = chamadoService.findByDataFechamento(dataFechamento)
				.stream()
				.map(this::todto)
				.toList();
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Void>deleteById(@PathVariable @NonNull Long id) {
		chamadoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/fechar")
	public ResponseEntity<ChamadoResponseDTO> fecharChamado(@PathVariable Long id) {
	    Chamado chamado = chamadoService.fecharChamado(id);

	    return ResponseEntity.ok(todto(chamado));
	}
	
	@PatchMapping("/{id}/abrir")
	public ResponseEntity<ChamadoResponseDTO> abrirChamado(@PathVariable Long id) {
	    Chamado chamado = chamadoService.abrirChamado(id);

	    return ResponseEntity.ok(todto(chamado));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChamadoResponseDTO> updateAllInfo(@PathVariable @NonNull Long id,@Valid @RequestBody ChamadoRequestDTO dto){
		Chamado chamadoAtualizado = chamadoService.updateAllInfo(id,
				dto.getTitulo(),
				dto.getDescricao(),
				dto.getCategoriaId(),
				dto.getPrioridade(),
				dto.getClienteId(),
				dto.getTecnicoId());
		
		return ResponseEntity.ok(todto(chamadoAtualizado));
	}
}