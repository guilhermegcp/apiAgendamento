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

import com.agendamento.model.Atendimento;
import com.agendamento.repository.AtendimentoRepository;

@RestController
@RequestMapping("/atendimento")
@CrossOrigin(origins = "*")
public class AtendimentoApi {
	
	@Autowired
	private AtendimentoRepository repository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaAtendimento(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public Atendimento adicionar(@Valid @RequestBody Atendimento atendimento) {
		return repository.save(atendimento);
	}
	
	@GetMapping(value="/listarTodos")
	public ResponseEntity<List<Atendimento>> listar() {
		
		List<Atendimento> lista = repository.findAll();
	
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(value="/listarPorCliente/{id}")
	public ResponseEntity<List<Atendimento>> listarPorCliente(@PathVariable Long id) {
		
		List<Atendimento> lista = repository.listarAtendimentoPorCliente(id);
		
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(value="/pesquisarPorId/{id}")
	public ResponseEntity<Optional<Atendimento>> buscar(@PathVariable Long id) {
		Optional<Atendimento> atendimento = repository.findById(id);
		
		if (atendimento == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(atendimento);
	}
	
	@PutMapping(value="/atualizarPorId/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Atendimento atendimento) {
		Optional<Atendimento> existente = repository.findById(id);
		
		if (existente.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Atendimento tempAtendimento = existente.get();
		
		BeanUtils.copyProperties(atendimento, tempAtendimento, "id");
		
		 repository.save(tempAtendimento);
		
		return ResponseEntity.ok("Salvo com Sucesso");
	}

	@DeleteMapping(value="/excluirPorId/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Atendimento> atendimento = repository.findById(id);
		
		if (atendimento == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(atendimento.get());
		
		return ResponseEntity.noContent().build();
		
		
	}

}
