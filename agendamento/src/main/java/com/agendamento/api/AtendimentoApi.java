package com.agendamento.api;

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

import com.agendamento.dto.AtendimentoDTO;
import com.agendamento.model.AgendaManicure;
import com.agendamento.model.Atendimento;
import com.agendamento.model.Cliente;
import com.agendamento.model.Manicure;
import com.agendamento.model.Servico;
import com.agendamento.repository.AtendimentoRepository;
import com.agendamento.repository.ClienteRepository;
import com.agendamento.repository.ManicureRepository;
import com.agendamento.repository.ServicoRepository;
import com.agendamento.repository.AgendaManicureRepository;

@RestController
@RequestMapping("/atendimento")
@CrossOrigin(origins = "*")
public class AtendimentoApi {
	
	@Autowired
	private AtendimentoRepository repository;
	
	@Autowired
	private ManicureRepository manicureRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private AgendaManicureRepository AgendaManicureRepository;
	
	@GetMapping(value="/")
	public ResponseEntity<String> verificaAtendimento(){
		return ResponseEntity.status(HttpStatus.OK).body("Serviço Operacional");
	}
	
	@PostMapping(value="/inserir")
	public Atendimento adicionar(@RequestBody AtendimentoDTO atendimentoDTO) {
		return repository.save(this.converterDTOParaAtendimento(atendimentoDTO));
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

	private AtendimentoDTO converterAtendimentoParaDto(Atendimento atendimento) {
		
		AtendimentoDTO novoAtendimento = new AtendimentoDTO();
		
		novoAtendimento.setId(atendimento.getId());
		novoAtendimento.setId_cliente(atendimento.getCliente().getId());
		novoAtendimento.setId_manicure(atendimento.getManicure().getId());
		novoAtendimento.setId_servico(atendimento.getServico().get(0).getId());
		novoAtendimento.setStatus_atendimento(atendimento.getStatus());
		novoAtendimento.setData_servico(atendimento.getData_servico());
		novoAtendimento.setHora_fim(atendimento.getHora_fim());
		novoAtendimento.setHora_inicio(atendimento.getHora_inicio());
		novoAtendimento.setNome_manicure(atendimento.getManicure().getNome());
		
		return novoAtendimento;
	}
	
	private Atendimento converterDTOParaAtendimento (AtendimentoDTO atendimentoDTO) {
		
		Atendimento novoAtendimento = new Atendimento();
		
		novoAtendimento.setId(atendimentoDTO.getId());
		novoAtendimento.setStatus(atendimentoDTO.getStatus_atendimento());
		
		if (atendimentoDTO.getId_agenda_manicure() == null ) {
			novoAtendimento.setData_servico(atendimentoDTO.getData_servico());
			novoAtendimento.setHora_fim(atendimentoDTO.getHora_fim());
			novoAtendimento.setHora_inicio(atendimentoDTO.getHora_inicio());
		}else {
			AgendaManicure am = this.AgendaManicureRepository.findById(atendimentoDTO.getId_agenda_manicure()).get();
			
			novoAtendimento.setData_servico(am.getData_servico());
			novoAtendimento.setHora_fim(am.getHora_fim());
			novoAtendimento.setHora_inicio(am.getHora_inicio());
			
		}
		
		Manicure m = this.manicureRepository.findById(atendimentoDTO.getId_manicure()).get();
		novoAtendimento.setManicure(m);
		
		//Cliente c = this.clienteRepository.findById(atendimentoDTO.getId_cliente()).get();
		Cliente c = this.clienteRepository.findByIdFacebook(atendimentoDTO.getId_cliente().toString());
		novoAtendimento.setCliente(c);
		
		List<Servico> listaS = new ArrayList<Servico>();
		Servico s = this.servicoRepository.findById(atendimentoDTO.getId_servico()).get(); 
		listaS.add(s);
		novoAtendimento.setServico(listaS);
		
		return novoAtendimento;
		
	}
}
