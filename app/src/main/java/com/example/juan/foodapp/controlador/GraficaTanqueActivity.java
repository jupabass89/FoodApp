package com.example.juan.foodapp.controlador;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    EditText e;
    Object[] o;
    LineChart line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafica_tanque);

        int i = 0;
        Bundle d = getIntent().getExtras();

        ArrayList<Object> z = (ArrayList<Object>)d.getSerializable("prueba");
        o = (Object[]) z.get(0);
        p = new PracticaTanque(this, null, null);

        p.configurarPractica(o);

        r = p.calcularDatosGrafica();

        graficar();
        /*e = (EditText)findViewById(R.id.editText4);
        Button b = (Button)findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = e.getText().toString();

                if(d.compareTo("")!=0){
                    o[8]=Float.parseFloat(d);
                    p.configurarPractica(o);
                    r = p.calcularDatosGrafica();
                    graficar();
                    long m = System.currentTimeMillis();
                    line.notifyDataSetChanged();
                    line.invalidate();
                    Log.e("DATOSSSS",""+(System.currentTimeMillis()-m));
                }
                else{
                    Toast.makeText(getApplicationContext(),"No hay datos ingresados.",Toast.LENGTH_LONG);
                }

            }
        });*/


    }


    public void graficar(){
        ArrayList<Float> f = (ArrayList<Float>) r.get(0);
        ArrayList<Float> g = (ArrayList<Float>) r.get(1);

        ArrayList<Entry> e = new ArrayList<>();
        ArrayList<Entry> d = new ArrayList<>();

        for (int i = 0; i < f.size(); i++) {
            e.add(new Entry((float)i*60, f.get(i)-40f));
            if(i<10){
                d.add(new Entry((float)i*60, g.get(i)-50f));
            }
        }
        LineDataSet dataset = new LineDataSet(e,"Calentamiento");
        dataset.setColor(Color.RED);
        LineDataSet datasetE = new LineDataSet(d,"Enfriamiento");
        datasetE.setColor(Color.BLUE);
        LineData data = new LineData(dataset,datasetE);
        //line.setMaxVisibleValueCount(1);
        line.setData(data);
        line.setBackgroundColor(Color.rgb(245,240,194));
    }
}
