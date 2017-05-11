package com.example.juan.foodapp.modelo.serviciosPractica;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.juan.foodapp.controlador.VisualizacionGuia;

/**
 * Clase que permite visualizar en la aplicacion la guia de una practica.
 */
public class Guia {

    public static void abrirGuia(String nommbreArchivo, Context contexto){
        Intent intencion = new Intent(contexto, VisualizacionGuia.class);
        intencion.putExtra("nombrePractica", nommbreArchivo);
        contexto.startActivity(intencion);
    }
}
