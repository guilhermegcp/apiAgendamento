package com.agendamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.agendamento.api.ServicoApi;

@SpringBootApplication
@Configuration
@ComponentScan(basePackageClasses = ServicoApi.class)
public class AgendamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoApplication.class, args);
	}	

}
