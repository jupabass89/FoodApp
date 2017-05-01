package com.example.juan.foodapp.modelo.practicaPlacas;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;

public class PracticaPlacas extends Practica {

    private ZonaPasteurizador regeneracion, pasteurizacion, enfriamiento;
    private Alimento alimento;
    private FluidoServicio fluidoDeServicio;
    private PasteurizadorPlacas pasteurizador;
    private OperacionesPlacas operador;

    public PracticaPlacas(){
        operador = new OperacionesPlacas();
        pasteurizador = new PasteurizadorPlacas();
        regeneracion = new ZonaPasteurizador();
        pasteurizacion = new ZonaPasteurizador();
        enfriamiento = new ZonaPasteurizador();
        alimento = new Alimento();
        fluidoDeServicio = new FluidoServicio();
        // Se deben setear los datos necesarios del Alimento y del Fluido de servicio
        // Estos datos son tomados del controlador de la vista correspondiente
    }

    private void calcularDatosDeZona(ZonaPasteurizador zona){
        // Se calcula el flujo de calor con los datos del alimento
        zona.setFlujoDeCalor(operador.flujoDeCalor(alimento.getFlujoMasico(), alimento.getCapacidadCalorifica(),
                alimento.getTempEntrada(), alimento.getTempSalida(), 0));
        zona.setTemperaturaParedPlaca(operador.tempEstimadaParedPlaca(alimento.getTemperaturaPromedio(),
                fluidoDeServicio.getTemperaturaPromedio()));
        zona.setCoefIndTCAlimento(operador.coeficientePorConveccionReal(zona.getFlujoDeCalor(),
                pasteurizador.getAreaCirculacion(), zona.getTemperaturaParedPlaca(), alimento.getTemperaturaPromedio()));
        // Preguntar sobre el Area de Circulacion

        zona.setAreaDeFlujo(operador.areaDeFlujo(pasteurizador.getAnchoPlaca(), pasteurizador.getDistanciaPlacas(),
                0));
        // Preguntar sobre el numero de canalaes

        zona.setDensidadDeFlujoMasicoGlobal(operador.densidadDeFlujoMasicaGlobal(fluidoDeServicio.getFlujoMasico(),
                zona.getAreaDeFlujo()));

        zona.setNumeroDeReynolds(operador.numeroDeReynolds(pasteurizador.getDiametroEquivalente(),
                fluidoDeServicio.getViscosidad(), zona.getDensidadDeFlujoMasicoGlobal()));
        zona.setNumeroDePrant(operador.numeroDePrant(0, fluidoDeServicio.getViscosidad(), fluidoDeServicio.getConductividadTermica()));
        // Preguntar sobre la Capacidad Calorifica de AMBOS fluidos necesitada en este caso

        zona.setCoefIndTCTeorico(operador.coeficientePorConveccionTeorico(fluidoDeServicio.getConductividadTermica(),
                pasteurizador.getDiametroEquivalente(), zona.getNumeroDeReynolds(), zona.getNumeroDePrant()));
        zona.setPorcentajeDeError(operador.porcentajeDeError(zona.getCoefIndTCAlimento(), zona.getCoefIndTCTeorico()));
        zona.setTemperaturaMediaLogaritmica(operador.temperaturaMediaLogaritmica(alimento.getTempEntrada(), alimento.getTempSalida(),
                fluidoDeServicio.getTempEntrada(), fluidoDeServicio.getTempSalida()));
        zona.setCoefGlobalTC(operador.coeficienteGlobalDeTC(zona.getFlujoDeCalor(), pasteurizador.getAreaCirculacion(),
                zona.getTemperaturaMediaLogaritmica()));
        // Preguntar sobre el Area de Circulacion

        zona.setNUT(operador.numeroUnidadesDeTC(alimento.getTempEntrada(), alimento.getTempSalida(), zona.getTemperaturaMediaLogaritmica()));

        // Calcular la efectividad

        zona.setFactorFriccionFanning(operador.factorFriccionDeFanning(zona.getNumeroDeReynolds()));

        // Calcular la caida de presion
        // Esta utlima se le evalua al alimento o al fluido de servicio?
    }
}
