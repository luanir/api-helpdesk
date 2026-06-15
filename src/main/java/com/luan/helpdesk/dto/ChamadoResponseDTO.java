package com.luan.helpdesk.dto;

import java.time.LocalDateTime;

import com.luan.helpdesk.enums.PrioridadeChamado;
import com.luan.helpdesk.enums.StatusChamado;

public class ChamadoResponseDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private StatusChamado status;

    private PrioridadeChamado prioridade;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    private Long clienteId;

    private String clienteNome;

    private Long tecnicoId;

    private String tecnicoNome;

    private Long categoriaId;

    private String categoriaNome;

   /* public ChamadoResponseDTO() {
    } */

    public ChamadoResponseDTO(
            Long id,
            String titulo,
            String descricao,
            StatusChamado status,
            PrioridadeChamado prioridade,
            LocalDateTime dataAbertura,
            LocalDateTime dataFechamento,
            Long clienteId,
            String clienteNome,
            Long tecnicoId,
            String tecnicoNome,
            Long categoriaId,
            String categoriaNome) {

        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.tecnicoId = tecnicoId;
        this.tecnicoNome = tecnicoNome;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
    }

   public Long getId() {
	   return id;
   }
   
   public String getTitulo () {
		return titulo;
	}
   public String getDescricao() {
		return descricao;
	}
   
   public StatusChamado getStatus() {
	   return status;
   }
   
   public PrioridadeChamado getPrioridade() {
	   return prioridade;
   }
   
   public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}
   
   public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	
   public Long getClienteId() {
		return clienteId;
	}
   
   public String getClienteNome() {
	   return clienteNome;
   }
   
   public Long getTecnicoId() {
		return tecnicoId;
	}
   
   public String getTecnicoNome() {
	   return tecnicoNome;
   }
   
   public Long getCategoriaId() {
		return categoriaId;
	}
   
   public String getCategoriaNome() {
	   return categoriaNome;
   }
}