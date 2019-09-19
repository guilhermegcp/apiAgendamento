//Classe com DTO
package com.agendamento.api;

import java.text.ParseException;
import java.util.ArrayList;
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

import com.agendamento.dto.AgendaManicureDTO;
import com.agendamento.model.AgendaManicure;
import com.agendamento.model.Manicure;
import com.agendamento.repository.AgendaManicureRepository;
import com.agendamento.repository.ManicureRepository;


@RestController
@RequestMapping("/agendaManicure")
@CrossOrigin(origins = "*")
public class AgendaManicureApi {
	
	@Autowired
	private ManicureRepository manicureRepository;
	
	@Autowired
	private AgendaManicureRepository repository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaAgendaManicure(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public AgendaManicure adicionar(@RequestBody AgendaManicureDTO agendaDTO) throws ParseException {
		
		return repository.save(this.converterDTOParaAgenda(agendaDTO));
		
	}
	
	@GetMapping(value="/listarTodos")
	public ResponseEntity<List<AgendaManicureDTO>> listar() {
		
		List<AgendaManicure> lista = repository.findAll();
		
		List<AgendaManicureDTO> listaDTO = new ArrayList<>();
		for(AgendaManicure agManicure : lista) {
			listaDTO.add(this.converterAgendaParaDTO(agManicure));
		}
	
		return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
	}
	
	@GetMapping(value="/pesquisarPorId/{id}")
	public ResponseEntity<AgendaManicureDTO> buscar(@PathVariable Long id) {
		Optional<AgendaManicure> opt = repository.findById(id);
		
		
		if (opt.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(this.converterAgendaParaDTO(opt.get()));
	}
	
	@PutMapping(value="/atualizarPorId/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
			@Valid @RequestBody AgendaManicureDTO manicureDTO) throws ParseException {
		Optional<AgendaManicure> existente = repository.findById(id);
		
		if (existente.get() == null) {
			return ResponseEntity.notFound().build();
		}
		
		AgendaManicureDTO tempAgendaManicure = this.converterAgendaParaDTO(existente.get());
		
		BeanUtils.copyProperties(manicureDTO, tempAgendaManicure, "id");
		
		repository.save(this.converterDTOParaAgenda(tempAgendaManicure));
		
		return ResponseEntity.ok("Salvo com Sucesso");
	}

	@DeleteMapping(value="/excluirPorId/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Optional<AgendaManicure> opt = repository.findById(id);
		
		if (opt == null) {
			return ResponseEntity.notFound().build();
		}
		
		repository.delete(opt.get());
		
		return ResponseEntity.noContent().build();
	}

	private AgendaManicureDTO converterAgendaParaDTO(AgendaManicure agendaManicure) {
		
		AgendaManicureDTO novaAgenda = new AgendaManicureDTO();
		
		novaAgenda.setId(agendaManicure.getId());
		novaAgenda.setData_servico(agendaManicure.getData_servico());
		novaAgenda.setHora_inicio(agendaManicure.getHora_inicio());
		novaAgenda.setHora_fim(agendaManicure.getHora_fim());
		novaAgenda.setStatus(agendaManicure.getStatus());
		novaAgenda.setId_manicure(agendaManicure.getManicure().getId());
		novaAgenda.setNome_manicure(agendaManicure.getManicure().getNome());
		
		return novaAgenda;
		
	}
	
	private AgendaManicure converterDTOParaAgenda(AgendaManicureDTO agendaManicureDTO) throws ParseException {
		AgendaManicure	novaAgenda = new AgendaManicure();
		
		novaAgenda.setId(agendaManicureDTO.getId());
		novaAgenda.setData_servico(agendaManicureDTO.getData_servico());
		novaAgenda.setHora_inicio(agendaManicureDTO.getHora_inicio());
		novaAgenda.setHora_fim(agendaManicureDTO.getHora_fim());
		novaAgenda.setStatus(agendaManicureDTO.getStatus());
		
		Manicure m = this.manicureRepository.findById(agendaManicureDTO.getId_manicure()).get();
		
		novaAgenda.setManicure(m);
		
		return novaAgenda;	
		
	}
}
