package com.luan.helpdesk.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.entity.*;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
	
	Optional<Usuario> findByEmail (String email);
	boolean existsByEmail(String email);
	List<Usuario> findByNome (String nome);
	List<Usuario> findByRole (Role role);
}
