package com.luan.helpdesk.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import static com.luan.helpdesk.exception.Exceptions.lanca;
import com.luan.helpdesk.repository.*;
import java.util.List;
import java.time.LocalDateTime;
import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.entity.*;
import com.luan.helpdesk.dto.*;

@Service
public class ChamadoService {
	
	private final ChamadoRepository chamadoRepo;
	//private final ComentarioRepository comentarioRepo;
	private final UsuarioRepository userRepo;
	private final CategoriaRepository categoriaRepo;
	
	public ChamadoService (ChamadoRepository chamadoRepo, UsuarioRepository userRepo, CategoriaRepository categoriaRepo) {
		this.chamadoRepo = chamadoRepo;
		this.userRepo = userRepo;
		this.categoriaRepo = categoriaRepo;
	}
	
	public Chamado create(ChamadoRequestDTO dto) {
		Usuario usuario = userRepo.findById(dto.getClienteId())
				.orElseThrow(lanca("Usuário não encontrado!"));
	
		Usuario tecnico = userRepo.findById(dto.getTecnicoId())
				.orElseThrow(lanca("Tecnico não encontrado!"));
		
		Categoria categoria = categoriaRepo.findById(dto.getCategoriaId())
	            .orElseThrow(lanca("Categoria não encontrada!"));
		
		Chamado chamado = new Chamado();
		
		chamado.setTitulo(dto.getTitulo());
		chamado.setDescricao(dto.getDescricao());
		chamado.setCategoria(categoria);
		chamado.setPrioridade(dto.getPrioridade());
		chamado.setCliente(usuario);
		chamado.setTecnico(tecnico);
		
		
				
		
		return chamadoRepo.save(chamado);
	}
	
	public List<Chamado> findAll(){
		return chamadoRepo.findAll();
	}
	
	public List<Chamado> findByDataAbertura(LocalDateTime dataAbertura){
		return chamadoRepo.findByDataAbertura(dataAbertura);
	}
	
	public List<Chamado> findByDataFechamento(LocalDateTime dataFechamento){
		return chamadoRepo.findByDataFechamento(dataFechamento);
	}
	
	public List<Chamado> findByStatus(StatusChamado status){
		return chamadoRepo.findByStatus(status);
	}
	
	public List<Chamado> findByPrioridade(PrioridadeChamado prioridade){
		return chamadoRepo.findByPrioridade(prioridade);
	}
	
	public List<Chamado> findByClienteId(Long clienteId){
		return chamadoRepo.findByClienteId(clienteId);
	}
	
	public List<Chamado> findByTecnicoId(Long tecnicoId){
		return chamadoRepo.findByTecnicoId(tecnicoId);
	}
	
	public List<Chamado> findByCategoriaId(Long categoriaId){
		return chamadoRepo.findByCategoriaId(categoriaId);
	}
	
	public List<Chamado> findByTitulo(String titulo) {
		return chamadoRepo.findByTitulo(titulo);
	}
	
	public Chamado findById(@NonNull Long id) {
		 return chamadoRepo.findById(id)
				 .orElseThrow(lanca("Chamado não encontrado!"));
	}
	
	public void deleteById(@NonNull Long id) {
	    Chamado chamado = chamadoRepo.findById(id)
	            .orElseThrow(lanca("Chamado não encontrado!"));

	    chamadoRepo.delete(chamado);
	}
	
	public Chamado fecharChamado(Long id) {
	    Chamado chamado = chamadoRepo.findById(id)
	            .orElseThrow(lanca("Chamado não encontrado!"));

	    chamado.setStatus(StatusChamado.FECHADO);
	    chamado.setDataFechamento(LocalDateTime.now());

	    return chamadoRepo.save(chamado);
	}
	
	public Chamado abrirChamado(Long id) {
		Chamado chamado = chamadoRepo.findById(id)
				.orElseThrow(lanca("Chamado não encontrado!"));
		
		chamado.setStatus(StatusChamado.ABERTO);
		chamado.setDataFechamento(null);
		
		return chamadoRepo.save(chamado);
	}
	
	public Chamado updateAllInfo(@NonNull Long id, String titulo, String descricao, Long categoriaId, PrioridadeChamado prioridade, Long usuarioId, Long tecnicoId) {
		Chamado chamado = chamadoRepo.findById(id)
				.orElseThrow(lanca("Chamado não encontrado!"));
		
		Categoria categoria = categoriaRepo.findById(categoriaId)
				.orElseThrow(lanca("Categoria não encontrada!"));
		
		Usuario usuario = userRepo.findById(usuarioId)
				.orElseThrow(lanca("Usuário não encontrado!"));
	
		Usuario tecnico = userRepo.findById(tecnicoId)
				.orElseThrow(lanca("Tecnico não encontrado!"));
		
		
		
		chamado.setTitulo(titulo);
		chamado.setDescricao(descricao);
		chamado.setCategoria(categoria);
		chamado.setPrioridade(prioridade);
		chamado.setCliente(usuario);
		chamado.setTecnico(tecnico);
		
		return chamadoRepo.save(chamado);
		
	}
	
}
