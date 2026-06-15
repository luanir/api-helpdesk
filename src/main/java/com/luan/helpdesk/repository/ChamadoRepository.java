package com.luan.helpdesk.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.entity.*;


@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
	List<Chamado> findByDataAbertura(LocalDateTime dataAbertura);
	List<Chamado> findByDataFechamento(LocalDateTime dataFechamento);
	List<Chamado> findByStatus(StatusChamado status);
	List<Chamado> findByPrioridade(PrioridadeChamado prioridade);
	List<Chamado> findByClienteId(Long clienteId);
	List<Chamado> findByTecnicoId(Long tecnicoId);
	List<Chamado> findByCategoriaId(Long categoriaId);
	List<Chamado> findByTitulo(String titulo);
	
	

}
