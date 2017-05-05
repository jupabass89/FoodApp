package com.example.juan.foodapp.modelo.practicaTanque;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;

public class PracticaTanque extends Practica {

    private Alimento calentamiento, enfriamiento;
    private FluidoServicio calefactor,refrigerante;
    private Tanque tanque;
    private OperacionTanqueAgitado operaciones;

    private float diametroInternoChaqueta;      //parametro
    private float tiempoEstCalentamiento;
    private float tiempoEstEnfriamiento;
    private float ht;                          //(COEF TRANS ALIM) 


    public PracticaTanque(){
        calentamiento = new Alimento();
        enfriamiento = new Alimento();
        calefactor =new FluidoServicio();
        refrigerante = new FluidoServicio();
        tanque = new Tanque();
        operaciones = new OperacionTanqueAgitado();
    }


    public Alimento getCalentamiento() {
     return calentamiento;
    }

    public void setCalentamiento(Alimento calentamiento) {
     this.calentamiento = calentamiento;
    }

    public Alimento getEnfriamiento() {
     return enfriamiento;
    }

    public void setEnfriamiento(Alimento enfriamiento) {
     this.enfriamiento = enfriamiento;
    }

    public FluidoServicio getCalefactor() {
     return calefactor;
    }

    public void setCalefactor(FluidoServicio calefactor) {
     this.calefactor = calefactor;
    }

    public FluidoServicio getRefrigerante() {
     return refrigerante;
    }

    public void setRefrigerante(FluidoServicio refrigerante) {
     this.refrigerante = refrigerante;
    }

    public Tanque getTanque() {
     return tanque;
    }

    public void setTanque(Tanque tanque) {
     this.tanque = tanque;
    }

    public OperacionTanqueAgitado getOperaciones() {
     return operaciones;
    }

    public void setOperaciones(OperacionTanqueAgitado operaciones) {
     this.operaciones = operaciones;
    }

    public float getDiametroInternoChaqueta() {
     return diametroInternoChaqueta;
    }

    public void setDiametroInternoChaqueta(float diametroInternoChaqueta) {
     this.diametroInternoChaqueta = diametroInternoChaqueta;
    }

    public float getTiempoEstCalentamiento() {
     return tiempoEstCalentamiento;
    }

    public void setTiempoEstCalentamiento(float tiempoEstCalentamiento) {
     this.tiempoEstCalentamiento = tiempoEstCalentamiento;
    }

    public float getTiempoEstEnfriamiento() {
     return tiempoEstEnfriamiento;
    }

    public void setTiempoEstEnfriamiento(float tiempoEstEnfriamiento) {
     this.tiempoEstEnfriamiento = tiempoEstEnfriamiento;
    }

    public float getHt() {
     return ht;
    }

    public void setHt(float ht) {
     this.ht = ht;
    }

    @Override
    public void graficar() {

    }

    @Override
    public void configurarPractica() {

    }
}
