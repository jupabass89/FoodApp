package com.example.juan.foodapp.modelo.practicaPlacas;

public class ZonaPasterizacion {

    private float flujoDeCalor; // J/s
    private float temperaturaMediaLogaritmica; // MLDT -- °c
    private float temperaturaParedPlaca; //°c
    private float areaDeDiseñoRequerida; // m^2
    private float areaDeTCDeCadaPlaca; // m^2
    private float numeroDePlacasNecesarias;
    private float numeroDeCanalesTotales;
    private float areaDeFlujo; // m^2
    private float densidadFlujoMasicaGlobalFluidoFrio; // kg/s.m^2
    private float densidadFlujoMasicaGlobalFluidoCaliente; // kg/s.m^2
    private float numeroDeReynoldsFluidoFrio;
    private float numeroDeReynoldsFluidoCaliente;
    private float numeroDePrantFluidoFrio;
    private float numeroDePrantFluidoCaliente;
    private float numeroNusseltFluidoFrio;
    private float numeroNusseltFluidoCaliente;
    private float coeficienteTCPorConveccionFluidoFrio; //W/m2°C
    private float coeficienteTCPorConveccionFluidoCaliente; //W/m2°C
    private float coeficienteDeDiseñoAsumido;
    private float coeficienteDeDiseñoCalculado;
    private float numeroDePlacasTotalesRequeridas;
    private float caudalDeEntradaAlimento;
    private float caudalDeEntradaFluidoDeServicio;

    public float getFlujoDeCalor() {
        return flujoDeCalor;
    }

    public void setFlujoDeCalor(float flujoDeCalor) {
        this.flujoDeCalor = flujoDeCalor;
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

    public float getAreaDeDiseñoRequerida() {
        return areaDeDiseñoRequerida;
    }

    public void setAreaDeDiseñoRequerida(float areaDeDiseñoRequerida) {
        this.areaDeDiseñoRequerida = areaDeDiseñoRequerida;
    }

    public float getAreaDeTCDeCadaPlaca() {
        return areaDeTCDeCadaPlaca;
    }

    public void setAreaDeTCDeCadaPlaca(float areaDeTCDeCadaPlaca) {
        this.areaDeTCDeCadaPlaca = areaDeTCDeCadaPlaca;
    }

    public float getNumeroDePlacasNecesarias() {
        return numeroDePlacasNecesarias;
    }

    public void setNumeroDePlacasNecesarias(float numeroDePlacasNecesarias) {
        this.numeroDePlacasNecesarias = numeroDePlacasNecesarias;
    }

    public float getNumeroDeCanalesTotales() {
        return numeroDeCanalesTotales;
    }

    public void setNumeroDeCanalesTotales(float numeroDeCanalesTotales) {
        this.numeroDeCanalesTotales = numeroDeCanalesTotales;
    }

    public float getAreaDeFlujo() {
        return areaDeFlujo;
    }

    public void setAreaDeFlujo(float areaDeFlujo) {
        this.areaDeFlujo = areaDeFlujo;
    }

    public float getDensidadFlujoMasicaGlobalFluidoFrio() {
        return densidadFlujoMasicaGlobalFluidoFrio;
    }

    public void setDensidadFlujoMasicaGlobalFluidoFrio(float densidadFlujoMasicaGlobalFluidoFrio) {
        this.densidadFlujoMasicaGlobalFluidoFrio = densidadFlujoMasicaGlobalFluidoFrio;
    }

    public float getDensidadFlujoMasicaGlobalFluidoCaliente() {
        return densidadFlujoMasicaGlobalFluidoCaliente;
    }

    public void setDensidadFlujoMasicaGlobalFluidoCaliente(float densidadFlujoMasicaGlobalFluidoCaliente) {
        this.densidadFlujoMasicaGlobalFluidoCaliente = densidadFlujoMasicaGlobalFluidoCaliente;
    }

    public float getNumeroDeReynoldsFluidoFrio() {
        return numeroDeReynoldsFluidoFrio;
    }

    public void setNumeroDeReynoldsFluidoFrio(float numeroDeReynoldsFluidoFrio) {
        this.numeroDeReynoldsFluidoFrio = numeroDeReynoldsFluidoFrio;
    }

    public float getNumeroDeReynoldsFluidoCaliente() {
        return numeroDeReynoldsFluidoCaliente;
    }

    public void setNumeroDeReynoldsFluidoCaliente(float numeroDeReynoldsFluidoCaliente) {
        this.numeroDeReynoldsFluidoCaliente = numeroDeReynoldsFluidoCaliente;
    }

    public float getNumeroDePrantFluidoFrio() {
        return numeroDePrantFluidoFrio;
    }

    public void setNumeroDePrantFluidoFrio(float numeroDePrantFluidoFrio) {
        this.numeroDePrantFluidoFrio = numeroDePrantFluidoFrio;
    }

    public float getNumeroDePrantFluidoCaliente() {
        return numeroDePrantFluidoCaliente;
    }

    public void setNumeroDePrantFluidoCaliente(float numeroDePrantFluidoCaliente) {
        this.numeroDePrantFluidoCaliente = numeroDePrantFluidoCaliente;
    }

    public float getNumeroNusseltFluidoFrio() {
        return numeroNusseltFluidoFrio;
    }

    public void setNumeroNusseltFluidoFrio(float numeroNusseltFluidoFrio) {
        this.numeroNusseltFluidoFrio = numeroNusseltFluidoFrio;
    }

    public float getNumeroNusseltFluidoCaliente() {
        return numeroNusseltFluidoCaliente;
    }

    public void setNumeroNusseltFluidoCaliente(float numeroNusseltFluidoCaliente) {
        this.numeroNusseltFluidoCaliente = numeroNusseltFluidoCaliente;
    }

    public float getCoeficienteTCPorConveccionFluidoFrio() {
        return coeficienteTCPorConveccionFluidoFrio;
    }

    public void setCoeficienteTCPorConveccionFluidoFrio(float coeficienteTCPorConveccionFluidoFrio) {
        this.coeficienteTCPorConveccionFluidoFrio = coeficienteTCPorConveccionFluidoFrio;
    }

    public float getCoeficienteTCPorConveccionFluidoCaliente() {
        return coeficienteTCPorConveccionFluidoCaliente;
    }

    public void setCoeficienteTCPorConveccionFluidoCaliente(float coeficienteTCPorConveccionFluidoCaliente) {
        this.coeficienteTCPorConveccionFluidoCaliente = coeficienteTCPorConveccionFluidoCaliente;
    }

    public float getCoeficienteDeDiseñoAsumido() {
        return coeficienteDeDiseñoAsumido;
    }

    public void setCoeficienteDeDiseñoAsumido(float coeficienteDeDiseñoAsumido) {
        this.coeficienteDeDiseñoAsumido = coeficienteDeDiseñoAsumido;
    }

    public float getCoeficienteDeDiseñoCalculado() {
        return coeficienteDeDiseñoCalculado;
    }

    public void setCoeficienteDeDiseñoCalculado(float coeficienteDeDiseñoCalculado) {
        this.coeficienteDeDiseñoCalculado = coeficienteDeDiseñoCalculado;
    }

    public float getNumeroDePlacasTotalesRequeridas() {
        return numeroDePlacasTotalesRequeridas;
    }

    public void setNumeroDePlacasTotalesRequeridas(float numeroDePlacasTotalesRequeridas) {
        this.numeroDePlacasTotalesRequeridas = numeroDePlacasTotalesRequeridas;
    }

    public float getCaudalDeEntradaAlimento() {
        return caudalDeEntradaAlimento;
    }

    public void setCaudalDeEntradaAlimento(float caudalDeEntradaAlimento) {
        this.caudalDeEntradaAlimento = caudalDeEntradaAlimento;
    }

    public float getCaudalDeEntradaFluidoDeServicio() {
        return caudalDeEntradaFluidoDeServicio;
    }

    public void setCaudalDeEntradaFluidoDeServicio(float caudalDeEntradaFluidoDeServicio) {
        this.caudalDeEntradaFluidoDeServicio = caudalDeEntradaFluidoDeServicio;
    }
}
