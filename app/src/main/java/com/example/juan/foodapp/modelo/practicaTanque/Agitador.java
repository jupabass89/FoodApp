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

    private float tablaAgitador[][];
    private float altura;
    private float velocidadGiroRPS;
    private float diametro;
    private int tipoAgitador;

    public Agitador(float altura, float velocidadGiroRPS){
        llenarTipoAgitador();
        this.altura = altura;
        this.velocidadGiroRPS = velocidadGiroRPS;
    }

    public float[] getAgitador(){
        return tablaAgitador[tipoAgitador];
    }
    public void setAgitador(int tipoAgitador){this.tipoAgitador = tipoAgitador;}

    private void llenarTipoAgitador(){
        tablaAgitador = new float[7][4];
        tablaAgitador[0][0] = 0.36f;       //paleta
        tablaAgitador[0][1] = 2/3;
        tablaAgitador[0][2] = 0.21f ;
        tablaAgitador[0][3] = 300-100000;
        tablaAgitador[1][0] =0.54f;       //hélice
        tablaAgitador[1][1] =0.666f;
        tablaAgitador[1][2] =0.14f;
        tablaAgitador[1][3] = 30-2000 ;
        tablaAgitador[2][0] =1.00f;           //ancla 1
        tablaAgitador[2][1] =0.5f;
        tablaAgitador[2][2] = 0.18f;
        tablaAgitador[2][3] = 10-300;
        tablaAgitador[3][0] =0.36f;           //ancla 2
        tablaAgitador[3][1] =2/3;
        tablaAgitador[3][2] =0.18f ;
        tablaAgitador[3][3] = 300-40000;
        tablaAgitador[4][0] =0.54f;           //Disco turbina paletas planas
        tablaAgitador[4][1] =2/3;
        tablaAgitador[4][2] = 0.14f;
        tablaAgitador[4][3] = 30-300000;
        tablaAgitador[5][0] =0.74f;           //Disco turbina paletas planas con deflectores
        tablaAgitador[5][1] = 2/3;
        tablaAgitador[5][2] = 0.14f;
        tablaAgitador[5][3] =500-300000;
        tablaAgitador[6][0] =0.633f ;          //Banda heliciodal
        tablaAgitador[6][1] = 1/2;
        tablaAgitador[6][2] =0.18f;
        tablaAgitador[6][3] = 800000;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getVelocidadGiroRPS() {
        return velocidadGiroRPS;
    }

    public void setVelocidadGiroRPS(float velocidadGiroRPS) {
        this.velocidadGiroRPS = velocidadGiroRPS;
    }

    public float getDiametro() {
        return diametro;
    }

    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }
}
