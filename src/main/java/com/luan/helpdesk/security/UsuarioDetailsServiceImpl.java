package com.luan.helpdesk.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luan.helpdesk.entity.Usuario;
import com.luan.helpdesk.repository.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
	private final UsuarioRepository userRepo;
	
	public UsuarioDetailsServiceImpl (UsuarioRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = userRepo.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado!"));
		return org.springframework.security.core.userdetails.User
				.withUsername(usuario.getEmail())
				.password(usuario.getSenha())
				.roles(usuario.getRole().name())
				.build();
}
}
