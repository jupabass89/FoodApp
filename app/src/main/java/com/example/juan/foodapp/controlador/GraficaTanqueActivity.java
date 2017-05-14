package com.example.juan.foodapp.controlador;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.practicaTanque.PracticaTanque;
import com.example.juan.foodapp.modelo.representacion.GraficaXY;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraficaTanqueActivity extends AppCompatActivity {

    ArrayList<Object> r;
    PracticaTanque p;
    Object[] o;
    LineChart line;
    ArrayList<Float> tempExperiCalenta;
    ArrayList<Float> tempExperiEnfria;
    EditText texto;
    Button btonAceptar;
    private boolean graficaTiempos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_tanque);
        graficaTiempos = false;

        Bundle d = getIntent().getExtras();
        ArrayList<Object> z = (ArrayList<Object>)d.getSerializable("datos");
        o = (Object[]) z.get(0);
        tempExperiEnfria = (ArrayList<Float>)o[2];
        tempExperiCalenta = (ArrayList<Float>)o[1];
        p = new PracticaTanque(this, null, null);
        hacerCalculos();
        line = (LineChart)findViewById(R.id.graficaTanque);
        GraficaXY.obtenerGraficaCoeficientes(r,line);
        btonAceptar = (Button)findViewById(R.id.btonAceptar);
        texto = (EditText)findViewById(R.id.temperatura);
        btonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = texto.getText().toString();
                if(text.compareTo("")!=0){
                    o[8] = Float.parseFloat(text);
                    hacerCalculos();
                    graficar();
                }
            }
        });
    }

    private void hacerCalculos(){
        p.configurarPractica(o);
        r = p.calcularDatosGrafica();
    }

    private void graficar(){
        if(graficaTiempos){
            GraficaXY.graficarTiempos(r,tempExperiCalenta,tempExperiEnfria,line);
        }
        else{
            GraficaXY.obtenerGraficaCoeficientes(r,line);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.coeficientes){
            GraficaXY.obtenerGraficaCoeficientes(r,line);
            graficaTiempos = false;
        }
        else if(id == R.id.tiempo){
            GraficaXY.graficarTiempos(r,tempExperiCalenta,tempExperiEnfria,line);
            graficaTiempos = true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grafica,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
