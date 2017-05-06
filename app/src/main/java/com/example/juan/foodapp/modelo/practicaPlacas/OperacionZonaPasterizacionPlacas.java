package com.example.juan.foodapp.modelo.practicaPlacas;

/**
 * Clase que se encarga de realizar los calculos matematicos correspondientes
 * a la zona de pasterizacion de la practica de pasteurizacion en placas.
 */
public class OperacionZonaPasterizacionPlacas {

    // Cp es la capacidad calorifica del fluido frio.
    public float calcularFlujoDeCalor(float flujoMasicoFluidoFrio, float CpFluidoFrio, float tempEntradaFluido, float tempSalidaFluido){
        return (flujoMasicoFluidoFrio*CpFluidoFrio*(tempSalidaFluido-tempEntradaFluido));
    }

    // Cp es la capacidad calorifica del fluido caliente
    public float calcularTempSalidaDelFluidoDeServicio(float flujoDeCalor, float flujoMasicoFluidoCaliente, float CpFluidoCaliente, float tempEntradaFluido){
        return ((flujoDeCalor+(flujoMasicoFluidoCaliente*CpFluidoCaliente*tempEntradaFluido))/(flujoMasicoFluidoCaliente*CpFluidoCaliente));
    }

    public float calcularTempMediaLogaritmica(float tempEntradaFluidoFrio, float tempSalidaFluidoFrio,
                                              float tempEntradaFluidoCaliente, float tempSalidaFluidoCaliente){
        return (float)(((tempEntradaFluidoCaliente-tempSalidaFluidoFrio)-(tempSalidaFluidoCaliente-tempEntradaFluidoFrio))/Math.log((tempEntradaFluidoCaliente-tempSalidaFluidoFrio)/(tempSalidaFluidoCaliente-tempEntradaFluidoFrio)));
    }

    public float calcularTempEstimadaParedPlaca(float tempMediaFluidoFrio, float tempMediaFluidoCaliente){
        return ((tempMediaFluidoCaliente+tempMediaFluidoFrio)/2);
    }

    public float calcularElAreaDeDiseñoRequerida(float flujoDeCalor, float tempMediaLogaritmica, float coefGlobalDeTCAsumido){
        return (flujoDeCalor/(coefGlobalDeTCAsumido*tempMediaLogaritmica));
    }

    public float calcularElAreaDeTCDeCadaPlaca(float anchoPlaca, float largoPlaca){
        return (anchoPlaca*largoPlaca);
    }

    public float calcularNumeroDePlacasNecesarias(float areaDeDiseñoRequerida, float areaDeTCDeCadaPlaca){
        return (areaDeDiseñoRequerida/areaDeTCDeCadaPlaca);
    }

    public float calcularElNumeroDeCanalesTotales(float numeroDePlacasNecesarias){
        return ((numeroDePlacasNecesarias+1)/2);
    }

    public float calcularAreaDeFlujo(float anchoPlaca, float separacionEntrePlacas, float numeroDeCanales){
        return (anchoPlaca*separacionEntrePlacas*numeroDeCanales);
    }

    public float calcularDensidadDeFlujoMasicaGlobalDeFluido(float flujoMasicoFluido, float areaDeFlujoTotal){
        return (flujoMasicoFluido/areaDeFlujoTotal);
    }

    public float calcularNumeroDeReynoldsFluido(float densidadDeFlujoMasicaGlobalFluido, float diametroEquivalente, float viscosidadFluido){
        return ((densidadDeFlujoMasicaGlobalFluido*diametroEquivalente)/viscosidadFluido);
    }

    public float calcularNumeroDePrantFluido(float CpFluido, float viscosidadFluido, float conductividadTermicaFluido){
        return ((CpFluido*viscosidadFluido)/conductividadTermicaFluido);
    }

    // viscosidadFluidoP corresponde al calculo de la viscosidad del fluido utilizando como temperatura promedio
    // la temperatura estimada de la pared de la placa.
    public float calcularNusseltParaFluido(float numeroDeReynoldsFluido, float numeroDePrantFluido, float viscosidadFluido,
                                           float viscosidadFluidoP){
        return(0);
    }
}
