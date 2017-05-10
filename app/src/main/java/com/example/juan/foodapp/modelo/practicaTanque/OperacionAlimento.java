package com.example.juan.foodapp.modelo.practicaTanque;

/**
 * Created by jdavid on 7/05/17.
 */

public class OperacionAlimento {
    private float porcentajeGrasa;
    private float porcentajeProteina;
    private float porcentajeFibra;
    private float porcentajeCeniza;
    private float porcentajeCarbohidratos;

    public OperacionAlimento(float porcentajeGrasa, float porcentajeProteina, float porcentajeFibra,
                             float porcentajeCeniza, float porcentajeCarbohidratos){
        this.porcentajeCarbohidratos =porcentajeCarbohidratos;
        this.porcentajeCeniza = porcentajeCeniza;
        this.porcentajeFibra = porcentajeFibra;
        this.porcentajeProteina = porcentajeProteina;
        this.porcentajeGrasa = porcentajeGrasa;

    }

    public float calcularCpAlimento(float temperatura){
        float exponente1 = (float)Math.pow(10,-3)*temperatura;
        float exponente2 = (float)Math.pow(10,-6)*temperatura*temperatura;
        float proteina = (float) (2.0082+(1.2089*exponente1)-(1.3129*exponente2));
        float grasa = (float)(1.9842+1.4733*exponente1-4.8008*exponente2);
        float carbohidrato = (float)(1.5488+1.9625*exponente1-5.9399*exponente2);
        float fibra = (float)(1.8459+1.8306*exponente1-4.6509*exponente2);
        float ceniza = (float)(1.0926+1.8896*exponente1-3.6817*exponente2);
        return (proteina*porcentajeProteina+grasa*porcentajeGrasa+carbohidrato*porcentajeCarbohidratos+fibra*porcentajeFibra+ceniza*porcentajeCeniza);
    }
    public float calcularConductividadAlimento(float temperatura){
        float exponente1 = (float)Math.pow(10,-3)*temperatura;
        float exponente2 = (float)Math.pow(10,-6)*temperatura*temperatura;
        float exponente0 = (float)Math.pow(10,-1);
        float proteina = (float) (1.7881*exponente0+(1.1958*exponente1)-(2.7178*exponente2));
        float grasa = (float)(1.8071*exponente0-0.27604*exponente1-0.17749*exponente2);
        float carbohidrato = (float)(2.0141*exponente0+1.3874*exponente1-4.3312*exponente2);
        float fibra = (float)(1.8331*exponente0+1.2497*exponente1-3.1683*exponente2);
        float ceniza = (float)(3.2962*exponente0+1.4011*exponente1-2.9069*exponente2);
        return (proteina*porcentajeProteina+grasa*porcentajeGrasa+carbohidrato*porcentajeCarbohidratos+fibra*porcentajeFibra+ceniza*porcentajeCeniza);
    }

    public float calcularDensidadAlimento(float temperatura){
        float exponente1 = (float)Math.pow(10,3);
        float exponente2 = (float)Math.pow(10,-1)*temperatura;
        float proteina = (float) (1.3299*exponente1-5.1840*exponente2);
        float grasa = (float)(0.92559*exponente1-4.1757*exponente2);
        float carbohidrato = (float)(1.5991*exponente1-3.1046*exponente2);
        float fibra = (float)(1.3115*exponente1-3.6589*exponente2);
        float ceniza = (float)(2.4238*exponente1-2.8063*exponente2);
        return (proteina*porcentajeProteina+grasa*porcentajeGrasa+carbohidrato*porcentajeCarbohidratos+fibra*porcentajeFibra+ceniza*porcentajeCeniza);
    }

    public float calcularCpAgua(float temperatura){
        return (float)(4176.2-((0.090864*temperatura)+(0.0054731*temperatura*temperatura)));
    }

    public float calcularConductividadAgua(float temperatura){
        return (float)(0.57109+((0.0017625*temperatura)-(0.0000067376*temperatura*temperatura)));
    }

    public float calcularDensidadAgua(float temperatura){
        return (float)(997.18+((0.0031439*temperatura)-(0.0037574*temperatura*temperatura)));
    }

    public float calcularViscosidadAgua(float temperatura){
        return (float)(0.0013-((0.00002*temperatura)+(0.0000001*temperatura*temperatura)));
    }

    public float calcularExpancionTermicaAgua(float temperaturaChaqueta){
        return (float)(0.00002+((0.00001*temperaturaChaqueta)-(0.00000004*temperaturaChaqueta)));
    }
}
