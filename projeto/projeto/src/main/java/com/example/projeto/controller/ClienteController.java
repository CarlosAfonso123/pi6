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

import com.example.projeto.model.Cliente;
import com.example.projeto.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	@Autowired
	ClienteRepository cliRepository;
	
	@GetMapping("/mensagem")
	public String mostrarMensagem() {
		String mensagem = "Bien venido al sistema!!";
		return mensagem;
	}
	
	@PostMapping("/incluir")
	public ResponseEntity incluirCliente(@RequestBody Cliente cli) {
		//System.out.println("nome Recebido: " + cli.getNome());
		System.out.println("NOME: " + cli.getNome());
		System.out.println("Endereço: " + cli.getEndereco());
		
		//////adicionando o objeto cli na lista//////
		cliRepository.save(cli);
		listaClientes.add(cli);
		return ResponseEntity.ok("Cliente funcionou ");
	}
	
	@GetMapping("/listagem")//chamar por get
	public List<Cliente> listarTudo(){
		
		listaClientes = cliRepository.findAll();
		return listaClientes;
	}
	
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		Optional<Cliente> cli = cliRepository.findById(id); //findbyid tem que usar optional
		if(cli.isPresent()) {
			cliRepository.deleteById(id);
			return ResponseEntity.ok("Cliente excluírdo com sucesso! Aurevoir");
			
		}else {
			return ResponseEntity.ok("Impossível excluir. Cliente não existe!!!");
		}
		
	}
	
	
	@PutMapping("/atualizar/{id}")
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
		}
	}
}
