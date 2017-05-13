package com.example.juan.foodapp.modelo.practicaTanque;

import android.util.Log;

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


    private double diametroInternoChaqueta;      //parametro
    private double tiempoEstCalentamiento;
    private double tiempoEstEnfriamiento;
    private double coeficienteGlobalDeTrasnferencia;
    private double ht;                          //(COEF TRANS ALIM) 


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
        double densidad, cp, conductividad;
        double tempI;
        double ut,ht, hch,nusF,re,Prf,nusCh,mldt,tiempoEstimado,k2;

        ArrayList<Float> coeficienteGlogalTransferenciaCalorCalentamiento = new ArrayList<>();
        ArrayList<Float> coeficienteGlogalTransferenciaCalorEnfriamiento = new ArrayList<>();
        ArrayList<Float> tiempoEstCalentamiento = new ArrayList<>();
        ArrayList<Float> tiempoEstEnfriamiento = new ArrayList<>();
        double []agitador = this.agitador.getAgitador();
        if(!esAgua){

            /*
            * falta tiempo estimado calentamiento para enfriamiento cuando no es agua.
            * */
            for (int i = 0; i < datosExperimentalesCalentamiento; i++) {
                tempI = calentamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAlimento(tempI);

                cp = operacionAlimento.calcularCpAlimento(tempI);
                conductividad = operacionAlimento.calcularConductividadAlimento(tempI);

                mldt = operaciones.calcularMLDTCalentamiento(alimento.getTempInicial(),tempI,tanque.getTempChaqueta());
                nusCh = operaciones.nusChCalentamiento(mldt);
                hch = operaciones.hch(operacionAlimento.calcularConductividadAgua(tempI),nusCh,
                        tanque.getDiametroInterno(),tanque.getDiametroExterno());
                Prf = operaciones.Prf(alimento.getViscosidad(),cp,conductividad);
                re = operaciones.Re(densidad,alimento.getViscosidad(),this.agitador.getDiametro(),this.agitador.getVelocidadGiroRPS());
                nusF = operaciones.nusF(Prf,re,agitador[0],agitador[1],agitador[2]);
                ht = operaciones.ht(nusF,conductividad,tanque.getDiametroInterno());
                ut = operaciones.ut(tanque.getEspesor(),tanque.getConductividadMaterial(), tanque.getFactorPorIncrustaciones(),
                        ht,hch);
                coeficienteGlogalTransferenciaCalorCalentamiento.add((float)ut);

                tiempoEstimado = operaciones.calcularTiempoEstimadCalentamiento(alimento.getVolumen(),densidad,alimento.getTempInicial(),tanque.getTempChaqueta(),tempI,
                        tanque.getArea(),cp,ut);
                tiempoEstCalentamiento.add((float)tiempoEstimado);

            }
            for (int i = 0; i < datosExperimentalesEnfriamiento; i++) {
                tempI = enfriamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAlimento(tempI);
                cp = operacionAlimento.calcularCpAlimento(tempI);
                conductividad = operacionAlimento.calcularConductividadAlimento(tempI);

                mldt = operaciones.calcularMLDTEnfriamiento(enfriamientoTemps.get(0),tempI, servicioEnfriamiento.getTempSalida(),
                        servicioEnfriamiento.getTempEntrada());
                double promedioServicio = servicioEnfriamiento.getTemperaturaPromedio();
                nusCh = operaciones.nusChEnfriamiento(mldt,operacionAlimento.calcularViscosidadAgua(promedioServicio),
                        operacionAlimento.calcularDensidadAgua(promedioServicio),operacionAlimento.calcularExpancionTermicaAgua(promedioServicio),
                        operacionAlimento.calcularCpAgua(promedioServicio),operacionAlimento.calcularConductividadAgua(promedioServicio));

                hch = operaciones.hch(conductividad,nusCh,
                        tanque.getDiametroInterno(),tanque.getDiametroExterno());
                Prf = operaciones.Prf(alimento.getViscosidad(),cp,conductividad);
                re = operaciones.Re(densidad,alimento.getViscosidad(),this.agitador.getDiametro(),this.agitador.getVelocidadGiroRPS());
                nusF = operaciones.nusF(Prf,re,agitador[0],agitador[1],agitador[2]);
                ht = operaciones.ht(nusF,conductividad,tanque.getDiametroInterno());
                ut = operaciones.ut(tanque.getEspesor(),tanque.getConductividadMaterial(), tanque.getFactorPorIncrustaciones(),
                        ht,hch);
                coeficienteGlogalTransferenciaCalorEnfriamiento.add((float)ut);

            }

        }else{
            for (int i = 0; i < calentamientoTemps.size(); i++) {
                tempI = calentamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAgua(tempI);
                cp = operacionAlimento.calcularCpAgua(tempI);
                conductividad = operacionAlimento.calcularConductividadAgua(tempI);

                mldt = operaciones.calcularMLDTCalentamiento(alimento.getTempInicial(),tempI,tanque.getTempChaqueta());
                nusCh = operaciones.nusChCalentamiento(mldt);
                hch = operaciones.hch(operacionAlimento.calcularConductividadAgua(tempI),nusCh,
                        tanque.getDiametroInterno(),tanque.getDiametroExterno());
                Prf = operaciones.Prf(operacionAlimento.calcularViscosidadAgua(tempI),cp,conductividad);
                re = operaciones.Re(densidad,operacionAlimento.calcularViscosidadAgua(tempI),this.agitador.getDiametro(),this.agitador.getVelocidadGiroRPS());
                nusF = operaciones.nusF(Prf,re,agitador[0],agitador[1],agitador[2]);
                ht = operaciones.ht(nusF,conductividad,tanque.getDiametroInterno());
                ut = operaciones.ut(tanque.getEspesor(),tanque.getConductividadMaterial(), tanque.getFactorPorIncrustaciones(),
                        ht,hch);
                coeficienteGlogalTransferenciaCalorCalentamiento.add((float)ut);

                tiempoEstimado = operaciones.calcularTiempoEstimadCalentamiento(alimento.getVolumen(),densidad,alimento.getTempInicial(),tanque.getTempChaqueta(),tempI,
                        tanque.getArea(),cp,ut);
                tiempoEstCalentamiento.add((float)tiempoEstimado);
            }
            for (int i = 0; i < datosExperimentalesEnfriamiento; i++) {
                tempI = enfriamientoTemps.get(i);
                densidad = operacionAlimento.calcularDensidadAgua(tempI);
                cp = operacionAlimento.calcularCpAgua(tempI);
                conductividad = operacionAlimento.calcularConductividadAgua(tempI);


                mldt = operaciones.calcularMLDTEnfriamiento(enfriamientoTemps.get(0),tempI, servicioEnfriamiento.getTempSalida(),
                        servicioEnfriamiento.getTempEntrada());
                double promedioServicio = servicioEnfriamiento.getTemperaturaPromedio();

                double expansionTermica = operacionAlimento.calcularExpancionTermicaAgua(promedioServicio);
                double cpagua = operacionAlimento.calcularCpAgua(promedioServicio);
                double conductividadAgua = operacionAlimento.calcularConductividadAgua(promedioServicio);
                double viscosidadAgua = operacionAlimento.calcularViscosidadAgua(promedioServicio);
                double densidadAgua = operacionAlimento.calcularDensidadAgua(promedioServicio);

                nusCh = operaciones.nusChEnfriamiento(mldt,viscosidadAgua,densidadAgua,expansionTermica,cpagua,conductividadAgua);
                hch = operaciones.hch(conductividad,nusCh,
                        tanque.getDiametroInterno(),tanque.getDiametroExterno());
                Prf = operaciones.Prf(operacionAlimento.calcularViscosidadAgua(tempI),cp,conductividad);
                re = operaciones.Re(densidad,operacionAlimento.calcularViscosidadAgua(tempI),this.agitador.getDiametro(),this.agitador.getVelocidadGiroRPS());
                nusF = operaciones.nusF(Prf,re,agitador[0],agitador[1],agitador[2]);
                ht = operaciones.ht(nusF,conductividad,tanque.getDiametroInterno());
                ut = operaciones.ut(tanque.getEspesor(),tanque.getConductividadMaterial(), tanque.getFactorPorIncrustaciones(),
                        ht,hch);
                coeficienteGlogalTransferenciaCalorEnfriamiento.add((float)ut);
                k2 = operaciones.calculark2(operacionAlimento.calcularCpAgua(promedioServicio),servicioEnfriamiento.getFlujoMasico(),
                        tanque.getArea(),ut);
                tiempoEstimado = operaciones.calcularTiempoEstimadEnfriamiento(alimento.getVolumen(),densidad,tempI,cp,operacionAlimento.calcularCpAgua(promedioServicio),
                        servicioEnfriamiento.getTempEntrada(), enfriamientoTemps.get(0),servicioEnfriamiento.getFlujoMasico(),k2);
                tiempoEstEnfriamiento.add((float)tiempoEstimado);
            }
        }
        ArrayList<Object> datos = new ArrayList<>();
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
        alimento.setTempInicial((float)datos[8]);
        if(!esAgua){
            float[] porcentajes = (float[])datos[3];
            operacionAlimento = new OperacionAlimento(porcentajes[0],porcentajes[1],porcentajes[2],porcentajes[3],porcentajes[4]);
            if(datos[7]!=null){
                alimento.setViscosidad((float)datos[7]);
            }
        }
        else{
            operacionAlimento = new OperacionAlimento();
        }


    }

    @Override
    public void generarInforme(String nombreArchivo) {

    }

}