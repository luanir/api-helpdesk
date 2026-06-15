package com.luan.helpdesk.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luan.helpdesk.entity.Comentario;

public interface ComentarioRepository extends JpaRepository <Comentario, Long>{

	List<Comentario> findByDataComentario (LocalDateTime dataComentario);
	List<Comentario> findByChamadoId (Long chamadoId);
	List<Comentario> findByUsuarioId (Long usuarioId);
}
