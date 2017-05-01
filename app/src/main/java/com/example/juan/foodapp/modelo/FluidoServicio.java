package com.example.juan.foodapp.modelo;

public class FluidoServicio {

    private String nombreFluido;
    private String tipodeFluido;
    private float flujoMasico;
    private float tempInicial;
    private float tempFinal;
    private float tempEntrada;
    private float tempSalida;
    private float capacidadCalorifica;
    private float calorEspecifico;
    private float conductividadTermica;
    private float densidad;
    private float viscosidad;
    private float coefExpTermica;
    private float hCH;

    public String getNombreFluido() {
        return nombreFluido;
    }

    public void setNombreFluido(String nombreFluido) {
        this.nombreFluido = nombreFluido;
    }

    public String getTipodeFluido() {
        return tipodeFluido;
    }

    public void setTipodeFluido(String tipodeFluido) {
        this.tipodeFluido = tipodeFluido;
    }

    public float getFlujoMasico() {
        return flujoMasico;
    }

    public void setFlujoMasico(float flujoMasico) {
        this.flujoMasico = flujoMasico;
    }

    public float getTempInicial() {
        return tempInicial;
    }

    public void setTempInicial(float tempInicial) {
        this.tempInicial = tempInicial;
    }

    public float getTempFinal() {
        return tempFinal;
    }

    public void setTempFinal(float tempFinal) {
        this.tempFinal = tempFinal;
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

    public float gethCH() {
        return hCH;
    }

    public void sethCH(float hCH) {
        this.hCH = hCH;
    }
}
