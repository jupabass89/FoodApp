package com.example.juan.foodapp.modelo.practicaPlacas;

import android.content.Context;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.Estudiante;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;
import com.example.juan.foodapp.modelo.serviciosPractica.Informe;

import java.util.ArrayList;

public class PracticaPlacas extends Practica {

    private Context contexto;
    private OperacionDeFluidoPlacas operadorFluidos;
    private OperacionZonaPasterizacionPlacas operadorPasterizacion;
    private PasteurizadorPlacas pasteurizador;
    private Alimento fluidoFrio;
    private FluidoServicio fluidoCaliente;
    private ZonaPasterizacion zonaPasterizacion;
    private final float TEMPERATURA_SALIDA_ALIMENTO = 75f;
    private final float FLUJO_MASICO_FLUIDO_DE_SERVICIO = 0.6f; // Fluido Caliente - AGUA
    boolean razonDeCoeficientesAceptable;
    private String[][] resultados;
    private String[][] datosDeEntrada;

    public PracticaPlacas(Context contexto, String asignatura, String profesor){
        super(contexto,asignatura,profesor);
        this.contexto = contexto;

        this.setNombreLaboratorio("Laboratorio de Operaciones Unitaras");
        this.setNombre("Transeferencia de calor en un pasterurizador de placas: zona de pasterizacion");

        operadorFluidos = new OperacionDeFluidoPlacas();
        operadorPasterizacion = new OperacionZonaPasterizacionPlacas();
        pasteurizador = new PasteurizadorPlacas();
        fluidoFrio = new Alimento();
        fluidoCaliente = new FluidoServicio();
        zonaPasterizacion = new ZonaPasterizacion();
    }

    /**
     * Se realizan los calculos necesarios para la zona de pasterizacion de la practica.
     * Se realizan iteraciones de calculos hasta que la razon entre el coeficiente de diseño asumido y el coeficiente
     * de diseño calculado sea aceptable.
     */
    public void calcularDatosZonaPasterizacion(){
        int iteraciones = 0;
        fluidoFrio.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoCaliente.getTempEntrada()));
        // Se calcula la densidad de cada fluido
        fluidoFrio.setDensidad(operadorFluidos.calcularDensidadDeFluido(fluidoFrio.getTemperaturaPromedio()));
        fluidoCaliente.setDensidad(operadorFluidos.calcularDensidadDeFluido(fluidoCaliente.getTempEntrada()));

        // Se calcula el flujo masico del fluido frio
        fluidoFrio.setFlujoMasico(operadorFluidos.calcularFlujoMasicoDeFluido(zonaPasterizacion.getCaudalDeEntradaAlimento(),
                fluidoFrio.getDensidad()));

        // Se calcula y se asigna la temperatura de salida del fluido caliente de la zona de pasterizacion
        fluidoCaliente.setTempSalida(operadorPasterizacion.calcularTemperaturaDeSalidaDelFluidoDeServicio(fluidoCaliente.getTempEntrada(), fluidoFrio.getTempEntrada(), fluidoFrio.getCapacidadCalorifica(),
                fluidoFrio.getFlujoMasico(), fluidoFrio.getTempSalida(), fluidoCaliente.getCapacidadCalorifica(), fluidoCaliente.getFlujoMasico()));
        fluidoCaliente.setCapacidadCalorifica(operadorFluidos.calcularCapacidadCalorificaDeFluido(fluidoCaliente.getTemperaturaPromedio()));

        zonaPasterizacion.setFlujoDeCalor(operadorPasterizacion.calcularFlujoDeCalor(fluidoFrio.getFlujoMasico(),
                fluidoFrio.getCapacidadCalorifica(), fluidoFrio.getTempEntrada(), fluidoFrio.getTempSalida()));

        // Se calcula la temperatura media logaritmica
        zonaPasterizacion.setTemperaturaMediaLogaritmica(operadorPasterizacion.calcularTempMediaLogaritmica(fluidoFrio.getTempEntrada(),
                fluidoFrio.getTempSalida(), fluidoCaliente.getTempEntrada(), fluidoCaliente.getTempSalida()));

        // Se calcula la temperatura estimada de la pared de la placa
        zonaPasterizacion.setTemperaturaParedPlaca(operadorPasterizacion.calcularTempEstimadaParedPlaca(fluidoFrio.getTemperaturaPromedio(),
                fluidoCaliente.getTemperaturaPromedio()));

        // Se calcula el area de TC de cada placa
        zonaPasterizacion.setAreaDeTCDeCadaPlaca(operadorPasterizacion.calcularElAreaDeTCDeCadaPlaca(pasteurizador.getAnchoPlaca(),
                pasteurizador.getLargoPlaca()));

        do {
            realizarCalculosParaCoeficienteGlobalDeDiseño();
            iteraciones++;
            razonDeCoeficientesAceptable = operadorPasterizacion.validacionCoeficientesDeDiseño(zonaPasterizacion.getCoeficienteDeDiseñoCalculado(),
                    zonaPasterizacion.getCoeficienteDeDiseñoAsumido());
            if (!razonDeCoeficientesAceptable)
                zonaPasterizacion.setCoeficienteDeDiseñoAsumido(zonaPasterizacion.getCoeficienteDeDiseñoCalculado());
        }while(!razonDeCoeficientesAceptable && iteraciones <= 10);
        if(iteraciones == 10){
            zonaPasterizacion.setAreaDeTCDeCadaPlaca(0);
            return;
        }

        // Se recalcula el Area de TC requerida, utilizando el coeficiente de TC calculado
        zonaPasterizacion.setAreaDeDiseñoRequerida(operadorPasterizacion.calcularElAreaDeDiseñoRequerida(zonaPasterizacion.getFlujoDeCalor(),
                zonaPasterizacion.getTemperaturaMediaLogaritmica(), zonaPasterizacion.getCoeficienteDeDiseñoCalculado()));

        // Se calcula el numero total del placas para la TC requerida
        zonaPasterizacion.setNumeroDePlacasTotalesRequeridas(operadorPasterizacion.calcularElNumeroDePlacasTotalesRequeridas(zonaPasterizacion.getAreaDeDiseñoRequerida(),
                zonaPasterizacion.getAreaDeTCDeCadaPlaca()));
    }

    /**
     * Se ralizan los calculos correspondientes a la zona pasteurizacion, desde del calculo del area de TC utlizando el coeficiente de TC de diseño asumido,
     * hasta calcular el coeficiente de diseño real.
     */
    private void realizarCalculosParaCoeficienteGlobalDeDiseño(){
        // Se calcula el Area de TC utlizando el coeficiente de TC asumido
        zonaPasterizacion.setAreaDeDiseñoRequerida(operadorPasterizacion.calcularElAreaDeDiseñoRequerida(zonaPasterizacion.getFlujoDeCalor(),
                zonaPasterizacion.getTemperaturaMediaLogaritmica(), zonaPasterizacion.getCoeficienteDeDiseñoAsumido()));

        // Se calcula el numero de placas necesarias
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
        float viscosidadFluidosP = operadorFluidos.calcularViscosidadDeFluido(zonaPasterizacion.getTemperaturaParedPlaca());
        // Esta viscosidad corresponde al calculo de la viscosidad de los fluidos utilizando como temperatura promedio la temperatura estimada de la pared de la placa
        zonaPasterizacion.setNumeroNusseltFluidoFrio(operadorPasterizacion.calcularNusseltParaFluido(zonaPasterizacion.getNumeroDeReynoldsFluidoFrio(),
                zonaPasterizacion.getNumeroDePrantFluidoFrio(), fluidoFrio.getViscosidad(),viscosidadFluidosP));
        zonaPasterizacion.setNumeroNusseltFluidoCaliente(operadorPasterizacion.calcularNusseltParaFluido(zonaPasterizacion.getNumeroDeReynoldsFluidoCaliente(),
                zonaPasterizacion.getNumeroDePrantFluidoCaliente(), fluidoCaliente.getViscosidad(), viscosidadFluidosP));
        zonaPasterizacion.setCoeficienteTCPorConveccionFluidoFrio(operadorPasterizacion.calcularCoeficienteTCPorConveccionDeFluido(zonaPasterizacion.getNumeroNusseltFluidoFrio(),
                fluidoFrio.getConductividadTermica(), pasteurizador.getDiametroEquivalente()));
        zonaPasterizacion.setCoeficienteTCPorConveccionFluidoCaliente(operadorPasterizacion.calcularCoeficienteTCPorConveccionDeFluido(zonaPasterizacion.getNumeroNusseltFluidoCaliente(),
                fluidoCaliente.getConductividadTermica(), pasteurizador.getDiametroEquivalente()));

        // Se calcula el coeficiente de diseño
        zonaPasterizacion.setCoeficienteDeDiseñoCalculado(operadorPasterizacion.calcularElCoefGlobalDeTC(pasteurizador.getEspesorPlacas(),
                pasteurizador.getConductividadTermica(), zonaPasterizacion.getCoeficienteTCPorConveccionFluidoFrio(), zonaPasterizacion.getCoeficienteTCPorConveccionFluidoCaliente(),
                fluidoFrio.getCoeficienteDeIncrustacion(), fluidoCaliente.getCoeficienteDeIncrustacion()));
    }

    public float getFlujoMasicoAlimento(){
        return (fluidoFrio.getFlujoMasico());
    }

    public float getAreaTC(){
        return (zonaPasterizacion.getAreaDeTCDeCadaPlaca());
    }

    public float getCoeficienteDeDiseñoCalculado(){
        return (zonaPasterizacion.getCoeficienteDeDiseñoCalculado());
    }

    // Se guardan tambien los datos de entrada
    public void guardarResultados(){
        this.resultados = obtenerResultados();
        this.datosDeEntrada = obtenerDatosDeEntrada();
    }

    public String[][] getResultados() {
        return resultados;
    }

    public String[][] getDatosDeEntrada() {
        return datosDeEntrada;
    }

    public String[][] obtenerResultados(){
        String[][] datos = new String[21][2];
        datos[0][0] = "Temp. media fluido frio (°C)";
        datos[1][0] = "Temp. media fluido caliente (°C)";
        datos[2][0] = "Temp. salida del fluido frio (°C)";
        datos[3][0] = "Temp. salida del fluido caliente (°C)";
        datos[4][0] = "Flujo de calor Q (J/s)";
        datos[5][0] = "Temperatura media logaritmica (°C)";
        datos[6][0] = "Temp. aproximada pared de placa (°C)";
        datos[7][0] = "Numero de Placas Necesarias";
        datos[8][0] = "Area TC de cada placa (m^2)";
        datos[9][0] = "Area de flujo total (m^2)";
        datos[10][0] = "Densidad flujo masica global fluido frio (kg/s.m^2)";
        datos[11][0] = "Densidad flujo masica global fluido caliente (kg/s.m^2)";
        datos[12][0] = "Numero de Reynolds fluido frio";
        datos[13][0] = "Numero de Prant fluido frio.";
        datos[14][0] = "Numero de Reynolds fluido caliente";
        datos[15][0] = "Numero de Prant fluido caliente";
        datos[16][0] = "Coef. TC por convección fluido frio (W/ m^2.°C)";
        datos[17][0] = "Coef. TC por convección fluido caliente (W/ m^2.°C)";
        datos[18][0] = "Coef. global de TC calculado (W/ m^2.°C)";
        datos[19][0] = "Area total de TC requerida (m^2)";
        datos[20][0] = "Numero de placas totales requeridas para la TC";
        datos[0][1] = String.valueOf(fluidoFrio.getTemperaturaPromedio());
        datos[1][1] = String.valueOf(fluidoCaliente.getTemperaturaPromedio());
        datos[2][1] = String.valueOf(fluidoFrio.getTempSalida());
        datos[3][1] = String.valueOf(fluidoCaliente.getTempSalida());
        datos[4][1] = String.valueOf(zonaPasterizacion.getFlujoDeCalor());
        datos[5][1] = String.valueOf(zonaPasterizacion.getTemperaturaMediaLogaritmica());
        datos[6][1] = String.valueOf(zonaPasterizacion.getTemperaturaParedPlaca());
        datos[7][1] = String.valueOf(zonaPasterizacion.getNumeroDePlacasNecesarias());
        datos[8][1] = String.valueOf(zonaPasterizacion.getAreaDeTCDeCadaPlaca());
        datos[9][1] = String.valueOf(zonaPasterizacion.getAreaDeFlujo());
        datos[10][1] = String.valueOf(zonaPasterizacion.getDensidadFlujoMasicaGlobalFluidoFrio());
        datos[11][1] = String.valueOf(zonaPasterizacion.getDensidadFlujoMasicaGlobalFluidoCaliente());
        datos[12][1] = String.valueOf(zonaPasterizacion.getNumeroDeReynoldsFluidoFrio());
        datos[13][1] = String.valueOf(zonaPasterizacion.getNumeroDePrantFluidoFrio());
        datos[14][1] = String.valueOf(zonaPasterizacion.getNumeroDeReynoldsFluidoCaliente());
        datos[15][1] = String.valueOf(zonaPasterizacion.getNumeroDePrantFluidoCaliente());
        datos[16][1] = String.valueOf(zonaPasterizacion.getCoeficienteTCPorConveccionFluidoFrio());
        datos[17][1] = String.valueOf(zonaPasterizacion.getCoeficienteTCPorConveccionFluidoCaliente());
        datos[18][1] = String.valueOf(zonaPasterizacion.getCoeficienteDeDiseñoCalculado());
        datos[19][1] = String.valueOf(zonaPasterizacion.getAreaDeDiseñoRequerida());
        datos[20][1] = String.valueOf(zonaPasterizacion.getNumeroDePlacasTotalesRequeridas());
        return (datos);
    }

    private String[][] obtenerDatosDelPasteurizador(){
        String[][] datos = new String[11][2];
        datos[0][0] = "Material";
        datos[1][0] = "Conductividad termica (W/m°k)";
        datos[2][0] = "Ancho placa (m)";
        datos[3][0] = "Largo placa (m)";
        datos[4][0] = "Distancia placas (m)";
        datos[5][0] = "Diametro equivalente (m)";
        datos[6][0] = "Calibre (m)";
        datos[7][0] = "Espesor placas (m)";
        datos[8][0] = "Area de circulacion (m^2)";
        datos[9][0] = "Coef. de obstruccion)";
        datos[10][0] = "Numero de placas";
        datos[0][1] = pasteurizador.getMaterial();
        datos[1][1] = String.valueOf(pasteurizador.getConductividadTermica());
        datos[2][1] = String.valueOf(pasteurizador.getAnchoPlaca());
        datos[3][1] = String.valueOf(pasteurizador.getLargoPlaca());
        datos[4][1] = String.valueOf(pasteurizador.getDistanciaPlacas());
        datos[5][1] = String.valueOf(pasteurizador.getDiametroEquivalente());
        datos[6][1] = String.valueOf(pasteurizador.getCalibre());
        datos[7][1] = String.valueOf(pasteurizador.getEspesorPlacas());
        datos[8][1] = String.valueOf(pasteurizador.getAreaCirculacion());
        datos[9][1] = String.valueOf(pasteurizador.getCoeficienteObstruccion());
        datos[10][1] = String.valueOf(pasteurizador.getNumeroDePlacas());
        return (datos);
    }

    public String[][] obtenerDatosDeEntrada(){
        String[][] datos = new String[6][2];
        datos[0][0] = "Temperatura de entrada del alimento (°C)";
        datos[1][0] = "Caudal de entrada del alimento (L/s)";
        datos[2][0] = "Coeficiente de incrustacion del alimento";
        datos[3][0] = "Temperatura de entrada del fluido de servicio (°C)";
        datos[4][0] = "Coeficiente de incrustacion del fluido de servicio (°C)";
        datos[5][0] = "Coeficiente de diseño asumido (W/ m^2.°C)";
        datos[0][1] = String.valueOf(fluidoFrio.getTempEntrada());
        datos[1][1] = String.valueOf(zonaPasterizacion.getCaudalDeEntradaAlimento());
        datos[2][1] = String.valueOf(fluidoFrio.getCoeficienteDeIncrustacion());
        datos[3][1] = String.valueOf(fluidoCaliente.getTempEntrada());
        datos[4][1] = String.valueOf(fluidoCaliente.getCoeficienteDeIncrustacion());
        datos[5][1] = String.valueOf(zonaPasterizacion.getCoeficienteDeDiseñoAsumido());
        return (datos);
    }

    @Override
    public void configurarPractica(Object[] datos) {
        fluidoFrio.setTempSalida(TEMPERATURA_SALIDA_ALIMENTO);
        fluidoCaliente.setFlujoMasico(FLUJO_MASICO_FLUIDO_DE_SERVICIO);
        fluidoFrio.setTempEntrada(Float.parseFloat(datos[0].toString()));
        zonaPasterizacion.setCaudalDeEntradaAlimento(Float.parseFloat(datos[1].toString()));
        fluidoFrio.setCoeficienteDeIncrustacion(Float.parseFloat(datos[2].toString()));
        fluidoCaliente.setTempEntrada(Float.parseFloat(datos[3].toString()));
        fluidoCaliente.setCoeficienteDeIncrustacion(Float.parseFloat(datos[4].toString()));
        zonaPasterizacion.setCoeficienteDeDiseñoAsumido(Float.parseFloat(datos[5].toString()));
    }

    @Override
    public ArrayList<Object> calcularDatosGrafica() {
        ArrayList<Float> x = new ArrayList<>();
        return null;
    }

    @Override
    public void generarInforme(String nombreArchivo){
        Informe informePractica = new Informe(nombreArchivo, this, Estudiante.getEstudiante(), contexto);
        informePractica.obtenerNuevoDocumento();
        informePractica.configurarPresentacionDocumento();
        informePractica.insertarDatos("ESPECIFICACIONES TECNICAS DEL PASTEURIZADOR DE PLACAS", obtenerDatosDelPasteurizador());
        informePractica.insertarDatos("DATOS DE ENTRADA", datosDeEntrada);
        informePractica.insertarDatos("RESULTADOS", resultados);
        // InsertarGrafica
        informePractica.cerrarDocumento();
    };
}
