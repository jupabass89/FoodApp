package com.example.juan.foodapp.modelo.practicaPlacas;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;

public class PracticaPlacas extends Practica {

    private OperacionDeFluidoPlacas operadorFluidos;
    private OperacionZonaPasterizacionPlacas operadorPasterizacion;
    private PasteurizadorPlacas pasteurizador;
    private Alimento fluidoFrio;
    private FluidoServicio fluidoCaliente;
    private ZonaPasterizacion zonaPasterizacion;
    private final float temperaturaDeSalidaDelAlimento = 75f;

    public PracticaPlacas(){
        operadorFluidos = new OperacionDeFluidoPlacas();
        operadorPasterizacion = new OperacionZonaPasterizacionPlacas();
        pasteurizador = new PasteurizadorPlacas();
        fluidoFrio = new Alimento();
        fluidoCaliente = new FluidoServicio();
        zonaPasterizacion = new ZonaPasterizacion();
        // Se deben setear los datos necesarios para la practica
        // Se utiliza el metodo configurarPractica()
        // Estos datos se obtienen de la vista a traves del controlador
    }

    /**
     * Se realizan los calculos necesarios para la zona de pasterizacion de la practica.
     */
    private void calcularDatosZonaPasterizacion(){
        fluidoFrio.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoCaliente.getTempEntrada()));
        // Se calcula la densidad de cada fluido
        fluidoFrio.setDensidad(operadorFluidos.calcularDensidadDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setDensidad(operadorFluidos.calcularDensidadDeFluido(fluidoCaliente.getTempEntrada()));

        // Se calcula el flujo masico de cada fluido
        fluidoFrio.setFlujoMasico(operadorFluidos.calcularFlujoMasicoDeFluido(zonaPasterizacion.getCaudalDeEntradaAlimento(),
                fluidoFrio.getDensidad()));
        fluidoCaliente.setFlujoMasico(operadorFluidos.calcularFlujoMasicoDeFluido(zonaPasterizacion.getCaudalDeEntradaFluidoDeServicio(),
                fluidoCaliente.getDensidad()));

        zonaPasterizacion.setFlujoDeCalor(operadorPasterizacion.calcularFlujoDeCalor(fluidoFrio.getFlujoMasico(),
                fluidoFrio.getCapacidadCalorifica(), fluidoFrio.getTempEntrada(), fluidoFrio.getTempSalida()));

        // Se calcula y se asigna la temperatura de salida del fluido caliente de la zona de pasterizacion
        fluidoCaliente.setTempSalida(operadorPasterizacion.calcularTempSalidaDelFluidoDeServicio(zonaPasterizacion.getFlujoDeCalor(),
                fluidoCaliente.getFlujoMasico(), fluidoCaliente.getCapacidadCalorifica(), fluidoCaliente.getTempEntrada()));
        fluidoCaliente.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoCaliente.getTemperaturaPromedio()));

        // Se calcula la temperatura media logaritmica
        zonaPasterizacion.setTemperaturaMediaLogaritmica(operadorPasterizacion.calcularTempMediaLogaritmica(fluidoFrio.getTempEntrada(),
                fluidoFrio.getTempSalida(), fluidoCaliente.getTempEntrada(), fluidoCaliente.getTempSalida()));

        // Se calcula la temperatura estimada de la pared de la placa
        zonaPasterizacion.setTemperaturaParedPlaca(operadorPasterizacion.calcularTempEstimadaParedPlaca(fluidoFrio.getTemperaturaPromedio(),
                fluidoCaliente.getTemperaturaPromedio()));

        // Se calcula el Area de TC utlizando el coeficiente de TC asumido
        zonaPasterizacion.setAreaDeDiseñoRequerida(operadorPasterizacion.calcularElAreaDeDiseñoRequerida(zonaPasterizacion.getFlujoDeCalor(),
                zonaPasterizacion.getTemperaturaMediaLogaritmica(), zonaPasterizacion.getCoeficienteDeDiseñoAsumido()));

        // Se calcula el numero de placas necesarias
        zonaPasterizacion.setAreaDeTCDeCadaPlaca(operadorPasterizacion.calcularElAreaDeTCDeCadaPlaca(pasteurizador.getAnchoPlaca(),
                pasteurizador.getLargoPlaca()));
        zonaPasterizacion.setNumeroDePlacasNecesarias(operadorPasterizacion.calcularNumeroDePlacasNecesarias(zonaPasterizacion.getAreaDeDiseñoRequerida(),
                zonaPasterizacion.getAreaDeTCDeCadaPlaca()));
        //if((int)zonaPasterizacion.getNumeroDePlacasNecesarias() > 700) generarAdvertencia

        // Se calcula el numero de canales totales
        zonaPasterizacion.setNumeroDeCanalesTotales(operadorPasterizacion.calcularElNumeroDeCanalesTotales(zonaPasterizacion.getNumeroDePlacasNecesarias()));

        // Se calcula el area de flujo
        zonaPasterizacion.setAreaDeFlujo(operadorPasterizacion.calcularAreaDeFlujo(pasteurizador.getAnchoPlaca(), pasteurizador.getDistanciaPlacas(),
                zonaPasterizacion.getNumeroDeCanalesTotales()));

        // Se calculan las densidades de flujo masica global de ambos fluidos
        zonaPasterizacion.setDensidadFlujoMasicaGlobalFluidoCaliente(operadorPasterizacion.calcularDensidadDeFlujoMasicaGlobalDeFluido(
                fluidoCaliente.getFlujoMasico(), zonaPasterizacion.getAreaDeFlujo()));
        zonaPasterizacion.setDensidadFlujoMasicaGlobalFluidoFrio(operadorPasterizacion.calcularDensidadDeFlujoMasicaGlobalDeFluido(
                fluidoFrio.getFlujoMasico(), zonaPasterizacion.getAreaDeFlujo()));

        // Se calculan la viscosidad y la conductividad termica de ambos fluidos
        fluidoFrio.setViscosidad(operadorFluidos.calcularViscosidadDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setViscosidad(operadorFluidos.calcularViscosidadDeFluido(fluidoCaliente.getTemperaturaPromedio()));
        fluidoFrio.setConductividadTermica(operadorFluidos.calcularConductividadTermicaDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setConductividadTermica(operadorFluidos.calcularConductividadTermicaDeFluido(fluidoCaliente.getTemperaturaPromedio()));

        // Se calculan el numero de Reynolds y de Prant para cada fluido
        zonaPasterizacion.setNumeroDeReynoldsFluidoFrio(operadorPasterizacion.calcularNumeroDeReynoldsFluido(zonaPasterizacion.getDensidadFlujoMasicaGlobalFluidoFrio(),
                pasteurizador.getDiametroEquivalente(), fluidoFrio.getViscosidad()));
        zonaPasterizacion.setNumeroDeReynoldsFluidoCaliente(operadorPasterizacion.calcularNumeroDeReynoldsFluido(zonaPasterizacion.getDensidadFlujoMasicaGlobalFluidoCaliente(),
                pasteurizador.getDiametroEquivalente(), fluidoCaliente.getViscosidad()));
        zonaPasterizacion.setNumeroDePrantFluidoFrio(operadorPasterizacion.calcularNumeroDePrantFluido(fluidoFrio.getCapacidadCalorifica(),
                fluidoFrio.getViscosidad(), fluidoFrio.getConductividadTermica()));
        zonaPasterizacion.setNumeroDePrantFluidoCaliente(operadorPasterizacion.calcularNumeroDePrantFluido(fluidoCaliente.getCapacidadCalorifica(),
                fluidoCaliente.getViscosidad(), fluidoCaliente.getConductividadTermica()));

        // Calculo de la variable Nusselt y del coeficienteTC para cada fluido
    }

    @Override
    public void configurarPractica() {
        fluidoFrio.setTempSalida(temperaturaDeSalidaDelAlimento);
        /*
        -	Temperatura del alimento a la entrada de la sección.
        -	Temperatura de entrada del fluido de servicio a la entrada de la sección.
        -	El Caudal (flujo másico) correspondiente a la entrada del alimento a la zona.
        -	El Caudal (flujo másico) correspondiente a la entrada del fluido de servicio a la zona.
        -	El Coeficiente Global de Transferencia de Calor de Diseño (UD) que se va a suponer en la práctica. (aceptar valores entre 10 y 8500)
        -	Coeficiente de incrustación para el fluido frio.
        -	Coeficiente de incrustación para el fluido caliente.
         */
    }

    @Override
    public void graficar(){}
}
