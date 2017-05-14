package com.example.juan.foodapp.modelo;

import android.content.Context;

import java.util.ArrayList;

public abstract class Practica {


    private String nombre;
    private String nombreLaboratorio;
    private String asignatura;
    private String profesor;

    public Practica(Context contexto,String asignatura,String profesor){
        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreLaboratorio() {
        return nombreLaboratorio;
    }

    public void setNombreLaboratorio(String nombreLaboratorio) {
        this.nombreLaboratorio = nombreLaboratorio;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public abstract ArrayList<Object> calcularDatosGrafica();
    /*
     *   el método configurar practica obtendra los parametros para setear los alimento, se obtiene segun los campos de la practica.
     *
     */
    public abstract ArrayList<Object> calcularDatosGrafica(Object[] datosEntrada);

    public abstract void configurarPractica(Object[] datos);

    /*
     * falta un metodo que permita modificar los datos ingresados, esto es para que en el momento de variar los datos se pueda hacer
     * desde ese metodo.
     */

    public abstract void generarInforme(String nombreArchivo);
}
