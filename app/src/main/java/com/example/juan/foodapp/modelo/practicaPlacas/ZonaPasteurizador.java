package com.example.juan.foodapp.modelo.practicaPlacas;

public class ZonaPasteurizador {

    private float flujoDeCalor;
    private float porcentajeDeRetencion;
    private float coefIndTCAlimento;
    private float areaDeFlujo;
    private float densidadDeFlujoMasicoGlobal;
    private float numeroDeReynolds;
    private float numeroDePrant;
    private float coefIndTCTeorico;
    private float porcentajeDeError;
    private float coefGlobalTC;
    private float NUT;                              // número de unidades de transferencia de calor
    private float efectividadIntercambiadorDeCalor;
    private float caidaDePresionFluido;
    private float temperaturaMediaLogaritmica;
    private float temperaturaParedPlaca;
    private float factorFriccionFanning;

    public float getFlujoDeCalor() {
        return flujoDeCalor;
    }

    public void setFlujoDeCalor(float flujoDeCalor) {
        this.flujoDeCalor = flujoDeCalor;
    }

    public float getPorcentajeDeRetencion() {
        return porcentajeDeRetencion;
    }

    public void setPorcentajeDeRetencion(float porcentajeDeRetencion) {
        this.porcentajeDeRetencion = porcentajeDeRetencion;
    }

    public float getCoefIndTCAlimento() {
        return coefIndTCAlimento;
    }

    public void setCoefIndTCAlimento(float coefIndTCAlimento) {
        this.coefIndTCAlimento = coefIndTCAlimento;
    }

    public float getAreaDeFlujo() {
        return areaDeFlujo;
    }

    public void setAreaDeFlujo(float areaDeFlujo) {
        this.areaDeFlujo = areaDeFlujo;
    }

    public float getDensidadDeFlujoMasicoGlobal() {
        return densidadDeFlujoMasicoGlobal;
    }

    public void setDensidadDeFlujoMasicoGlobal(float densidadDeFlujoMasicoGlobal) {
        this.densidadDeFlujoMasicoGlobal = densidadDeFlujoMasicoGlobal;
    }

    public float getNumeroDeReynolds() {
        return numeroDeReynolds;
    }

    public void setNumeroDeReynolds(float numeroDeReynolds) {
        this.numeroDeReynolds = numeroDeReynolds;
    }

    public float getNumeroDePrant() {
        return numeroDePrant;
    }

    public void setNumeroDePrant(float numeroDePrant) {
        this.numeroDePrant = numeroDePrant;
    }

    public float getCoefIndTCTeorico() {
        return coefIndTCTeorico;
    }

    public void setCoefIndTCTeorico(float coefIndTCTeorico) {
        this.coefIndTCTeorico = coefIndTCTeorico;
    }

    public float getPorcentajeDeError() {
        return porcentajeDeError;
    }

    public void setPorcentajeDeError(float porcentajeDeError) {
        this.porcentajeDeError = porcentajeDeError;
    }

    public float getCoefGlobalTC() {
        return coefGlobalTC;
    }

    public void setCoefGlobalTC(float coefGlobalTC) {
        this.coefGlobalTC = coefGlobalTC;
    }

    public float getNUT() {
        return NUT;
    }

    public void setNUT(float NUT) {
        this.NUT = NUT;
    }

    public float getEfectividadIntercambiadorDeCalor() {
        return efectividadIntercambiadorDeCalor;
    }

    public void setEfectividadIntercambiadorDeCalor(float efectividadIntercambiadorDeCalor) {
        this.efectividadIntercambiadorDeCalor = efectividadIntercambiadorDeCalor;
    }

    public float getCaidaDePresionFluido() {
        return caidaDePresionFluido;
    }

    public void setCaidaDePresionFluido(float caidaDePresionFluido) {
        this.caidaDePresionFluido = caidaDePresionFluido;
    }

    public float getTemperaturaMediaLogaritmica() {
        return temperaturaMediaLogaritmica;
    }

    public void setTemperaturaMediaLogaritmica(float temperaturaMediaLogaritmica) {
        this.temperaturaMediaLogaritmica = temperaturaMediaLogaritmica;
    }

    public float getTemperaturaParedPlaca() {
        return temperaturaParedPlaca;
    }

    public void setTemperaturaParedPlaca(float temperaturaParedPlaca) {
        this.temperaturaParedPlaca = temperaturaParedPlaca;
    }

    public float getFactorFriccionFanning() {
        return factorFriccionFanning;
    }

    public void setFactorFriccionFanning(float factorFriccionFanning) {
        this.factorFriccionFanning = factorFriccionFanning;
    }
}
