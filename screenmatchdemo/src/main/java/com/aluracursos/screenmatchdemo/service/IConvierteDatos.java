package com.aluracursos.screenmatchdemo.service;

public interface IConvierteDatos {//4

    <T> T obtenerDatos(String json, Class<T> clase);


}
