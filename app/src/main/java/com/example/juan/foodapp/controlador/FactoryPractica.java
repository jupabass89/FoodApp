package com.example.juan.foodapp.controlador;

import com.example.juan.foodapp.modelo.Practica;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.practicaTanque.PracticaTanque;

public class FactoryPractica {

    public static Practica crearPractica(int seleccion){
        //seleccion = 1-tanque
        //seleccion = 2 -placas
        if(seleccion ==1){
            return (new PracticaTanque());
        }
        else if(seleccion == 2){
            return (new PracticaPlacas());
        }
        return null;
    }
}
