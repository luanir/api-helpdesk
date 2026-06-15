package com.luan.helpdesk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import com.luan.helpdesk.enums.*;

import java.time.LocalDateTime;

//import com.luan.helpdesk.entity.*;

@Entity
@Table (name = "chamados")
public class Chamado {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	@NotBlank
	@Getter @Setter
	private String titulo;
	
	@NotBlank
	@Getter @Setter
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private StatusChamado status;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private PrioridadeChamado prioridade;
	
	@Column(name = "data_abertura")
	private LocalDateTime dataAbertura;

	@Column(name = "data_fechamento")
	private LocalDateTime dataFechamento;
		
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	@Getter @Setter
	private Usuario tecnico;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@Getter @Setter
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	@Getter @Setter
	private Categoria categoria;
	
	@PrePersist
	public void prePersist() {
		if(dataAbertura == null) {
			dataAbertura = LocalDateTime.now();
		}
		
		if (status == null) {
			status = StatusChamado.ABERTO;
		}
	}
	
	public Chamado() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo () {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public StatusChamado getStatus() {
		return status;
	}
	
	public void setStatus (StatusChamado status) {
		this.status = status;
	}
	
	public PrioridadeChamado getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(PrioridadeChamado prioridade) {
		this.prioridade = prioridade;
	}
	
	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	public Usuario getTecnico() {
		return tecnico;
	}
	
	public void setTecnico(Usuario tecnico) {
		this.tecnico = tecnico;
	}
	
	public Usuario getCliente () {
		return cliente;
	}
	
	public void setCliente (Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
