package com.example.juan.foodapp.modelo.practicaPlacas;

public class PasteurizadorPlacas {

    // Especificaciones técnicas del pasteurizador de placas de la planta de lácteos de la UdeA
    private final String MATERIAL= "Acero inoxidable AISI 316";  
    private final float CONDUCTIVIDAD_TERMICA = 0.016f;         //w/m°k
    private final float ANCHO_PLACA = 0.1f;                     //metros
    private final float LARGO_PLACA= 0.48f;                     //mts
    private final float DISTANCIA_PLACAS= 0.003f;               //mts
    private final float DIAMETRO_EQUIVALENTE= 0.006f;           //mts
    private final float CALIBRE = 0.00075f;                     //mts
    private final float ESPESOR_PLACAS= 0.003f;                 //mts
    private final float AREA_CIRCULACION= 0.003f;               //m2
    private final float COEFICIENTE_OBSTRUCCION= 0.009f;
    private final int NUMERO_PLACAS_PASTERIZACION_ENFRIAMIENTO = 6;
    private final int NUMERO_PLACAS_REGENERACION = 8;
    
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
    private int numeroPlacasPasteurizacion_Enfriamiento;
    private int numeroPlacasRegeneracion;
    
    public PasteurizadorPlacas(){
        conductividadTermica = CONDUCTIVIDAD_TERMICA;
        anchoPlaca = ANCHO_PLACA;
        largoPlaca = LARGO_PLACA;
        distanciaPlacas =DISTANCIA_PLACAS;
        diametroEquivalente = DIAMETRO_EQUIVALENTE;
        calibre = CALIBRE;
        espesorPlacas=ESPESOR_PLACAS;
        areaCirculacion = AREA_CIRCULACION;
        coeficienteObstruccion = COEFICIENTE_OBSTRUCCION;
        numeroPlacasPasteurizacion_Enfriamiento= NUMERO_PLACAS_PASTERIZACION_ENFRIAMIENTO;
        numeroPlacasRegeneracion = NUMERO_PLACAS_REGENERACION;
    }

    public String getMaterial(){
        return material;
    }

    public float getConductividadTermica() {
        return conductividadTermica;
    }

    public void setConductividadTermica(float conductividadTermica) {
        this.conductividadTermica = conductividadTermica;
    }

    public float getAnchoPlaca() {
        return anchoPlaca;
    }

    public void setAnchoPlaca(float anchoPlaca) {
        this.anchoPlaca = anchoPlaca;
    }

    public float getLargoPlaca() {
        return largoPlaca;
    }

    public void setLargoPlaca(float largoPlaca) {
        this.largoPlaca = largoPlaca;
    }

    public float getDistanciaPlacas() {
        return distanciaPlacas;
    }

    public void setDistanciaPlacas(float distanciaPlacas) {
        this.distanciaPlacas = distanciaPlacas;
    }

    public float getDiametroEquivalente() {
        return diametroEquivalente;
    }

    public void setDiametroEquivalente(float diametroEquivalente) {
        this.diametroEquivalente = diametroEquivalente;
    }

    public float getCalibre() {
        return calibre;
    }

    public void setCalibre(float calibre) {
        this.calibre = calibre;
    }

    public float getEspesorPlacas() {
        return espesorPlacas;
    }

    public void setEspesorPlacas(float espesorPlacas) {
        this.espesorPlacas = espesorPlacas;
    }

    public float getAreaCirculacion() {
        return areaCirculacion;
    }

    public void setAreaCirculacion(float areaCirculacion) {
        this.areaCirculacion = areaCirculacion;
    }

    public float getCoeficienteObstruccion() {
        return coeficienteObstruccion;
    }

    public void setCoeficienteObstruccion(float coeficienteObstruccion) {
        this.coeficienteObstruccion = coeficienteObstruccion;
    }

    public int getNumeroPlacasPasteurizacion_Enfriamiento() {
        return numeroPlacasPasteurizacion_Enfriamiento;
    }

    public void setNumeroPlacasPasteurizacion_Enfriamiento(int numeroPlacasPasteurizacion_Enfriamiento) {
        this.numeroPlacasPasteurizacion_Enfriamiento = numeroPlacasPasteurizacion_Enfriamiento;
    }

    public int getNumeroPlacasRegeneracion() {
        return numeroPlacasRegeneracion;
    }

    public void setNumeroPlacasRegeneracion(int numeroPlacasRegeneracion) {
        this.numeroPlacasRegeneracion = numeroPlacasRegeneracion;
    }
}
