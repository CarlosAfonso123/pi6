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

import com.example.projeto.model.Produto;
import com.example.projeto.repository.ProdutoRepository;

@RestController
@RequestMapping("/product")
public class ProdutoController {
	List<Produto> listaProdutos = new ArrayList<Produto>();
	
	@Autowired
	ProdutoRepository productRepository;
	
	@GetMapping("/mensagem")
	public String mostrarMensagem() {
		String mensagem = "Bien venido al sistema!!";
		return mensagem;
	}
	
	@PostMapping("/incluir")
	public ResponseEntity incluirProduto(@RequestBody Produto prod) {
		//System.out.println("nome Recebido: " + cli.getNome());
		System.out.println("Nome produto: " + prod.getNome());
		System.out.println("Descrição do Produto: " + prod.getDescricao());
		System.out.println("Valor: " + prod.getValor());
		//////adicionando o objeto cli na lista//////
		productRepository.save(prod);
		listaProdutos.add(prod);
		return ResponseEntity.ok("Produto Incluido Com Successo!");
	}
	
	@GetMapping("/listagem")//chamar por get
	public List<Produto> listarTudo(){
		
		listaProdutos = productRepository.findAll();
		return listaProdutos;
	}
	
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity excluir(@PathVariable Long id) {
		Optional<Produto> prod = productRepository.findById(id); //findbyid tem que usar optional
		if(prod.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok("Produto excluído com sucesso!");
			
		}else {
			return ResponseEntity.ok("Impossível excluir. Produto inexistente!!!");
		}
		
	}
	
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity atualizar(@PathVariable Long id, @RequestBody Produto produto)
	{
		Optional<Produto> produtoExistente = productRepository.findById(id);
		if(produtoExistente.isPresent())
		{
			//atualizar os atributos do objetos para enviar ao banco e atualizar
			produtoExistente.get().setNome(produto.getNome());//pega o cliente e atualiza o nome
			produtoExistente.get().setDescricao(produto.getDescricao());
			produtoExistente.get().setValor(produto.getValor());
			productRepository.save(produtoExistente.get());
			return ResponseEntity.ok("Produto atualizado com sucesso!!!");
		}
		else
		{
			return ResponseEntity.ok("Impossível atualizar pois produto não existe!");
		}
	}
}
