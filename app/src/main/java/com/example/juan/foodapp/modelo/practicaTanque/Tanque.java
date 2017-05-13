package com.example.juan.foodapp.modelo.practicaTanque;

public class Tanque {

    private float alturaProducto;
    private float espesor;
    private float diametroInterno;
    private float diametroExterno;
    private float conductividadMaterial;
    private float factorPorIncrustaciones;
    private float tempChaqueta;
    private float diametroChaqueta;
    private float area;

    public Tanque(){

    }

    public float getAlturaProducto() {
        return alturaProducto;
    }

    public void setAlturaProducto(float alturaProducto) {
        this.alturaProducto = alturaProducto;
    }

    public float getEspesor() {
        return espesor;
    }

    public void setEspesor(float espesor) {
        this.espesor = espesor;
    }

    public float getDiametroInterno() {
        return diametroInterno;
    }

    public void setDiametroInterno(float diametroInterno) {
        this.diametroInterno = diametroInterno;
    }

    public float getDiametroExterno() {
        return diametroExterno;
    }

    public void setDiametroExterno(float diametroExterno) {
        this.diametroExterno = diametroExterno;
    }

    public float getConductividadMaterial() {
        return conductividadMaterial;
    }

    public void setConductividadMaterial(float conductividadMaterial) {
        this.conductividadMaterial = conductividadMaterial;
    }

    public float getFactorPorIncrustaciones() {
        return factorPorIncrustaciones;
    }

    public void setFactorPorIncrustaciones(float factorPorIncrustaciones) {
        this.factorPorIncrustaciones = factorPorIncrustaciones;
    }

    public float getArea() {
        return (2*((float)Math.PI*(float)Math.pow((diametroInterno/2),2)));
    }

    public void setArea(float area) {
        this.area = area;
    }

    public float getTempChaqueta() {
        return tempChaqueta;
    }

    public void setTempChaqueta(float tempChaqueta) {
        this.tempChaqueta = tempChaqueta;
    }

    public float getDiametroChaqueta() {
        return diametroChaqueta;
    }

    public void setDiametroChaqueta(float diametroChaqueta) {
        this.diametroChaqueta = diametroChaqueta;
    }
}

