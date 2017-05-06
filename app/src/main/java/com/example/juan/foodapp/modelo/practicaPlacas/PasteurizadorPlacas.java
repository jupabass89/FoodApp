package com.example.juan.foodapp.modelo.practicaPlacas;

/**
 * Clase que contiene los datos del Pasteurizador de placas utilizado para el proceso
 * de pasteurizacion(Practica Placas).
 */
public class PasteurizadorPlacas {

    // Especificaciones técnicas del pasteurizador de placas de la planta de lácteos de la UdeA
    private final String MATERIAL= "Acero inoxidable AISI 316";  
    private final float CONDUCTIVIDAD_TERMICA = 0.016f;         //w/m°k
                        // Revisar valor de conductividad termica
    private final float ANCHO_PLACA = 0.1f;                     //mts
    private final float LARGO_PLACA= 0.48f;                     //mts
    private final float DISTANCIA_PLACAS= 0.003f;               //mts
    private final float DIAMETRO_EQUIVALENTE= 2*DISTANCIA_PLACAS; //mts
    private final float CALIBRE = 0.00075f;                     //mts
    private final float ESPESOR_PLACAS= 0.003f;                 //mts
    private final float AREA_CIRCULACION= 0.0003f;               //m2
    private final float COEFICIENTE_OBSTRUCCION= 0.0009f;
    private final int NUMERO_PLACAS = 6;
    
    private String material;
    private float conductividadTermica;
    private float anchoPlaca;
    private float largoPlaca;
    private float distanciaPlacas;
    private float diametroEquivalente;
    private float calibre;
    private float espesorPlacas;
    private float areaCirculacion;
    private float coeficienteObstruccion;
    private int numeroDePlacas;
    
    public PasteurizadorPlacas(){
        material = MATERIAL;
        conductividadTermica = CONDUCTIVIDAD_TERMICA;
        anchoPlaca = ANCHO_PLACA;
        largoPlaca = LARGO_PLACA;
        distanciaPlacas =DISTANCIA_PLACAS;
        diametroEquivalente = DIAMETRO_EQUIVALENTE;
        calibre = CALIBRE;
        espesorPlacas=ESPESOR_PLACAS;
        areaCirculacion = AREA_CIRCULACION;
        coeficienteObstruccion = COEFICIENTE_OBSTRUCCION;
        numeroDePlacas = NUMERO_PLACAS;
    }

    public void setAnchoPlaca(float anchoPlaca) {
        this.anchoPlaca = anchoPlaca;
    }

    public void setLargoPlaca(float largoPlaca) {
        this.largoPlaca = largoPlaca;
    }

    public String getMaterial() {
        return material;
    }

    public float getConductividadTermica() {
        return conductividadTermica;
    }

    public float getAnchoPlaca() {
        return anchoPlaca;
    }

    public float getLargoPlaca() {
        return largoPlaca;
    }

    public float getDistanciaPlacas() {
        return distanciaPlacas;
    }

    public float getDiametroEquivalente() {
        return diametroEquivalente;
    }

    public float getCalibre() {
        return calibre;
    }

    public float getEspesorPlacas() {
        return espesorPlacas;
    }

    public float getAreaCirculacion() {
        return areaCirculacion;
    }

    public float getCoeficienteObstruccion() {
        return coeficienteObstruccion;
    }

    public int getNumeroDePlacas() {
        return numeroDePlacas;
    }
}
