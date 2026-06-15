package com.luan.helpdesk.service;
import static com.luan.helpdesk.exception.Exceptions.lanca;
//import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;


import com.luan.helpdesk.repository.*;
//import com.luan.helpdesk.enums.*;
import com.luan.helpdesk.dto.CategoriaRequestDTO;
import com.luan.helpdesk.entity.*;
import org.springframework.lang.NonNull;

@Service
public class CategoriaService {
	private final CategoriaRepository categoriaRepo;
	
	public CategoriaService (CategoriaRepository categoriaRepo) {
		this.categoriaRepo = categoriaRepo;
	}
	
	public Categoria create(CategoriaRequestDTO dto) {
		if(categoriaRepo.findByNome(dto.getNome()).size() > 0) {
			throw new RuntimeException("Categoria já criada!");
		}
		
		Categoria categoria = new Categoria();
		
		categoria.setNome(dto.getNome());
		categoria.setDescricao(dto.getDescricao());
			
		return categoriaRepo.save(categoria);
	}
	
	public List<Categoria> findByNome(String nome){
		return categoriaRepo.findByNome(nome);
	}
	
	public void deleteById(@NonNull Long id) {
		Categoria categoria = categoriaRepo.findById(id)
	            .orElseThrow(lanca("Categoria não encontrada!"));
		
		categoriaRepo.delete(categoria);
	}
	
	public List<Categoria> findAll(){
		return categoriaRepo.findAll();
	}
	
	public Categoria findById(Long id) {
		return categoriaRepo.findById(id)
				.orElseThrow(lanca("Categoria nao encontrada!"));
	}
	
	
	public Categoria updateAllInfo(Long id, String nome, String descricao) {
		Categoria categoria = categoriaRepo.findById(id)
				.orElseThrow(lanca("Categoria não encontrada!"));
		
		categoria.setNome(nome);
		categoria.setDescricao(descricao);
		
		return categoriaRepo.save(categoria);
	}
}
