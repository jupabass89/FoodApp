package com.example.juan.foodapp.controlador;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.representacion.GraficaXY;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraficaPlacasActivity extends AppCompatActivity {

    private LineChart line;
    private ArrayList<Object> data;
    private ArrayList<Float> tempEntradaAlimento, coeficienteTCAlimento, areaDiseño;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_placas);
        Bundle bundle = getIntent().getExtras();
        data = (ArrayList<Object>) bundle.getSerializable("datos");
        tempEntradaAlimento = (ArrayList<Float>) data.get(0);
        coeficienteTCAlimento = (ArrayList<Float>) data.get(1);
        areaDiseño = (ArrayList<Float>) data.get(2);
        line = (LineChart)findViewById(R.id.chartPlacas);
        GraficaXY.graficaPlacas(line, tempEntradaAlimento, coeficienteTCAlimento, areaDiseño);
    }
}
