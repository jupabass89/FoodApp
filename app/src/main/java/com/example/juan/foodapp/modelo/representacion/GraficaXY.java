package com.example.juan.foodapp.modelo.representacion;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.juan.foodapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class GraficaXY {

    public static void obtenerGraficaCoeficientes(ArrayList<Object> r,LineChart line){
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

    public static void graficarTiempos(ArrayList<Object> r,ArrayList<Float> tempExperiCalenta,ArrayList<Float> tempExperiEnfria,LineChart line){
        ArrayList<Float> f = (ArrayList<Float>) r.get(2);
        ArrayList<Float> g = (ArrayList<Float>) r.get(3);
        ArrayList<Entry> tiempoCalenTeorico = new ArrayList<>();
        ArrayList<Entry> tiempoEnfiTeorico = new ArrayList<>();
        ArrayList<Entry> tiempoCalenExp = new ArrayList<>();
        ArrayList<Entry> tiempoEnfiExp = new ArrayList<>();


        for (int i = 0; i < f.size(); i++) {
            tiempoCalenExp.add(new Entry(i*30,tempExperiCalenta.get(i)));
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

    public static void graficaPlacas(LineChart line, ArrayList<Float> tempEntradaAlimento, ArrayList<Float> coeficienteTCAlimento,
                                     ArrayList<Float> areaDiseño){
        ArrayList<Entry> datos1 = new ArrayList<>();
        for (int i = 0; i < tempEntradaAlimento.size(); i++) {
            datos1.add(new Entry(tempEntradaAlimento.get(i),coeficienteTCAlimento.get(i)));
        }
        LineDataSet dataset1 = new LineDataSet(datos1, "Temperatura Entrada Alimento vs Coef.TC del Alimento");
        dataset1.setColor(Color.RED);

        ArrayList<Entry> datos2 = new ArrayList<>();
        for (int i = 0; i < tempEntradaAlimento.size(); i++) {
            datos2.add(new Entry(tempEntradaAlimento.get(i),areaDiseño.get(i)));
        }

        LineDataSet dataset2 = new LineDataSet(datos2, "Temperatura Entrada Alimento vs Area Diseño ");
        dataset2.setColor(Color.GREEN);

        LineData data = new LineData(dataset1, dataset2);
        line.setData(data);
        line.setBackgroundColor(Color.WHITE);
    }
}
