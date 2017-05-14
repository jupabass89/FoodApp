package com.example.juan.foodapp.controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.foodapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PracticasGuardadasPlacas extends AppCompatActivity {

    ListView lv;
    ArrayList<String> practicasGuardadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_practicas_guardadas_placas);
        Bundle datos = getIntent().getExtras();
        practicasGuardadas = datos.getStringArrayList("datos");

        View header = (View) getLayoutInflater().inflate(R.layout.list_header_row_selecciona_practica, null);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Lista de registros recuperados", Toast.LENGTH_SHORT).show();
            }
        });

        if(practicasGuardadas != null){
            ListaDeRegistrosAdapterActivity adapter = new ListaDeRegistrosAdapterActivity(this, R.layout.listview_item_row_selecciona_registro,
                    convertirArray(practicasGuardadas.toArray()));
            lv = (ListView) findViewById(R.id.lvRegistros);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView v = (TextView) view.findViewById(R.id.tvRegistros);
                    ControlPlacasPrincipal.setPracticaSeleccionada(v.getText().toString());
                    finish();
                }
            });
        }
    }

    private String[] convertirArray(Object[] vector){
        String[] nuevo = new String[vector.length];
        for(int i=0; i<vector.length; i++){
            nuevo[i] = vector[i].toString();
        }return (nuevo);
    }
}
