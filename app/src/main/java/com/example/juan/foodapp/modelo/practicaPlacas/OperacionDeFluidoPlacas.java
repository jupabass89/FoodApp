package com.example.juan.foodapp.modelo.practicaPlacas;

/**
 * Clase encargada de calcular valores necesarios de los fluidos utilizados en la practica
 * de pasteurizacion con placas.
 */
public class OperacionDeFluidoPlacas {

    public float calcularCapacidadCalorificaDeFluido(float tempPromedioDelFluido){
        return (float)(4176.2-((0.090864)*tempPromedioDelFluido)+(0.0054731*(tempPromedioDelFluido*tempPromedioDelFluido)));
    }

    public float calcularDensidadDeFluido(float tempPromedioDelFluido){
        return (float)(997.18+(0.0031439*tempPromedioDelFluido)-(0.0037574*(tempPromedioDelFluido*tempPromedioDelFluido)));
    }

    public float calcularFlujoMasicoDeFluido(float caudalDelFluido, float densidadDelFluido){
        return ((caudalDelFluido*densidadDelFluido)/1000);
    }

    public float calcularViscosidadDeFluido(float tempPromedioDelFluido){
        return (float)(0.0013-((0.00002)*tempPromedioDelFluido)+((0.0000001)*(tempPromedioDelFluido*tempPromedioDelFluido)));
    }

    public float calcularConductividadTermicaDeFluido(float tempPromedioDelFluido){
        return (float)(0.57109 + (0.0017625*tempPromedioDelFluido)-(0.0000067376)*(tempPromedioDelFluido*tempPromedioDelFluido));
    }
}
