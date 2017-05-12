package com.example.juan.foodapp.controlador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransferenciaPlacasActivity extends AppCompatActivity {

    private PracticaPlacas practica;
    private Context contexto;

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapterTransferenciaPlacas adapter;

    ExpandableListAdapterTransferenciaPlacas capturaDatos = new ExpandableListAdapterTransferenciaPlacas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Actividad a pantalla completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_transferencia_placas);
        contexto = this.getApplicationContext();

        //Muestra barra de acción
        ActionBar ab = getSupportActionBar();
        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        expandableListView = (ExpandableListView) findViewById(R.id.transferencia_placas_list);
        expandableListView.setGroupIndicator(null);

        setItems();
        setListener();

        final Button ingresarDatos = (Button) findViewById(R.id.btnCalculoPlacas);
        ingresarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String profesor = "ejemplo nombre profesor"; // Estos datos vendran en un Bundle a la actividad
                String asignatura = "ejemplo asignatura";
                practica = new PracticaPlacas(contexto, asignatura, profesor);
            }
        });
    }

    void setItems() {

        ArrayList<String> header = new ArrayList<String>();

        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();

        header.add("Temperatura del alimento (Fluido frío)");
        child1.add("");

        header.add("Temperatura del fluido del servicio (Fuido caliente)");
        child2.add("");

        header.add("Dato general de la práctica");
        child3.add("");

        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        adapter = new ExpandableListAdapterTransferenciaPlacas(TransferenciaPlacasActivity.this, header, hashMap);
        expandableListView.setAdapter(adapter);
    }

    void setListener() {
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override public void onGroupCollapse(int groupPosition) {
            Toast.makeText(getApplicationContext(),
            "Datos Guardados",
            Toast.LENGTH_SHORT).show();
        }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                break;
            case (R.id.verGuia):
                Intent intencion = new Intent(this, Guia.class);
                intencion.putExtra("nombrePractica", "pasterizacionPlacas.pdf");
                startActivity(intencion);
                break;
        }
        return (true);
    }
}