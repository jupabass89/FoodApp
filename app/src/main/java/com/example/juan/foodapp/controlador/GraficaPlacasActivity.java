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

    private Object[] data;
    private Button boton;
    private LineChart line;
    private ArrayList<Float> x,y;
    private EditText nuevo;
    PracticaPlacas p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_placas);
        Bundle datos = getIntent().getExtras();
        data = (Object[]) datos.getSerializable("data");
        nuevo = (EditText)findViewById(R.id.nuevoCaudal);
        boton = (Button)findViewById(R.id.btnAgregar);
        x = new ArrayList<>();
        y = new ArrayList<>();
        p = new PracticaPlacas(this, null, null);
        p.configurarPractica(data);
        p.calcularDatosZonaPasterizacion();
        x.add(Float.parseFloat(data[1].toString()));
        y.add(p.getAreaTC());
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dato = nuevo.getText().toString();
                if(dato.compareTo("")!=0){
                    data[1] = dato;
                    p.configurarPractica(data);
                    p.calcularDatosZonaPasterizacion();
                    x.add(Float.parseFloat(data[1].toString()));
                    y.add(p.getAreaTC());

                    graficar();
                    line.notifyDataSetChanged();
                    line.invalidate();

                }
            }
        });
        graficar();
    }

    private void graficar(){
        line = (LineChart)findViewById(R.id.chartPlacas);
        ArrayList<Entry> datos = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            datos.add(new Entry(x.get(i),y.get(i)));
        }
        LineDataSet dataset = new LineDataSet(datos,null);
        dataset.setColor(Color.RED);

        LineData da = new LineData(dataset);
        line.setData(da);
    }
}
