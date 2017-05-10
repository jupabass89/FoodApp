package com.example.juan.foodapp.modelo;

import java.util.ArrayList;

public abstract class Practica {
    
    protected String nombre;
    protected String nombreLaboratorio;
    protected String asignatura;
    protected String profesor;

    public abstract ArrayList<Object> calcularDatosGrafica();
    /*
    *el método configurar practica obtendra los parametros para setear los alimento, se obtiene segun los campos de la practica.
    *
     */
    public abstract void configurarPractica(Object[] datos);

    /*
    * falta un metodo que permita modificar los datos ingresados, esto es para que en el momento de variar los datos se pueda hacer
    * desde ese metodo.
     */
}
