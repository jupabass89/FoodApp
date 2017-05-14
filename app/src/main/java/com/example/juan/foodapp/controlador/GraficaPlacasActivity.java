package com.example.juan.foodapp.controlador;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraficaPlacasActivity extends AppCompatActivity {

    private LineChart line;
    private ArrayList<Object> data;
    private ArrayList<Float> flujoMasicoAlimento, numeroPlacasTotales, areaDiseño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_placas);
        Bundle bundle = getIntent().getExtras();
        data = (ArrayList<Object>) bundle.getSerializable("datos");
        flujoMasicoAlimento = (ArrayList<Float>) data.get(0);
        numeroPlacasTotales = (ArrayList<Float>) data.get(1);
        areaDiseño = (ArrayList<Float>) data.get(2);
        graficar();
    }

    private void graficar(){
        line = (LineChart)findViewById(R.id.chartPlacas);
        ArrayList<Entry> datos1 = new ArrayList<>();
        for (int i = 0; i < flujoMasicoAlimento.size(); i++) {
            datos1.add(new Entry(flujoMasicoAlimento.get(i),numeroPlacasTotales.get(i)));
        }
        LineDataSet dataset1 = new LineDataSet(datos1, "Flujo Masico Alimento vs Numero de Placas Totales Requeridas");
        dataset1.setColor(Color.BLACK);

        ArrayList<Entry> datos2 = new ArrayList<>();
        for (int i = 0; i < flujoMasicoAlimento.size(); i++) {
            datos2.add(new Entry(flujoMasicoAlimento.get(i),areaDiseño.get(i)));
        }

        LineDataSet dataset2 = new LineDataSet(datos2, "Flujo Masico Alimento vs Area Diseño ");
        dataset2.setColor(Color.BLUE);

        LineData data = new LineData(dataset1, dataset2);
        line.setData(data);
    }
}
