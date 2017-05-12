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

    public float calcularTemperaturaDeSalidaDelFluidoDeServicio(float tempEntradaFC, float tempEntradaFF, float CpFF, float flujoMasicoFF, float tempSalidaFF,
                    float CpFC, float flujoMasicoFC){
        float diferenciaTemperaturasFluidoCaliente = (((CpFF*flujoMasicoFF)*(tempSalidaFF-tempEntradaFF))/(CpFC*flujoMasicoFC));
        return (tempEntradaFC - diferenciaTemperaturasFluidoCaliente);
    }

    public float calcularTempMediaLogaritmica(float tempEntradaFluidoFrio, float tempSalidaFluidoFrio,
                                              float tempEntradaFluidoCaliente, float tempSalidaFluidoCaliente){
        return (float)(((tempEntradaFluidoCaliente-tempSalidaFluidoFrio)-(tempSalidaFluidoCaliente-tempEntradaFluidoFrio))/Math.log((tempEntradaFluidoCaliente-tempSalidaFluidoFrio)/(tempSalidaFluidoCaliente-tempEntradaFluidoFrio)));
    }

    public float calcularTempEstimadaParedPlaca(float tempMediaFluidoFrio, float tempMediaFluidoCaliente){
        return ((tempMediaFluidoCaliente+tempMediaFluidoFrio)/2);
    }

    public float calcularElAreaDeDiseñoRequerida(float flujoDeCalor, float tempMediaLogaritmica, float coefGlobalDeTC){
        return (flujoDeCalor/(coefGlobalDeTC*tempMediaLogaritmica));
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
        float[] constantes = obttenerConstantesParaNusselt(numeroDeReynoldsFluido);
        float c1 = constantes[0];
        float m = constantes[1];
        return (float)(c1*Math.pow(numeroDeReynoldsFluido,m)*Math.pow(numeroDePrantFluido,0.33)*Math.pow((viscosidadFluido/viscosidadFluidoP),0.17));
    }

    // FALTA IMPLEMENTAR
    private float[] obttenerConstantesParaNusselt(float numeroDeReynoldsFluido){
        int nRe = (short)numeroDeReynoldsFluido; // Parte entera del numero de Reynolds
        float[] datos = new float[2];
        float c1, m;
        if(nRe <= 20){
            c1 = 0.562f;
            m = 0.326f;
        }else if(nRe > 20 && nRe <= 500){
            c1 = 0.331f;
            m = 0.503f;
        }else{ // nRe > 500
            c1 = 0.087f;
            m = 0.718f;
        }
        datos[0] = c1;
        datos[1] = m;
        return (datos);
    }

    public float calcularCoeficienteTCPorConveccionDeFluido(float variableNusseltDelFluido, float conductividadTermicaFluido, float diametroEquivalente){
        return ((variableNusseltDelFluido*conductividadTermicaFluido)/diametroEquivalente);
    }

    public float calcularElCoefGlobalDeTC(float espesorPlacas, float conductividadTermicaMaterialDePlacas, float coefTCFludioFrio,
                                          float coefTCFluidoCaliente, float coefIncrustacionFluidoFrio, float coefIncrustacionFluidoCaliente){
        float coeficiente = (1/coefTCFludioFrio)+(espesorPlacas/conductividadTermicaMaterialDePlacas)+(1/coefTCFluidoCaliente)+
                coefIncrustacionFluidoFrio+coefIncrustacionFluidoCaliente;
        return (1/coeficiente);
    }

    /**
     * Se compara y se valida la relacion entre el coeficiete de diseño asumido para la practica, y el obtenido mediante los calculos.
     * @param coefDiseñoCalculado Coeficiente calculado.
     * @param coefDiseñoAsumido Coeficiente asumido.
     * @return True si la razon entre el coeficiente asumido y el calculado es aceptable, es decir, esta entre 0.955 y 1.05 ,
     * False de lo contrario.
     */
    public boolean validacionCoeficientesDeDiseño(float coefDiseñoCalculado, float coefDiseñoAsumido){
        float razon = (coefDiseñoAsumido/coefDiseñoCalculado);
        if(razon >= 0.955 && razon <= 1.05) return (true);
        return (false);
    }

    public float calcularElNumeroDePlacasTotalesRequeridas(float areaDeDiseñoRequerida, float areaDeTCPlacas){
        return (2+(areaDeDiseñoRequerida/areaDeTCPlacas));
    }
}
