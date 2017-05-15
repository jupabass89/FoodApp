package com.example.juan.foodapp.modelo.practicaTanque;

public class Agitador {

    //PARÁMETROS (en tabla) DEPENDIENDO DE TIPO AGITADOR
    /* C[0] = a
    * C[1] = b
    * C[2] = m
    * C[3] = Reynolds
    *
    * F[0] = Paleta
    * F[1] = Hélice
    * F[2] = Ancla
    * F[3] = Disco turbina paletas planas
    * F[4] = Disco turbina paletas planas con deflectores
    * F[5] = Banda heliciodal
    */

    private double tablaAgitador[][];
    private double altura;
    private double velocidadGiroRPS;
    private double diametro;
    private int tipoAgitador;

    public Agitador(double altura, double velocidadGiroRPS){
        llenarTipoAgitador();
        this.altura = altura;
        this.velocidadGiroRPS = velocidadGiroRPS;
    }

    public double[] getAgitador(){
        return tablaAgitador[tipoAgitador];
    }
    public void setAgitador(int tipoAgitador){this.tipoAgitador = tipoAgitador;}

    private void llenarTipoAgitador(){
        tablaAgitador = new double[7][4];

        //paleta
        tablaAgitador[0][0] = 0.36f;
        tablaAgitador[0][1] = 0.6667f;
        tablaAgitador[0][2] = 0.21f ;
        tablaAgitador[0][3] = 300-100000f;

        //hélice
        tablaAgitador[1][0] =0.54f;
        tablaAgitador[1][1] =0.666f;
        tablaAgitador[1][2] =0.14f;
        tablaAgitador[1][3] = 30-2000f;

        //ancla 1
        tablaAgitador[2][0] =1.00f;
        tablaAgitador[2][1] =0.5f;
        tablaAgitador[2][2] = 0.18f;
        tablaAgitador[2][3] = 10-300;

        //ancla 2
        tablaAgitador[3][0] =0.36f;
        tablaAgitador[3][1] =0.6667f;
        tablaAgitador[3][2] =0.18f ;
        tablaAgitador[3][3] = 300-40000f;

        //Disco turbina paletas planas
        tablaAgitador[4][0] =0.54f;
        tablaAgitador[4][1] =0.6667f;
        tablaAgitador[4][2] = 0.14f;
        tablaAgitador[4][3] = 30-300000f;

        //Disco turbina paletas planas con deflectores
        tablaAgitador[5][0] =0.74f;
        tablaAgitador[5][1] = 0.6667f;
        tablaAgitador[5][2] = 0.14f;
        tablaAgitador[5][3] =500-300000;

        //Banda heliciodal
        tablaAgitador[6][0] =0.633f ;
        tablaAgitador[6][1] = 0.5f;
        tablaAgitador[6][2] =0.18f;
        tablaAgitador[6][3] = 800000f;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public double getVelocidadGiroRPS() {
        return velocidadGiroRPS;
    }

    public void setVelocidadGiroRPS(float velocidadGiroRPS) {
        this.velocidadGiroRPS = velocidadGiroRPS;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }
}