package com.example.juan.foodapp.modelo;

public class FluidoServicio {

    private float flujoMasico;
    private float tempEntrada;
    private float tempSalida;
    private float capacidadCalorifica;
    private float calorEspecifico;
    private float conductividadTermica;
    private float densidad;
    private float viscosidad;
    private float coefExpTermica;
    private float coeficienteDeIncrustacion;

    public float getFlujoMasico() {
        return flujoMasico;
    }

    public void setFlujoMasico(float flujoMasico) {
        this.flujoMasico = flujoMasico;
    }

    public float getTempEntrada() {
        return tempEntrada;
    }

    public void setTempEntrada(float tempEntrada) {
        this.tempEntrada = tempEntrada;
    }

    public float getTempSalida() {
        return tempSalida;
    }

    public void setTempSalida(float tempSalida) {
        this.tempSalida = tempSalida;
    }

    public float getTemperaturaPromedio(){
        return((tempEntrada+tempSalida)/2);
    }

    public float getCapacidadCalorifica() {
        return capacidadCalorifica;
    }

    public void setCapacidadCalorifica(float capacidadCalorifica) {
        this.capacidadCalorifica = capacidadCalorifica;
    }

    public float getCalorEspecifico() {
        return calorEspecifico;
    }

    public void setCalorEspecifico(float calorEspecifico) {
        this.calorEspecifico = calorEspecifico;
    }

    public float getConductividadTermica() {
        return conductividadTermica;
    }

    public void setConductividadTermica(float conductividadTermica) {
        this.conductividadTermica = conductividadTermica;
    }

    public float getDensidad() {
        return densidad;
    }

    public void setDensidad(float densidad) {
        this.densidad = densidad;
    }

    public float getViscosidad() {
        return viscosidad;
    }

    public void setViscosidad(float viscosidad) {
        this.viscosidad = viscosidad;
    }

    public float getCoefExpTermica() {
        return coefExpTermica;
    }

    public void setCoefExpTermica(float coefExpTermica) {
        this.coefExpTermica = coefExpTermica;
    }

    public float getCoeficienteDeIncrustacion() {
        return coeficienteDeIncrustacion;
    }

    public void setCoeficienteDeIncrustacion(float coeficienteDeIncrustacion) {
        this.coeficienteDeIncrustacion = coeficienteDeIncrustacion;
    }

    public float temperaturaPromedio(){
        return ((tempEntrada+tempSalida)/2);
    }
}
