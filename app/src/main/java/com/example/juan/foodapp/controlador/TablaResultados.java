package com.example.juan.foodapp.controlador;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.juan.foodapp.R;

import java.util.ArrayList;

public class TablaResultados extends AppCompatActivity {

    private String[][] datos;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_resultados);
        Bundle bundle = getIntent().getExtras();
        lista = bundle.getStringArrayList("datos");
        datos = this.arrayToTable(lista);
        cargarDatos(datos);
    }

    private void cargarDatos(String[][] datos){
        TableLayout tabla = (TableLayout)findViewById(R.id.tabla);
        TextView texto;
        TableRow fila;
        for(int i=0; i<datos.length; i++){
            fila = new TableRow(getBaseContext());
            for(int j=0; j < datos[i].length; j++){
                texto = new TextView(getBaseContext());
                texto.setGravity(Gravity.CENTER_VERTICAL);
                texto.setPadding(15, 15, 15, 15);
                texto.setText(datos[i][j]);
                texto.setTextColor(Color.BLACK);
                fila.addView(texto);
            }
            tabla.addView(fila);
        }
    }

    private String[][] arrayToTable(ArrayList<String> lista){
        ArrayList<ArrayList<String>> tabla = new ArrayList<>();
        ArrayList<String> fila = new ArrayList<>();
        int mayorNumeroColumnas = 0;
        int contadorColumnas = 0;
        for(String dato : lista){
            if(dato.compareTo("/") == 0){
                contadorColumnas = 0;
                tabla.add(fila);
                fila = new ArrayList<>();
                continue;
            }
            contadorColumnas++;
            fila.add(dato);
            if(contadorColumnas > mayorNumeroColumnas)
                mayorNumeroColumnas = contadorColumnas;
        }
        String[][] matriz = new String[tabla.size()][mayorNumeroColumnas];
        for(int i=0; i<tabla.size(); i++){
            fila = tabla.get(i);
            for(int j=0; j<fila.size(); j++){
                matriz[i][j] = fila.get(j);
            }
        }return (matriz);
    }

    public static ArrayList<String> tableToArray(String[][] matriz){
        ArrayList<String> lista = new ArrayList<>();
        for(int i=0 ; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                lista.add(matriz[i][j]);
            }lista.add("/");
        }return (lista);
    }
}
