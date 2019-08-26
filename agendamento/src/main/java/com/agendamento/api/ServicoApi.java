package com.agendamento.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agendamento.model.Servico;
import com.agendamento.repository.ServicoRepository;

@RestController
@RequestMapping("/servico")
@CrossOrigin(origins = "*")
public class ServicoApi {

	@Autowired
	private ServicoRepository repository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaServico(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public Servico adicionar(@RequestBody Servico servico) {
		return repository.save(servico);
	}
	
	@GetMapping(value="/listarTodos")
	public ResponseEntity<List<Servico>> listar() {
		
		List<Servico> lista = repository.findAll();
	
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(value="/pesquisarPorId/{id}")
	public ResponseEntity<Optional<Servico>> buscar(@PathVariable Long id) {
		Optional<Servico> servico = repository.findById(id);
		
		if (servico == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(servico);
	}
	
	@PutMapping(value="/atualizarPorId/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Servico servico) {
		Optional<Servico> existente = repository.findById(id);
		
		if (existente.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Servico tempServico = existente.get();
		
		BeanUtils.copyProperties(servico, tempServico, "id");
		
		 repository.save(tempServico);
		
		return ResponseEntity.ok("Salvo com Sucesso");
	}

	@DeleteMapping(value="/excluirPorId/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Servico> servico = repository.findById(id);
		
		if (servico == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(servico.get());
		
		return ResponseEntity.noContent().build();
	}
	
}
