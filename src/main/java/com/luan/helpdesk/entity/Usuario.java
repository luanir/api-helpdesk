package com.luan.helpdesk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import com.luan.helpdesk.enums.Role;

@Entity
@Table (name = "usuarios")
public class Usuario {
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;
	
	
	@NotBlank
	@Getter @Setter
	private String nome;
	
	@NotBlank
	@Email
	@Getter @Setter
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Getter @Setter
	private String senha;
	
	
	@Enumerated(EnumType.STRING)
	@Getter @Setter
	private Role role;


	public Usuario() {//Construtor Vazio
    	
    }

    public Long getId() {//getter
    	return id;
    }
    
    public void setId(Long id) {//setter
    	this.id = id;
    }
    
    public String getNome() {//getter
    	return nome;
    }
    
    public void setNome(String nome) {//setter
    	this.nome = nome;
    }
    
    public String getEmail() {//getter
    	return email;
    }
    
    public void setEmail(String email) {//setter
    	this.email = email;
    }
    
    public String getSenha () {//getter
    	return senha;
    }
    
    public void setSenha(String senha) {//setter
    	this.senha = senha;
    }
    
    public Role getRole() {
    	return role;
    }
    
    public void setRole(Role role) {
    	this.role = role;
    }
	
}
