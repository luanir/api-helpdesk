package com.luan.helpdesk.dto;

//import com.luan.helpdesk.entity.Categoria;
//import com.luan.helpdesk.entity.Usuario;
import com.luan.helpdesk.enums.PrioridadeChamado;

import jakarta.validation.constraints.NotBlank;


public class ChamadoRequestDTO {
	@NotBlank(message = "Título é obrigatório")
    private String titulo;
	
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;
   
   //@NotBlank(message = "Prioridade é obrigatório")
    private PrioridadeChamado prioridade;
    
    //@NotBlank(message = "Cliente é obrigatório")
    private Long clienteId;
    
   // @NotBlank(message = "Tecnico é obrigatório")
    private Long tecnicoId;

    private Long categoriaId;
    
    public ChamadoRequestDTO() {
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
	
	public PrioridadeChamado getPrioridade() {
		return prioridade;
	}
	
	public void setPrioridade(PrioridadeChamado prioridade) {
		this.prioridade = prioridade;
	}
	
	public Long getTecnicoId() {
		return tecnicoId;
	}
	
	public void setTecnicoId(Long tecnicoId) {
		this.tecnicoId = tecnicoId;
	}
	
	public Long getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
}