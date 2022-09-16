package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projeto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	

}
