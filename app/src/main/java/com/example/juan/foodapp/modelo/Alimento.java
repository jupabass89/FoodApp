package com.example.juan.foodapp.modelo;

public class Alimento {
  
    private String nombreAlimento;          //
    private float masa;                     //Kg
    private float volumen;                  //m3 / L
    private float tempInicial;              //°C
    private float tempEnfriamiento;         //°C
    private float tempCalentamiento;        //°C
    private float tempEntrada;              //°C
    private float tempSalida;               //°C
    private float conductividadTermica;     //w/m°C
    private float calorEspecifico;          //J/kg°C
    // capacidadCalorifica
    private float flujoMasico;              // Kg/s
    private float densidad;                 //kg/m3
    private float viscosidad;               //kg/m*s
    private float ht;                       //ht (COEF TRANS ALIM)

    public String getNombreAlimento() {
        return nombreAlimento;
    }

    public void setNombreAlimento(String nombreAlimento) {
        this.nombreAlimento = nombreAlimento;
    }

    public float getMasa() {
        return masa;
    }

    public void setMasa(float masa) {
        this.masa = masa;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public float getTempInicial() {
        return tempInicial;
    }

    public void setTempInicial(float tempInicial) {
        this.tempInicial = tempInicial;
    }

    public float getTempEnfriamiento() {
        return tempEnfriamiento;
    }

    public void setTempEnfriamiento(float tempEnfriamiento) {
        this.tempEnfriamiento = tempEnfriamiento;
    }

    public float getTempCalentamiento() {
        return tempCalentamiento;
    }

    public void setTempCalentamiento(float tempCalentamiento) {
        this.tempCalentamiento = tempCalentamiento;
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

    public float getConductividadTermica() {
        return conductividadTermica;
    }

    public void setConductividadTermica(float conductividadTermica) {
        this.conductividadTermica = conductividadTermica;
    }

    public float getCalorEspecifico() {
        return calorEspecifico;
    }

    public void setCalorEspecifico(float calorEspecifico) {
        this.calorEspecifico = calorEspecifico;
    }

    public float getFlujoMasico() {
        return flujoMasico;
    }

    public void setFlujoMasico(float flujoMasico) {
        this.flujoMasico = flujoMasico;
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

    public float getHt() {
        return ht;
    }

    public void setHt(float ht) {
        this.ht = ht;
    }

    // Modificar
    public float getCapacidadCalorifica(){
        return (0);
    }
}
