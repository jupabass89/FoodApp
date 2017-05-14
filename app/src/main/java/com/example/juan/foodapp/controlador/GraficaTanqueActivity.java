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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_tanque);

        int i = 0;
        Bundle d = getIntent().getExtras();
        ArrayList<Object> z = (ArrayList<Object>)d.getSerializable("datos");
        o = (Object[]) z.get(0);
        tempExperiEnfria = (ArrayList<Float>)o[2];
        tempExperiCalenta = (ArrayList<Float>)o[1];
        p = new PracticaTanque(this, null, null);
        hacerCalculos();
        line = (LineChart)findViewById(R.id.graficaTanque);
        GraficaXY.obtenerGraficaCoeficientes(r,line);
    }

    private void hacerCalculos(){
        p.configurarPractica(o);
        r = p.calcularDatosGrafica();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.coeficientes){
            GraficaXY.obtenerGraficaCoeficientes(r,line);
        }
        else if(id == R.id.tiempo){
            GraficaXY.graficarTiempos(r,tempExperiCalenta,tempExperiEnfria,line);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grafica,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
