package com.agendamento.api;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agendamento.model.Cliente;
import com.agendamento.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteApi {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaServico(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return repository.save(cliente);
	}
	
	@GetMapping(value="/listarTodos")
	public ResponseEntity<List<Cliente>> listar() {
		
		List<Cliente> lista = repository.findAll();
	
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(value="/pesquisarPorId/{id}")
	public ResponseEntity<Optional<Cliente>> buscar(@PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping(value="/atualizarPorId/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Cliente cliente) {
		Optional<Cliente> existente = repository.findById(id);
		
		if (existente.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente tempCliente = existente.get();
		
		BeanUtils.copyProperties(cliente, tempCliente, "id");
		
		 repository.save(tempCliente);
		
		return ResponseEntity.ok("Salvo com Sucesso");
	}

	@DeleteMapping(value="/excluirPorId/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(cliente.get());
		
		return ResponseEntity.noContent().build();
	}

}
