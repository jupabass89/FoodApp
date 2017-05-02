
package com.example.juan.foodapp.modelo.practicaTanque;

import com.example.juan.foodapp.modelo.Practica;


public class OperacionTanqueAgitado extends Practica {

    public OperacionTanqueAgitado(){

    }

    /**
     * Calula el Uc (Ut).
     * @return
     */
    /* coeficienteIndividual es ht.
     *conductividadParedTanque es conductividad del material al interior del tanque.
     * se necesita haber calculado previamente coeficienteTransfereciainteriorTanque y coeficienteIndividual
     *
     */
    public float calcularCoeficienteGlobalTrasnferenciaDeCalor(float conductividadParedTanque, float coeficienteIndividual,
            float diametroInternoTanque, float diametroExternoTanque, float factorObstruccionPorIncustracion,
                                                               float coeficienteTransfereciainteriorTanque){
        double exponente = Math.pow((diametroInternoTanque/diametroExternoTanque),2);
        float Ut= (1/coeficienteIndividual)
                    +((diametroInternoTanque/(diametroExternoTanque*conductividadParedTanque))*(diametroExternoTanque-diametroInternoTanque))
                    +((float)exponente*(1/coeficienteTransfereciainteriorTanque))+factorObstruccionPorIncustracion;

        return 1/Ut;
    }

    /*
     * este metodo corresponde a la ecuacion nro 7 de tanque agitado.
     * falta parametros de temperatura de entrada y salida del alimento a procesar(igual que la ecuacion de abajo)
     * se necesita haber calculado previamiente ut y areaTransferenciaDeCalor;
     */
    public float calcularTimepoEstimadoCalentamiento(float masaAlimento,float calorEspeficicoAlimiento,
                                                     float areaTransferenciaDeCalor, float temperaturaInicialAlimento,
                                                     float temperaturaEntradaAlimento,
                                                     float temperaturaSalidaAlimento, float ut){
        double log = (temperaturaInicialAlimento-temperaturaEntradaAlimento)/(temperaturaInicialAlimento-temperaturaSalidaAlimento);
        return (masaAlimento*calorEspeficicoAlimiento)/(ut*areaTransferenciaDeCalor)*((float)Math.log(log));
    }

    /*
    * este metodo corresponde a la ecuación nro 8 de tanque agitado.
    * faltan parametros de temperatura de entrada y salida del alimento(existe duda en esos parametros).
    * fata metodo para calcular K sub 2, de la misma ecuacion (hay duda en el significado de Uf).
    * falta hacer el calculo y retornarlo.
    * se necesita haber calculado previamiente ut y areaTransferenciaDeCalor;
    */
    public float calcularTiempoEstimadoEnfriamiento(float masaAlimento, float calorEspecificoAlimento,
                                                    float calorEspeficicoRefrigerante, float flujoMasicoRefrigerante,
                                                    float areaTransferenciaDeCalor, float temperaturaInicialFluidoRefrigerante,
                                                    float temperaturaDeEntradaAlimento, float temperaturaSalidaAlimento, float uf){
        double log = (temperaturaDeEntradaAlimento-temperaturaInicialFluidoRefrigerante)/(temperaturaSalidaAlimento-temperaturaInicialFluidoRefrigerante);
        float k2 = (kSub2(uf,areaTransferenciaDeCalor,flujoMasicoRefrigerante,calorEspecificoAlimento));

        return (masaAlimento*calorEspecificoAlimento)/(flujoMasicoRefrigerante*calorEspeficicoRefrigerante)*(k2/(k2-1))*
                (float)(Math.log(log));
    }

    private float kSub2(float uf,float areaTransferenciaDeCalor, float flujoMasicoRefrigerante, float calorEspecificoAlimento){
        return (float)Math.pow(Math.E, uf*areaTransferenciaDeCalor)/(flujoMasicoRefrigerante*calorEspecificoAlimento);
    }
}
