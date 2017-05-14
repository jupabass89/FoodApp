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
        graficar();
    }

    private void hacerCalculos(){
        p.configurarPractica(o);
        r = p.calcularDatosGrafica();
    }

    private void graficarTiempos(){
        ArrayList<Float> f = (ArrayList<Float>) r.get(2);
        ArrayList<Float> g = (ArrayList<Float>) r.get(3);
        ArrayList<Entry> tiempoCalenTeorico = new ArrayList<>();
        ArrayList<Entry> tiempoEnfiTeorico = new ArrayList<>();
        ArrayList<Entry> tiempoCalenExp = new ArrayList<>();
        ArrayList<Entry> tiempoEnfiExp = new ArrayList<>();


        for (int i = 0; i < f.size(); i++) {
            tiempoCalenExp.add(new Entry(i*60,tempExperiCalenta.get(i)));
            tiempoCalenTeorico.add(new Entry(f.get(i),tempExperiCalenta.get(i)));
        }
        for (int i = 0; i < g.size(); i++) {
            tiempoEnfiExp.add(new Entry(i*60, tempExperiEnfria.get(i)));
            tiempoEnfiTeorico.add(new Entry(g.get(i),tempExperiEnfria.get(i)));
        }

        LineDataSet dataset = new LineDataSet(tiempoCalenExp,"Calent_Experi");
        dataset.setColor(Color.RED);
        LineDataSet datasetE = new LineDataSet(tiempoEnfiExp,"Enfria_Experi");
        datasetE.setColor(Color.rgb(0,0,255));

        LineDataSet datasetF = new LineDataSet(tiempoCalenTeorico,"Calent_Teórico");
        datasetF.setColor(Color.rgb(252,174,18));
        LineDataSet datasetG = new LineDataSet(tiempoEnfiTeorico,"Enfria_Teórico");
        datasetE.setColor(Color.rgb(0,255,0));

        LineData data = new LineData(dataset,datasetE,datasetF,datasetG);
        line.setMaxVisibleValueCount(1);
        line.setData(data);
        line.setBackgroundColor(Color.rgb(245,240,194));
        line.notifyDataSetChanged();
        line.invalidate();
    }


    private void graficar(){
        ArrayList<Float> f = (ArrayList<Float>) r.get(0);
        ArrayList<Float> g = (ArrayList<Float>) r.get(1);
        ArrayList<Entry> c = new ArrayList<>();
        ArrayList<Entry> e = new ArrayList<>();

        for (int i = 0; i < f.size(); i++) {
            c.add(new Entry((float)i*60, f.get(i)-20f));

        }
        for (int i = 0; i < g.size(); i++) {
            e.add(new Entry((float)i*60, g.get(i)-40f));
        }

        LineDataSet dataset = new LineDataSet(c,"Calentamiento");
        dataset.setColor(Color.RED);
        LineDataSet datasetE = new LineDataSet(e,"Enfriamiento");
        datasetE.setColor(Color.BLUE);

        LineData data = new LineData(dataset,datasetE);
        line.setMaxVisibleValueCount(1);
        line.setData(data);
        line.setBackgroundColor(Color.rgb(245,240,194));
        line.notifyDataSetChanged();
        line.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id ==R.id.coeficientes){
            graficar();
        }
        else if(id == R.id.tiempo){
            graficarTiempos();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grafica,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
