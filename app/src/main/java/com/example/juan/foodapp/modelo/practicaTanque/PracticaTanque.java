package com.example.juan.foodapp.modelo.practicaTanque;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;

public class PracticaTanque extends Practica {

    private Alimento calentamiento, enfriamiento;
    private FluidoServicio calefactor,refrigerante;
    private Tanque tanque;
    private OperacionTanqueAgitado operaciones;
    private Agitador agitador;

    private float diametroInternoChaqueta;      //parametro
    private float tiempoEstCalentamiento;
    private float tiempoEstEnfriamiento;
    private float coeficienteGlobalDeTrasnferencia;
    private float ht;                          //(COEF TRANS ALIM) 


    public PracticaTanque(){
        calentamiento = new Alimento();
        enfriamiento = new Alimento();
        calefactor =new FluidoServicio();
        refrigerante = new FluidoServicio();
        tanque = new Tanque();
        operaciones = new OperacionTanqueAgitado();
        agitador = new Agitador(5,5);
    }

    private void hacerCalculos(){
        float []agitador = this.agitador.getAgitador(0);
        ht = operaciones.calcularCoeficienteIndividualTransferenciaCalorInteriorTanque(
                tanque.getDiametroInterno(),calefactor.getConductividadTermica(),agitador[0],agitador[1],agitador[3],
                this.agitador.getAltura(), calefactor.getDensidad(), this.agitador.getVelocidadGiroRPS(),calefactor.getViscosidad(),
                calefactor.getViscosidad(),calefactor.getCalorEspecifico());

        diametroInternoChaqueta = 0;//hacer calculo con funcion de operaciones

        coeficienteGlobalDeTrasnferencia = operaciones.calcularCoeficienteGlobalTrasnferenciaDeCalor(tanque.getConductividadMaterial(),ht,
                tanque.getDiametroInterno()/2,tanque.getDiametroExterno()/2,tanque.getFactorPorIncrustaciones(),tanque.getConductividadMaterial());

        tiempoEstCalentamiento = operaciones.calcularTimepoEstimadoCalentamiento(calentamiento.getVolumen(),
                calentamiento.getDensidad(),calentamiento.getCalorEspecifico(),
                tanque.getDiametroInterno(),calefactor.getTempInicial(),
                calentamiento.getTempEntrada(),calentamiento.getTempSalida(),coeficienteGlobalDeTrasnferencia);

        tiempoEstEnfriamiento=0;

    }

    @Override
    public void graficar() {

    }

    @Override
    public void configurarPractica() {

    }
}
