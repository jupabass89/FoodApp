package com.example.juan.foodapp.modelo.practicaTanque;

/**
 * Created by jdavid on 7/05/17.
 */

public class OperacionAlimento {
    private double porcentajeGrasa;
    private double porcentajeProteina;
    private double porcentajeFibra;
    private double porcentajeCeniza;
    private double porcentajeCarbohidratos;

    public OperacionAlimento(double porcentajeGrasa, double porcentajeProteina, double porcentajeFibra,
                             double porcentajeCeniza, double porcentajeCarbohidratos){
        this.porcentajeCarbohidratos =porcentajeCarbohidratos;
        this.porcentajeCeniza = porcentajeCeniza;
        this.porcentajeFibra = porcentajeFibra;
        this.porcentajeProteina = porcentajeProteina;
        this.porcentajeGrasa = porcentajeGrasa;

    }

    public OperacionAlimento(){}



    public double calcularCpAlimento(double temperatura){
        double exponente1 = Math.pow(10,-3)*temperatura;
        double exponente2 = Math.pow(10,-6)*temperatura*temperatura;
        double proteina =  (2.0082+(1.2089*exponente1)-(1.3129*exponente2));
        double grasa = (1.9842+(1.4733*exponente1)-(4.8008*exponente2));
        double carbohidrato = (1.5488+(1.9625*exponente1)-(5.9399*exponente2));
        double fibra = (1.8459+(1.8306*exponente1)-4.6509*exponente2);
        double ceniza = (1.0926+(1.8896*exponente1)-3.6817*exponente2);
        return (proteina*porcentajeProteina+grasa*porcentajeGrasa+carbohidrato*porcentajeCarbohidratos+fibra*porcentajeFibra+ceniza*porcentajeCeniza);
    }
    public double calcularConductividadAlimento(double temperatura){
        double exponente1 = Math.pow(10,-3)*temperatura;
        double exponente2 = Math.pow(10,-6)*temperatura*temperatura;
        double exponente0 = Math.pow(10,-1);
        double proteina =  ((1.7881*exponente0)+(1.1958*exponente1)-(2.7178*exponente2));
        double grasa = ((1.8071*exponente0)-(0.27604*exponente1)-(0.17749*exponente2));
        double carbohidrato = ((2.0141*exponente0)+(1.3874*exponente1)-(4.3312*exponente2));
        double fibra = ((1.8331*exponente0)+(1.2497*exponente1)-(3.1683*exponente2));
        double ceniza = ((3.2962*exponente0)+(1.4011*exponente1)-(2.9069*exponente2));
        return (proteina*porcentajeProteina+grasa*porcentajeGrasa+carbohidrato*porcentajeCarbohidratos+fibra*porcentajeFibra+ceniza*porcentajeCeniza);
    }

    public double   calcularDensidadAlimento(double temperatura){
        double exponente1 = Math.pow(10,3);
        double exponente2 = Math.pow(10,-1)*temperatura;
        double proteina = ((1.3299*exponente1)-(5.1840*exponente2));
        double grasa = ((9.2559*100)-(4.1757*exponente2));
        double carbohidrato = ((1.5991*exponente1)-(3.1046*exponente2));
        double fibra = ((1.3115*exponente1)-(3.6589*exponente2));
        double ceniza = ((2.4238*exponente1)-(2.8063*exponente2));
        return 1/(porcentajeProteina/proteina+porcentajeGrasa/grasa+porcentajeCarbohidratos/carbohidrato+porcentajeFibra/fibra+porcentajeCeniza/ceniza);
    }

    public double calcularCpAgua(double temperatura){
        return (4176.2-((0.090864*temperatura)+(0.0054731*temperatura*temperatura)));
    }

    public double calcularConductividadAgua(double temperatura){
        return (0.57109+((0.0017625*temperatura)-(0.0000067376*temperatura*temperatura)));
    }

    public double calcularDensidadAgua(double temperatura){
        return (997.18+((0.0031439*temperatura)-(0.0037574*temperatura*temperatura)));
    }

    public double calcularViscosidadAgua(double temperatura){
        return 0.00055;

    }

    public double calcularExpancionTermicaAgua(double temperaturaChaqueta){
        return (0.00002+((0.00001*temperaturaChaqueta)-(0.00000004*temperaturaChaqueta)));
    }
}
