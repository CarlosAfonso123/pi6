package com.example.projeto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.model.Categoria;
import com.example.projeto.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	List<Categoria> listaCategorias = new ArrayList<Categoria>();
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping("/mensagem")
	public String mostrarMensagem() {
		String mensagem = "Bien venido al sistema!!";
		return mensagem;
	}
	
	@PostMapping("/incluir")
	public ResponseEntity incluirCategoria(@RequestBody Categoria categoria) {
		//System.out.println("nome Recebido: " + cli.getNome());
		System.out.println("NOME: " + categoria.getNome());
		//System.out.println("Endereço: " + categoria.getEndereco());
		
		//////adicionando o objeto cli na lista//////
		categoriaRepository.save(categoria);
		listaCategorias.add(categoria);
		return ResponseEntity.ok("Categoria criada ");
	}
	
	@GetMapping("/listagem")//chamar por get
	public List<Categoria> listarTudo(){
		
		listaCategorias = categoriaRepository.findAll();
		return listaCategorias;
	}
	
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id); //findbyid tem que usar optional
		if(categoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok("Categoria excluída com sucesso!");
			
		}else {
			return ResponseEntity.ok("Impossível excluir. Categoria não existe!!!");
		}
		
	}
	
	
	/*@PutMapping("/atualizar/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Cliente cliente)
	{
		Optional<Cliente> clienteExistente = cliRepository.findById(id);
		if(clienteExistente.isPresent())
		{
			//atualizar os atributos do objetos para enviar ao banco e atualizar
			clienteExistente.get().setNome(cliente.getNome());//pega o cliente e atualiza o nome
			clienteExistente.get().setEndereco(cliente.getEndereco());
			cliRepository.save(clienteExistente.get());
			return ResponseEntity.ok("Atualizado com sucesso!!!");
		}
		else
		{
			return ResponseEntity.ok("Impossível atualizar pois cliente não existe!");
		}*/
	}