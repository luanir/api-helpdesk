package com.luan.helpdesk.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

//import com.luan.helpdesk.entity.*;

@Entity
@Table (name = "comentarios")
public class Comentario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@NotBlank
	@Getter @Setter
	private String mensagem;
	
	@Getter @Setter
	private LocalDateTime dataComentario;
	
	@PrePersist
	public void prePersist() {
		dataComentario = LocalDateTime.now();
	}
	
	@ManyToOne
	@JoinColumn(name = "chamado_id")
	@Getter @Setter
	private Chamado chamado;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@Getter @Setter
	private Usuario usuario;
	
	public Comentario() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public LocalDateTime getDataComentario() {
		return dataComentario;
	}
	
	public void setDataComentario(LocalDateTime dataComentario) {
		this.dataComentario = dataComentario;
	}
	
	public Chamado getChamado() {
		return chamado;
	}
	
	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
