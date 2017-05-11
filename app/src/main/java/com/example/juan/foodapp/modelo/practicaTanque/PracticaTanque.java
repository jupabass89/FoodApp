package com.example.juan.foodapp.modelo.practicaTanque;

import com.example.juan.foodapp.modelo.Alimento;
import com.example.juan.foodapp.modelo.FluidoServicio;
import com.example.juan.foodapp.modelo.Practica;

import java.util.ArrayList;
import java.util.Objects;

public class PracticaTanque extends Practica {

    private Alimento alimento;
    private FluidoServicio servicioEnfriamiento;
    private Tanque tanque;
    private OperacionTanqueAgitado operaciones;
    private Agitador agitador;

    private OperacionAlimento operacionAlimento;
    private ArrayList<Float> calentamientoTemps;
    private ArrayList<Float> enfriamientoTemps;
    private boolean esAgua;


    private float diametroInternoChaqueta;      //parametro
    private float tiempoEstCalentamiento;
    private float tiempoEstEnfriamiento;
    private float coeficienteGlobalDeTrasnferencia;
    private float ht;                          //(COEF TRANS ALIM) 


    public PracticaTanque(){
        alimento = new Alimento();
        servicioEnfriamiento =new FluidoServicio();
        tanque = new Tanque();
        operaciones = new OperacionTanqueAgitado();
        agitador = new Agitador(5,5);
    }

    @Override
    public ArrayList<Object> calcularDatosGrafica() {
        int datosExperimentalesCalentamiento = calentamientoTemps.size();
        int datosExperimentalesEnfriamiento = enfriamientoTemps.size();
        float densidad,ut,mldt;
        float cp;
        float conductividad;
        float tempI;
        float ht;//coeficienteGlobaltrasnferenciadeCalor
        float hch; // diametroInternoChaqueta
        float temperaturaCalentamiento, temperaturaEnfriamiento;
        ArrayList<Float> coeficientesTrasnferenciaCalentamiento = new ArrayList<>(datosExperimentalesCalentamiento);
        ArrayList<Float> coeficientesTrasnferenciaEnfriamiento = new ArrayList<>(datosExperimentalesEnfriamiento);
        ArrayList<Float> coeficienteGlogalTransferenciaCalorCalentamiento = new ArrayList<>(datosExperimentalesCalentamiento);
        ArrayList<Float> coeficienteGlogalTransferenciaCalorEnfriamiento = new ArrayList<>(datosExperimentalesEnfriamiento);
        ArrayList<Float> tiempoEstCalentamiento = new ArrayList<>();
        ArrayList<Float> tiempoEstEnfriamiento = new ArrayList<>();
        float []agitador = this.agitador.getAgitador();
        if(!esAgua){
            for (int i = 0; i < datosExperimentalesCalentamiento; i++) {
                tempI = calentamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAlimento(tempI);
                cp = operacionAlimento.calcularCpAlimento(tempI);
                conductividad = operacionAlimento.calcularConductividadAlimento(tempI);
                ht = operaciones.calcularCoeficienteIndividualTransferenciaCalorInteriorTanque(
                        tanque.getDiametroInterno(),conductividad,agitador[0],agitador[1],agitador[3],
                        this.agitador.getDiametro(), densidad, this.agitador.getVelocidadGiroRPS(),alimento.getViscosidad(),
                        alimento.getViscosidad(),cp);
                coeficientesTrasnferenciaCalentamiento.set(i,ht);

                mldt = operaciones.calcularMLDTCalentamiento(alimento.getTempInicial(),tempI,tanque.getTempChaqueta());

                hch = operaciones.calcularCoeficienteIndividualTransferenciaCalorChaqueta(tanque.getDiametroChaqueta(),tanque.getDiametroExterno(),
                        tanque.getDiametroExterno(), mldt,operacionAlimento.calcularCpAgua(tanque.getTempChaqueta()),alimento.getViscosidad(),
                        operacionAlimento.calcularConductividadAgua(tanque.getTempChaqueta()),
                        operacionAlimento.calcularDensidadAgua(tanque.getTempChaqueta()),9.8f,
                        operacionAlimento.calcularExpancionTermicaAgua(tanque.getTempChaqueta()));

                ut = operaciones.calcularCoeficienteGlobalTrasnferenciaDeCalor(tanque.getConductividadMaterial(),ht,tanque.getDiametroInterno(),
                        tanque.getDiametroExterno(),tanque.getFactorPorIncrustaciones(),hch);
                coeficienteGlogalTransferenciaCalorCalentamiento.set(i,ut);

                //masaAlimento = (densidad*((float)Math.pow(tanque.getAlturaProducto(),3)*(float)Math.PI*(2/3)));
                temperaturaCalentamiento = operaciones.calcularTimepoEstimadoCalentamiento(alimento.getVolumen(),densidad,cp,tanque.getDiametroInterno(),tanque.getTempChaqueta(),
                        alimento.getTempEntrada(),tempI,ut);

                tiempoEstCalentamiento.add(temperaturaCalentamiento);
            }
            for (int i = 0; i < datosExperimentalesEnfriamiento; i++) {
                tempI = enfriamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAlimento(tempI);
                cp = operacionAlimento.calcularCpAlimento(tempI);
                conductividad = operacionAlimento.calcularConductividadAlimento(tempI);
                ht = operaciones.calcularCoeficienteIndividualTransferenciaCalorInteriorTanque(
                        tanque.getDiametroInterno(),conductividad,agitador[0],agitador[1],agitador[3],
                        this.agitador.getDiametro(), densidad, this.agitador.getVelocidadGiroRPS(),alimento.getViscosidad(),
                        alimento.getViscosidad(),cp);
                coeficientesTrasnferenciaEnfriamiento.set(i,ht);

                mldt = operaciones.calcularMLDTEnfriamiento(enfriamientoTemps.get(0),tempI,servicioEnfriamiento.getTempSalida(),servicioEnfriamiento.getTempEntrada());

                hch = operaciones.calcularCoeficienteIndividualTransferenciaCalorChaqueta(tanque.getDiametroChaqueta(),tanque.getDiametroExterno(),
                        tanque.getDiametroExterno(),mldt,operacionAlimento.calcularCpAgua(tanque.getTempChaqueta()),alimento.getViscosidad(),
                        operacionAlimento.calcularConductividadAgua(tanque.getTempChaqueta()),
                        operacionAlimento.calcularDensidadAgua(tanque.getTempChaqueta()),9.8f,
                        operacionAlimento.calcularExpancionTermicaAgua(tanque.getTempChaqueta()));

                ut = operaciones.calcularCoeficienteGlobalTrasnferenciaDeCalor(tanque.getConductividadMaterial(),ht,tanque.getDiametroInterno(),
                        tanque.getDiametroExterno(),tanque.getFactorPorIncrustaciones(),hch);
                coeficienteGlogalTransferenciaCalorEnfriamiento.set(i,ut);

                temperaturaEnfriamiento = operaciones.calcularTiempoEstimadoEnfriamiento(alimento.getVolumen(),densidad,cp,
                        operacionAlimento.calcularCpAgua(servicioEnfriamiento.getTemperaturaPromedio()),servicioEnfriamiento.getFlujoMasico(),
                        tanque.getArea(),servicioEnfriamiento.getTempEntrada(),enfriamientoTemps.get(0),tempI,ut);
                tiempoEstEnfriamiento.add(temperaturaEnfriamiento);
            }

        }else{
            for (int i = 0; i < calentamientoTemps.size(); i++) {
                tempI = calentamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAgua(tempI);
                cp = operacionAlimento.calcularCpAlimento(tempI);
                conductividad = operacionAlimento.calcularConductividadAgua(tempI);
                ht = operaciones.calcularCoeficienteIndividualTransferenciaCalorInteriorTanque(
                        tanque.getDiametroInterno(),conductividad,agitador[0],agitador[1],agitador[3],
                        this.agitador.getDiametro(), densidad, this.agitador.getVelocidadGiroRPS(),alimento.getViscosidad(),
                        alimento.getViscosidad(),cp);
                coeficientesTrasnferenciaCalentamiento.set(i,ht);
                mldt = operaciones.calcularMLDTCalentamiento(alimento.getTempInicial(),tempI,tanque.getTempChaqueta());
                hch = operaciones.calcularCoeficienteIndividualTransferenciaCalorChaqueta(tanque.getDiametroChaqueta(),tanque.getDiametroExterno(),
                        tanque.getDiametroExterno(),mldt,operacionAlimento.calcularCpAgua(tanque.getTempChaqueta()),alimento.getViscosidad(),
                        operacionAlimento.calcularConductividadAgua(tanque.getTempChaqueta()),
                        operacionAlimento.calcularDensidadAgua(tanque.getTempChaqueta()),9.8f,
                        operacionAlimento.calcularExpancionTermicaAgua(tanque.getTempChaqueta()));

                ut = operaciones.calcularCoeficienteGlobalTrasnferenciaDeCalor(tanque.getConductividadMaterial(),ht,tanque.getDiametroInterno(),
                        tanque.getDiametroExterno(),tanque.getFactorPorIncrustaciones(),hch);
                coeficienteGlogalTransferenciaCalorCalentamiento.set(i,ut);

                temperaturaCalentamiento = operaciones.calcularTimepoEstimadoCalentamiento(alimento.getVolumen(),densidad,cp,tanque.getDiametroInterno(),tanque.getTempChaqueta(),
                        alimento.getTempEntrada(),tempI,ut);

                tiempoEstCalentamiento.add(temperaturaCalentamiento);


            }
            for (int i = 0; i < datosExperimentalesEnfriamiento; i++) {
                tempI = enfriamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAgua(tempI);
                cp = operacionAlimento.calcularCpAgua(tempI);
                conductividad = operacionAlimento.calcularConductividadAgua(tempI);
                ht = operaciones.calcularCoeficienteIndividualTransferenciaCalorInteriorTanque(
                        tanque.getDiametroInterno(),conductividad,agitador[0],agitador[1],agitador[3],
                        this.agitador.getDiametro(), densidad, this.agitador.getVelocidadGiroRPS(),alimento.getViscosidad(),
                        alimento.getViscosidad(),cp);
                coeficientesTrasnferenciaEnfriamiento.set(i,ht);
                mldt = operaciones.calcularMLDTEnfriamiento(enfriamientoTemps.get(0),tempI,servicioEnfriamiento.getTempSalida(),servicioEnfriamiento.getTempEntrada());
                hch = operaciones.calcularCoeficienteIndividualTransferenciaCalorChaqueta(tanque.getDiametroChaqueta(),tanque.getDiametroExterno(),
                        tanque.getDiametroExterno(),mldt,operacionAlimento.calcularCpAgua(tanque.getTempChaqueta()),alimento.getViscosidad(),
                        operacionAlimento.calcularConductividadAgua(tanque.getTempChaqueta()),
                        operacionAlimento.calcularDensidadAgua(tanque.getTempChaqueta()),9.8f,
                        operacionAlimento.calcularExpancionTermicaAgua(tanque.getTempChaqueta()));

                ut = operaciones.calcularCoeficienteGlobalTrasnferenciaDeCalor(tanque.getConductividadMaterial(),ht,tanque.getDiametroInterno(),
                        tanque.getDiametroExterno(),tanque.getFactorPorIncrustaciones(),hch);
                coeficienteGlogalTransferenciaCalorEnfriamiento.set(i,ut);
                temperaturaEnfriamiento = operaciones.calcularTiempoEstimadoEnfriamiento(alimento.getVolumen(),densidad,cp,
                        operacionAlimento.calcularCpAgua(servicioEnfriamiento.getTemperaturaPromedio()),servicioEnfriamiento.getFlujoMasico(),
                        tanque.getArea(),servicioEnfriamiento.getTempEntrada(),enfriamientoTemps.get(0),tempI,ut);
                tiempoEstEnfriamiento.add(temperaturaEnfriamiento);
            }
        }
        ArrayList<Object> datos = new ArrayList<>();
        datos.add(coeficientesTrasnferenciaCalentamiento);
        datos.add(coeficientesTrasnferenciaEnfriamiento);
        datos.add(coeficienteGlogalTransferenciaCalorCalentamiento);
        datos.add(coeficienteGlogalTransferenciaCalorEnfriamiento);
        datos.add(tiempoEstCalentamiento);
        datos.add(tiempoEstEnfriamiento);

        return datos;
    }

    @Override
    public void configurarPractica(Object[] datos) {
        /*
        *el usuario ingresa:
        * datos[0] contiene si el alimento es agua o no (boolean)
        * datos[1]:
         *  temperaturas de calentamiento: es un arraylist: 40 datos maximo
        * datos[2]:
         *  temperaturas de enfriamiento: es un arraylist: 40 datos maximo
        * datos[3]: vector de float 6 posiciones con los siguientes porcentajes, y en el mismo orden
         *   grasa, proteina, fibra, ceniza, carbohidratos,
        *datos[4]:
         *  selecciona un tipo de agitador(int)
        *datos[5]: float[3]:altura, diametro,rps. esto del agitador
        *datos[6]: informacion del tanque: float[] con los siguientes parametros:
         *  altura producto, espesor, diametro interno, diametro externo, diametro de la chaqueta,tempChaqueta, conductividad, factor por incustracion
        *datos[7]viscosidad(no es porcentaje)
        * datos[8] alimento
         *temperaturaEntradaAlimento,
        *datos[9] alimento
         *volumen alimento
        *datos[10]:fluidos de servicio temperatura entrada
        * datos[11] temperatura salida, fluido servicio
        * datos[12] flujo masico fluido de servicio.
         */

        servicioEnfriamiento.setTempEntrada((float)datos[10]);
        servicioEnfriamiento.setTempSalida((float)datos[11]);
        servicioEnfriamiento.setFlujoMasico((float)datos[12]);

        esAgua = (Boolean)datos[0];
        calentamientoTemps = (ArrayList<Float>) datos[1];
        enfriamientoTemps = (ArrayList<Float>)datos[2];

        agitador.setAgitador((int)datos[4]);
        float[] parametrosAgitador = (float[])datos[5];
        agitador.setAltura(parametrosAgitador[0]);
        agitador.setDiametro(parametrosAgitador[1]);
        agitador.setVelocidadGiroRPS(parametrosAgitador[2]);

        float[] parametrosTanque = (float[])datos[6];
        tanque.setAlturaProducto(parametrosTanque[0]);
        tanque.setEspesor(parametrosTanque[1]);
        tanque.setDiametroInterno(parametrosTanque[2]);
        tanque.setDiametroExterno(parametrosTanque[3]);
        tanque.setDiametroChaqueta(parametrosTanque[4]);
        tanque.setTempChaqueta(parametrosTanque[5]);
        tanque.setConductividadMaterial(parametrosTanque[6]);
        tanque.setFactorPorIncrustaciones(parametrosTanque[7]);

        alimento.setVolumen((float)datos[9]);

        if(!esAgua){
            float[] porcentajes = (float[])datos[3];
            operacionAlimento = new OperacionAlimento(porcentajes[0],porcentajes[1],porcentajes[2],porcentajes[3],porcentajes[4]);
            if(datos[7]!=null){
                alimento.setViscosidad((float)datos[7]);
            }
        }


    }

    @Override
    public void generarInforme() {}

    @Override
    public void visualizarGuia(){}
}