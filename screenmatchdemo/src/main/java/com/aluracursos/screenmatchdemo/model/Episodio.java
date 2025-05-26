package com.aluracursos.screenmatchdemo.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {

    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo= d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion());
        }catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }


        try {
            String fechaStr = d.fechaDeLanzamiento();
            if (fechaStr != null && !fechaStr.equalsIgnoreCase("N/A")) {
                this.fechaDeLanzamiento = LocalDate.parse(fechaStr); // <-- Usa un formato personalizado si es necesario
            } else {
                this.fechaDeLanzamiento = null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Fecha inválida: " + d.fechaDeLanzamiento());
            this.fechaDeLanzamiento = null;
        }
        //this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    @Override
    public String toString() {
        return "Episodio: " +
                "temporada= " + temporada +
                ", titulo= " + titulo +
                ", numeroEpisodio= " + numeroEpisodio +
                ", evaluacion =" + evaluacion +
                ", fechaDeLanzamiento=" + fechaDeLanzamiento;
    }
}
