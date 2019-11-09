//Classe com DTO
package com.agendamento.api;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.json.JSONParser;
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
import com.agendamento.model.Servico;
import com.agendamento.modelMesagesQuick.Atributos;
import com.agendamento.modelMesagesQuick.MessagesQuick;
import com.agendamento.modelMesagesQuick.QuickReplie;
import com.agendamento.modelMesagesQuick.QuickReplyOptions;
import com.agendamento.modelMesagesQuick.RetornoQuick;
import com.agendamento.modelMessagesBtn.Attachment;
import com.agendamento.modelMessagesBtn.Buttons;
import com.agendamento.modelMessagesBtn.MessagesBtn;
import com.agendamento.modelMessagesBtn.Payload;
import com.agendamento.modelMessagesBtn.RetornoBtn;
import com.agendamento.modelMessagesTxt.Messages;
import com.agendamento.modelMessagesTxt.Text;
import com.agendamento.repository.AgendaManicureRepository;
import com.agendamento.repository.ManicureRepository;
import com.agendamento.repository.ServicoRepository;
import com.google.gson.Gson;


@RestController
@RequestMapping("/agendaManicure")
@CrossOrigin(origins = "*")
public class AgendaManicureApi {
	
	@Autowired
	private ManicureRepository manicureRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
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
	
	@GetMapping(value = "/listarMsg")
	public ResponseEntity<Messages> listaMsg() {

		Gson gson = new Gson();

		String msg = "{\"messages\": [{\"text\": \"Welcome to the Chatfuel Rockets!\"},{\"text\": \"What are you up to?\"}]}";

		Messages m = gson.fromJson(msg, Messages.class);

		Messages m2 = new Messages();

		List<Text> lista = new ArrayList<>();

		Text t1 = new Text();

		t1.setText("Ola!");

		lista.add(t1);

		m2.setMessages(lista);

		return ResponseEntity.ok(m2);
	}
	
	@GetMapping(value = "/listarBtn")
	public ResponseEntity<RetornoBtn> listaBtn() {

		Gson gson = new Gson();
		
		RetornoBtn rBtn = new RetornoBtn();
		
		List<MessagesBtn> listaMessagesBtn = new ArrayList<MessagesBtn>();
		
		MessagesBtn m = new MessagesBtn();

		Attachment a1 = new Attachment();
		
		a1.setType("template");

		Payload p1 = new Payload();
		
		p1.setTemplate_type("button");
		
		p1.setText("Hello!");
		
		List<Buttons> listaButtons = new ArrayList<>();
		
		List<String> blockNames = new ArrayList<String>();
		blockNames.add("blockNames");
		
		listaButtons.add(new Buttons("show_block",blockNames,"Show Block",null));
		
		p1.setButtons(listaButtons);
		
		a1.setPayload(p1);
		
		m.setAttachment(a1);
		
		listaMessagesBtn.add(m);

		rBtn.setMessages(listaMessagesBtn);

		return ResponseEntity.ok(rBtn);
	}
	
	@GetMapping(value = "/listarQuick")
	public ResponseEntity<RetornoQuick> listaQuick() {
		
		Gson gson = new Gson();
		
		RetornoQuick rq = new RetornoQuick();
		
		List<MessagesQuick> listaMessagesQuick = new ArrayList<MessagesQuick>();
		
		MessagesQuick mq = new MessagesQuick();
		
		mq.setText("Horarios disponiveis:");
		
		List<QuickReplie> listaQuickReplie = new ArrayList<QuickReplie>();
		
		//Manicure
		Manicure manicure = this.manicureRepository.findAll().get(0);
		
		//Servico
		Servico servico = this.servicoRepository.findAll().get(0);
		
		//Agenda Manicure
		List<AgendaManicure> listaAgenda = repository.findAll();
		List<AgendaManicureDTO> listaAgendaDTO = new ArrayList<>();
		for(AgendaManicure agManicure : listaAgenda) {
			listaAgendaDTO.add(this.converterAgendaParaDTO(agManicure));
		}
		
		for(AgendaManicureDTO agendaDTO : listaAgendaDTO) {
			QuickReplie qr = new QuickReplie();
			String titulo = agendaDTO.getData_servico()+" às " +agendaDTO.getHora_inicio(); 
			qr.setTitle(titulo);
			
			Atributos a = new Atributos();
			a.setCodAgenda(agendaDTO.getId().toString());
			a.setCodManicure(manicure.getId().toString());
			a.setNomeManicure(manicure.getNome());
			a.setCodServico(servico.getId().toString());
			
			qr.setSet_attributes(a);
			listaQuickReplie.add(qr);
			
		}
		
		//Set a lista de resposta rapidas
		mq.setQuick_replies(listaQuickReplie);
		
		//Opções 
		QuickReplyOptions qro = new QuickReplyOptions();
		qro.setProcess_text_by_ai(false);
		qro.setText_attribute_name("user_message");
		
		//Set opções
		mq.setQuick_reply_options(qro);
		
		listaMessagesQuick.add(mq);
		
		rq.setMessages(listaMessagesQuick);
		
		return ResponseEntity.ok(rq);
		
	}
}
