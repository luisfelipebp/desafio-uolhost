package com.luisfelipebp;

import com.luisfelipebp.model.Jogador;
import com.luisfelipebp.model.enums.GrupoEnum;
import com.luisfelipebp.repository.JogadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioUolhostApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioUolhostApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(JogadorRepository jogadorRepository) {
		return args -> {
//			Jogador jogador1 = new Jogador(null, "Luis Felipe", "luis@gmail.com","2299879786",GrupoEnum.LIGA_DA_JUSTICA, "Hulk");
//			Jogador jogador2 = new Jogador(null, "Jonas", "jonas@gmail.com","2299879783", GrupoEnum.VINGADORES, "Capitão América");
//			jogadorRepository.save(jogador1);
//			jogadorRepository.save(jogador2);
		};
	}

}
