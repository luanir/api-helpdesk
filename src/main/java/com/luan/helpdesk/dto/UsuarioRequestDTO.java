package com.luan.helpdesk.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.luan.helpdesk.enums.Role;

public class UsuarioRequestDTO {

	private Long id;
	
	@NotBlank(message = "nome é obrigatória")
	@Size(min = 2, message = "Coloque um nome válido!")
	private String nome;
	
	@NotBlank(message = "Email é obrigatória")
	@Email	
	private String email;
	
	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 5, message ="Coloque uma senha válida")
	private String senha;
	
	//@Enumerated(EnumType.STRING)
	private Role role;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
}
