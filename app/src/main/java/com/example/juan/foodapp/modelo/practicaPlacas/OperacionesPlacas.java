package com.example.juan.foodapp.modelo.practicaPlacas;

/**
 * Clase que se encarga de realizar los calculos matematicos correspondientes
 * a la practica de pasteurizacion en placas
 */
public class OperacionesPlacas {

    // si formula es igual a 1 se calculara el flujo de calor con los datos del fluido de servicio
    // de lo contrario se calculara el flujo de calor con los datos del alimento
    public float flujoDeCalor(float flujoMasico, float capacidadCalorifica, float tempEntrada, float tempSalida, int formula){
        if(formula == 1)
            return (flujoMasico*capacidadCalorifica*(tempEntrada-tempSalida));
        else
            return (flujoMasico*capacidadCalorifica*(tempSalida-tempEntrada));
    }

    public float tempEstimadaParedPlaca(float tempMediaAlimento, float tempMediaFluidoDeServicio){
        return ((tempMediaAlimento+tempMediaFluidoDeServicio)/2);
    }

    // Se calcula el coeficiente individual de TC del alimento
    public float coeficientePorConveccionReal(float flujoDeCalor, float AreaDeTransferenciaDeCalor,
                                              float tempEstimadaParedPlaca, float tempMediaAlimento){
        return (flujoDeCalor/(AreaDeTransferenciaDeCalor*(tempEstimadaParedPlaca-tempMediaAlimento)));
    }

    public float areaDeFlujo(float anchoDePlaca, float separacionEntrePlacas, int numeroDeCanales){
        return (anchoDePlaca*separacionEntrePlacas*numeroDeCanales);
    }

    public float densidadDeFlujoMasicaGlobal(float flujoMasicoFluido, float areaDeFlujo){
        return (flujoMasicoFluido/areaDeFlujo);
    }

    public float numeroDeReynolds(float diametroHidraulicoEq, float viscosidadFluido, float densidadDeFlujoMasicaGlobal){
        return ((densidadDeFlujoMasicaGlobal*diametroHidraulicoEq)/viscosidadFluido);
    }

    public float numeroDePrant(float capacidadCalorifica, float viscosidadFluido, float conductividadTermicaDelFS){
        return ((capacidadCalorifica*viscosidadFluido)/conductividadTermicaDelFS);
    }

    // Se calcula el coeficiente individual de transferencia de calor teórico
    public float coeficientePorConveccionTeorico(float conductividadTermicaDelFS, float diametroHidraulicoEq,
                                                 float numeroDeReynolds, float numeroDePrant){
        return(float) (0.2536*(conductividadTermicaDelFS/diametroHidraulicoEq)*Math.pow(numeroDeReynolds, 0.65)*Math.pow(numeroDePrant, 0.4));
    }

    public float porcentajeDeError(float coeficienteTCReal, float coeficienteTCTeorico){
        return (Math.abs((coeficienteTCTeorico-coeficienteTCReal)/coeficienteTCTeorico)*100);
    }

    public float temperaturaMediaLogaritmica(float tempEntradaAlimento, float tempSalidaAlimento,
                                             float tempEntradaFluidoDeServicio, float tempSalidaFluidoDeServicio){
        float numerador = (tempEntradaFluidoDeServicio-tempSalidaAlimento)-(tempSalidaFluidoDeServicio-tempEntradaAlimento);
        float denominador =(float) Math.log((tempEntradaFluidoDeServicio-tempSalidaAlimento)/(tempSalidaFluidoDeServicio-tempEntradaAlimento));
        return (numerador/denominador);
    }

    public float coeficienteGlobalDeTC(float flujoDeCalor, float areaDeTransferenciaCalor, float TempMediaLogaritmica){
        return (flujoDeCalor/(areaDeTransferenciaCalor*TempMediaLogaritmica));
    }

    public float numeroUnidadesDeTC(float tempEntradaAlimento, float tempSalidaAlimento, float tempMediaLogaritmica){
        return ((tempSalidaAlimento-tempEntradaAlimento)/tempMediaLogaritmica);
    }

    /*public float efectividadDeIntercambiadorDeCalor(){
    }*/

    public float factorFriccionDeFanning(float numeroDeReynolds){
        return(float) (2.5/Math.pow(numeroDeReynolds, 0.3));
    }

    public float caidaDePresion(float factorFriccionDeFanning, float densidadDeFlujoMasicaGlobal, float longitudPlaca,
                                float diametroEquivalente, float densidadDelFluido){
        return (float) (2*factorFriccionDeFanning*((Math.pow(longitudPlaca, 2)*longitudPlaca)/(9.8*diametroEquivalente*densidadDelFluido)));
    }
}
