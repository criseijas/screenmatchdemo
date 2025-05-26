package com.aluracursos.screenmatchdemo.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo() {

        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria Fernanda", "Eric", "Genesys");

        nombres.stream()
                .sorted()//ordena alfabeticamente
                .limit(4)//muestra las 4 primeras personas
                .filter(n -> n.startsWith("L")) //filtramos que encuentre el primer elemento que comience con la letra L
                .map(n -> n.toUpperCase())//convertir ese nombre a letras mayusculas
                .forEach(System.out::println); //imprime por pantalla cada elemento
    }

}
