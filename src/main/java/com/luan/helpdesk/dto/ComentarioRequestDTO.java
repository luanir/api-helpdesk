package com.luan.helpdesk.dto;




public class ComentarioRequestDTO {

	
		
	private String mensagem;
		
	private Long chamadosId;
	
	private Long usuarioId;
	
	
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public Long getChamadosId() {
		return chamadosId;
	}
	
	public void setChamadosId(Long chamadosId) {
		this.chamadosId = chamadosId;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
