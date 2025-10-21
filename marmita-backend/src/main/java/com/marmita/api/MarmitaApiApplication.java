package com.marmita.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarmitaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarmitaApiApplication.class, args);
		System.out.println("Marmita API Server rodando na porta 8080");
		System.out.println("Marmitas: http://localhost:8080/api/marmitas");
		System.out.println("Usuários: http://localhost:8080/api/usuarios");
		System.out.println("Desenvolvido para Sistema de Delivery de Marmitas");
	}
}