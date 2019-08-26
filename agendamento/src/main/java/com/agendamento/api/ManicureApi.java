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

import com.agendamento.model.Manicure;
import com.agendamento.repository.ManicureRepository;

@RestController
@RequestMapping("/manicure")
@CrossOrigin(origins = "*")
public class ManicureApi {

	@Autowired
	private ManicureRepository repository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaServico(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public Manicure adicionar(@Valid @RequestBody Manicure manicure) {
		return repository.save(manicure);
	}
	
	@GetMapping(value="/listarTodos")
	public ResponseEntity<List<Manicure>> listar() {
		
		List<Manicure> lista = repository.findAll();
	
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@GetMapping(value="/pesquisarPorId/{id}")
	public ResponseEntity<Optional<Manicure>> buscar(@PathVariable Long id) {
		Optional<Manicure> manicure = repository.findById(id);
		
		if (manicure == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(manicure);
	}
	
	@PutMapping(value="/atualizarPorId/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Manicure manicure) {
		Optional<Manicure> existente = repository.findById(id);
		
		if (existente.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Manicure tempServico = existente.get();
		
		BeanUtils.copyProperties(manicure, tempServico, "id");
		
		 repository.save(tempServico);
		
		return ResponseEntity.ok("Salvo com Sucesso");
	}

	@DeleteMapping(value="/excluirPorId/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<Manicure> manicure = repository.findById(id);
		
		if (manicure == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(manicure.get());
		
		return ResponseEntity.noContent().build();
	}
}

