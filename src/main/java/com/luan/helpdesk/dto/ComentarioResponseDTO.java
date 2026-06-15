package com.luan.helpdesk.dto;

import java.time.LocalDateTime;

public class ComentarioResponseDTO {

	private Long id;
	
	private String mensagem;
		
	private LocalDateTime dataComentario;
		
	private Long chamadosId;
	
	private Long usuarioId;
	
	private String usuarioNome;
	
	public ComentarioResponseDTO(Long id, String mensagem, LocalDateTime dataComentario, Long chamadosId, Long usuarioId, String usuarioNome) {
		this.id = id;
		this.mensagem = mensagem;
		this.dataComentario = dataComentario;
		this.chamadosId = chamadosId;
		this.usuarioId = usuarioId;
		this.usuarioNome = usuarioNome;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public LocalDateTime getDataComentario() {
		return dataComentario;
	}
	
	public Long getChamadoId() {
		return chamadosId;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	
	public String getUsuarioNome() {
		return usuarioNome;
	}
	
}
