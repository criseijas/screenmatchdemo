package com.aluracursos.screenmatchdemo;

import com.aluracursos.screenmatchdemo.model.DatosEpisodio;
import com.aluracursos.screenmatchdemo.model.DatosSerie;
import com.aluracursos.screenmatchdemo.model.DatosTemporadas;
import com.aluracursos.screenmatchdemo.principal.EjemploStreams;
import com.aluracursos.screenmatchdemo.principal.Principal;
import com.aluracursos.screenmatchdemo.service.ConsumoAPI;
import com.aluracursos.screenmatchdemo.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchdemoApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchdemoApplication.class, args);

	}
	//58a4ee44
	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();

		principal.muestraMenu();

		//EjemploStreams ejemploStreams = new EjemploStreams();
		//ejemploStreams.muestraEjemplo();












		/*json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=58a4ee44");
		System.out.println("Episodio: " + json);
		DatosEpisodio episodio = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodio);*/


	}
}
