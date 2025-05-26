package com.aluracursos.screenmatchdemo.principal;

import com.aluracursos.screenmatchdemo.model.DatosEpisodio;
import com.aluracursos.screenmatchdemo.model.DatosSerie;
import com.aluracursos.screenmatchdemo.model.DatosTemporadas;
import com.aluracursos.screenmatchdemo.model.Episodio;
import com.aluracursos.screenmatchdemo.service.ConsumoAPI;
import com.aluracursos.screenmatchdemo.service.ConvierteDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=58a4ee44";
    private ConvierteDatos conversor = new ConvierteDatos();



    public void muestraMenu() {

        System.out.println("Ingrese el nombre de la serie que desea buscar: ");
        //Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + /*nombreSerie.replace(" ", "+")*/URLEncoder.encode(nombreSerie, StandardCharsets.UTF_8) + API_KEY);
        System.out.println(json);
        var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println("DATOS DE LA SERIE: " + datosSerie);

        //Busca los datos de todas las temporadas

        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datosSerie.totalDeTemporadas() ; i++) {
            json = consumoAPI.obtenerDatos( URL_BASE + /*nombreSerie.replace(" ", "+")*/URLEncoder.encode(nombreSerie, StandardCharsets.UTF_8) + "&Season=" + i + API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        System.out.println(" --------- DATOS DE LAS TEMPORADAS ----------- ");
        temporadas.forEach(System.out::println);

        //Mostrar solo el titulo de los episodios para las temporadas
        /*for (int i = 0; i < datosSerie.totalDeTemporadas(); i++) {
            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();

            for (int j = 0; j < episodiosTemporada.size() ; j++) {
                System.out.println(episodiosTemporada.get(j).titulo());
            }
        }*/

        temporadas.forEach(t -> t.episodios().
                forEach(e -> System.out.println(e.titulo())));

        //convertir todas las informaciones a una lista de tipo datosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                                            .flatMap(t -> t.episodios().stream())//nos está convirtiendo cada uno de los elementos que están dentro de esas temporadas. En una lista de episodios. Está trayendo esos episodios. Aquí los estamos mapeando. Y a su vez, vamos a utilizar el stream para, de hecho, tener nuestra operación final. Que va a ser convertir todo en una lista. Y para eso vamos a usar el CollectCollectorToList
                                            .collect(Collectors.toList());

        //top 5 episodios
        System.out.println("TOP 5: Episodios");
        datosEpisodios.stream().filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                                .limit(5)
                                .forEach(System.out::println);


        //convirtiendo los datos a una lista de tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream().map(d -> new Episodio(t.numero(),d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        //busqueda de episodios a partir de x año
        System.out.println("Por favor, ingrese el año a partir del cual deseas ver los episodios: ");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        episodios.stream()
                .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                        "Titulo Episodio: " + e.getTitulo() +
                        "Fecha de Lanzamiento: " + e.getFechaDeLanzamiento().format(dtf)
                ));


    }

}
