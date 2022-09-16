package com.example.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projeto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
