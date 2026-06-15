package com.luan.helpdesk.service;
import static com.luan.helpdesk.exception.Exceptions.lanca;
//import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


import com.luan.helpdesk.repository.*;
//import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.dto.ComentarioRequestDTO;
import com.luan.helpdesk.entity.*;
//import org.springframework.lang.NonNull;

@Service
public class ComentarioService {
	
	private final ComentarioRepository comentarioRepo;
	private final ChamadoRepository chamadoRepo;
	private final UsuarioRepository userRepo;
	
	public ComentarioService (ComentarioRepository comentarioRepo, ChamadoRepository chamadoRepo, UsuarioRepository userRepo) {
		this.comentarioRepo = comentarioRepo;
		this.chamadoRepo = chamadoRepo;
		this.userRepo = userRepo;
	}
	
	public Comentario create(ComentarioRequestDTO dto) {

	    System.out.println(dto.getMensagem());
	    System.out.println(dto.getChamadosId());
	    System.out.println(dto.getUsuarioId());

	    Chamado chamado = chamadoRepo.findById(dto.getChamadosId())
	            .orElseThrow(() -> new RuntimeException("Não achou o chamado!"));

	    Usuario usuario = userRepo.findById(dto.getUsuarioId())
	            .orElseThrow(() -> new RuntimeException("Não achou o usuário!"));

	    Comentario comentario = new Comentario();

	    comentario.setMensagem(dto.getMensagem());
	    comentario.setChamado(chamado);
	    comentario.setUsuario(usuario);

	    return comentarioRepo.save(comentario);
	}
	public List<Comentario> findAll(){
		return comentarioRepo.findAll();
	}

	public List<Comentario> findByDataComentario(LocalDateTime dataComentario){
		return comentarioRepo.findByDataComentario(dataComentario);
	}
	
	public void deleteById(@NonNull Long id) {
		Comentario comentario = comentarioRepo.findById(id)
	            .orElseThrow(lanca("Comentário não encontrado!"));
		
		comentarioRepo.delete(comentario);
	}
	
	public List<Comentario> findByChamadoId(Long chamadoId){
		return comentarioRepo.findByChamadoId(chamadoId);
	}
	
	public List<Comentario> findByUsuarioId(Long usuarioId){
		return comentarioRepo.findByUsuarioId(usuarioId);
	}
	
	public Comentario updateAllInfo(Long id, String mensagem, Long chamadoId, Long usuarioId) {
	    Comentario comentario = comentarioRepo.findById(id)
	            .orElseThrow(lanca("Comentário não encontrado!"));

	    Chamado chamado = chamadoRepo.findById(chamadoId)
	            .orElseThrow(lanca("Chamado não encontrado!"));

	    Usuario usuario = userRepo.findById(usuarioId)
	            .orElseThrow(lanca("Usuário não encontrado!"));

	    comentario.setMensagem(mensagem);
	    comentario.setChamado(chamado);
	    comentario.setUsuario(usuario);

	    return comentarioRepo.save(comentario);
	}
}
