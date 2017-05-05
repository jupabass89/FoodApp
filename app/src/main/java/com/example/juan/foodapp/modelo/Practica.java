package com.example.juan.foodapp.modelo;

public  abstract class Practica {
    
    protected String nombre;
    protected String nombreLaboratorio;
    protected String asignatura;
    protected String profesor;

    public abstract void graficar();
    /*
    *el método configurar practica obtendra los parametros para setear los alimento, se obtiene segun los campos de la practica.
    *
     */
    public abstract void configurarPractica();

}
