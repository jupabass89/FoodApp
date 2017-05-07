package com.example.juan.foodapp.modelo;

public abstract class Practica {
    
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

    /*
    * falta un metodo que permita modificar los datos ingresados, esto es para que en el momento de variar los datos se pueda hacer
    * desde ese metodo.
     */
}
