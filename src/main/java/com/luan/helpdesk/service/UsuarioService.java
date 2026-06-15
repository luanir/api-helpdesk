package com.luan.helpdesk.service;
import static com.luan.helpdesk.exception.Exceptions.lanca;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;


import com.luan.helpdesk.repository.*;
import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.dto.UsuarioRequestDTO;
import com.luan.helpdesk.entity.*;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsuarioService {
	
	private final UsuarioRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	public UsuarioService (UsuarioRepository userRepo,PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Usuario create(UsuarioRequestDTO dto) {
		if(userRepo.existsByEmail(dto.getEmail())){
			throw new RuntimeException("Email já cadastrado!");
		}
		
		if (dto.getSenha() == null) {
		    throw new RuntimeException("Senha é obrigatória!");
		}
		
		Usuario usuario = new Usuario();
		
		
		
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
		usuario.setRole(dto.getRole());
		
		
		return userRepo.save(usuario);
	}
	
	
	public List<Usuario> findByNome(String nome){
		return userRepo.findByNome(nome);
	}
	
	
	public List<Usuario> findAll(){
		return userRepo.findAll();
	}
	
	
	public void deleteById(@NonNull Long id) {
		Usuario usuario = userRepo.findById(id)
	            .orElseThrow(lanca("Usuário não encontrado!"));
		
		userRepo.delete(usuario);
	}
	
	
	public Usuario findById(@NonNull Long id) {
		 return userRepo.findById(id)
				 .orElseThrow(lanca("Usuário não encontrado!"));
	}
	
	
	public Optional<Usuario> findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public List<Usuario> findByRole(Role roles){
		return userRepo.findByRole(roles);
	}
	
	public Usuario updateAllInfo(Long id, String nome, String email,String senha, Role role) {
		 Usuario usuario = userRepo.findById(id)
		            .orElseThrow(lanca("Usuário não encontrado!"));

		    usuario.setNome(nome);
		    usuario.setEmail(email);
		usuario.setSenha(senha);
		    usuario.setRole(role);

		
		return userRepo.save(usuario);
		
	}
}
