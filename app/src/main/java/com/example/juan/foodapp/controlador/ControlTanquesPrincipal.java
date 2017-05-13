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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.practicaTanque.PracticaTanque;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

public class ControlTanquesPrincipal extends AppCompatActivity {

    private PracticaTanque practica;
    private Context contexto;

    //Botones de la vista

    Button btnCalculaTanque;

    // Variables para Guardar los datos

    //Alimento


    private String nombreAlimento;
    private String porcentajeCarbohidratos;
    private String porcentajeCeniza;
    private String porcentajeFibra;
    private String porcentajeProteina;
    private String porcentajeGrasa;
    private String porcentajeViscosidad;
    private String Volumen;
    private String TempInicialAlimen;

    //Fluido de servicio

    /**
     * PENDIENTE:
     * CAMBIAR A private String y cambiarle el nombre Y HACER LOS GETTERS
     *
     *
     * EditText txtEntradaFlui;
     EditText txtSalidaFlui;
     EditText txtFlujoMas;

     //Tanque

     EditText txtAltProd;
     EditText txtEspesor;
     EditText txtDiamInter;
     EditText txtDiameExt;
     EditText txtConduc;
     EditText txtIncrustac;
     EditText chaqueta;


     //Agitador

     EditText txtAltAgit;
     EditText txtDiamAgit;

     //Temperaturas de calentamiento

     EditText txtTempcal1;
     EditText txtTempcal2;
     EditText txtTempcal3;
     EditText txtTempcal4;
     EditText txtTempcal5;
     EditText txtTempcal6;
     EditText txtTempcal7;
     EditText txtTempcal8;
     EditText txtTempcal9;
     EditText txtTempcal10;


     //Temperaturas de calentamiento


     EditText txtTempEnfri1;
     EditText txtTempEnfri2;
     EditText txtTempEnfri3;
     EditText txtTempEnfri4;
     EditText txtTempEnfri5;
     EditText txtTempEnfri6;
     EditText txtTempEnfri7;
     EditText txtTempEnfri8;
     EditText txtTempEnfri9;
     EditText txtTempEnfri10;
     **/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_tanques_principal);
        contexto = this.getApplicationContext();

        //Muestra barra de acción
        ActionBar ab = getSupportActionBar();

        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_agitador);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_tipo_agitador, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


        //Asosiación vista - clase


        //Alimento


        final EditText txtNomAlim = (EditText)findViewById(R.id.campoNombreAlimento);
        final EditText txpoCarb = (EditText)findViewById(R.id.campoCarbohi);
        final EditText txtCeniza = (EditText)findViewById(R.id.campoCeniza);
        final EditText txtFibra = (EditText)findViewById(R.id.campoFibra);
        final EditText txtProtein = (EditText)findViewById(R.id.campoProteina);
        final EditText txtGrasa = (EditText)findViewById(R.id.campoGrasa);
        final EditText txtViscosidad = (EditText)findViewById(R.id.campoVisco);
        final EditText txtVolumen = (EditText)findViewById(R.id.campoVolumen);
        final EditText txtTempInicialAlimen =(EditText)findViewById(R.id.campoTempIniciAlimento);


        //Fluido de servicio

        final EditText txtEntradaFlui =(EditText)findViewById(R.id.tempEntrFlu);
        final EditText txtSalidaFlui =(EditText)findViewById(R.id.tempSaliFlu);
        final EditText txtFlujoMas =(EditText)findViewById(R.id.flujoMasico);


        //Tanque

        final EditText  txtAltProd =(EditText)findViewById(R.id.campoAltProd);
        final EditText txtEspesor =(EditText)findViewById(R.id.campoEspesor);
        final EditText txtDiamInter =(EditText)findViewById(R.id.campoDiamInt);
        final EditText txtDiameExt =(EditText)findViewById(R.id.campoDiameExt);
        final EditText txtConduc =(EditText)findViewById(R.id.campoConduc);
        final EditText txtIncrustac =(EditText)findViewById(R.id.campoFactIncru);
        final EditText txtTempChaqueta=(EditText)findViewById(R.id.campoTempChaqueta);

        //Agitador

        final EditText  txtAltAgit = (EditText)findViewById(R.id.campoAltAgit);
        final EditText txtDiamAgit = (EditText)findViewById(R.id.campoDiametroAgit);

        //Temperaturas de calentamiento

        final EditText txtTempcal1 = (EditText)findViewById(R.id.tempCal1);
        final EditText txtTempcal2 = (EditText)findViewById(R.id.tempCal2);
        final EditText txtTempcal3 = (EditText)findViewById(R.id.tempCal3);
        final EditText txtTempcal4 = (EditText)findViewById(R.id.tempCal4);
        final EditText txtTempcal5 = (EditText)findViewById(R.id.tempCal5);
        final EditText txtTempcal6 = (EditText)findViewById(R.id.tempCal6);
        final EditText txtTempcal7 = (EditText)findViewById(R.id.tempCal7);
        final EditText txtTempcal8 = (EditText)findViewById(R.id.tempCal8);
        final EditText txtTempcal9 = (EditText)findViewById(R.id.tempCal9);
        final EditText txtTempcal10 = (EditText)findViewById(R.id.tempCal10);


        //Temperaturas de calentamiento


        final EditText txtTempEnfri1 = (EditText)findViewById(R.id.tempEnfri1);
        final EditText txtTempEnfri2 = (EditText)findViewById(R.id.tempEnfri2);
        final EditText txtTempEnfri3 = (EditText)findViewById(R.id.tempEnfri3);
        final EditText txtTempEnfri4 = (EditText)findViewById(R.id.tempEnfri4);
        final EditText txtTempEnfri5 = (EditText)findViewById(R.id.tempEnfri5);
        final EditText txtTempEnfri6 = (EditText)findViewById(R.id.tempEnfri6);
        final EditText txtTempEnfri7 = (EditText)findViewById(R.id.tempEnfri7);
        final EditText txtTempEnfri8 = (EditText)findViewById(R.id.tempEnfri8);
        final EditText txtTempEnfri9 = (EditText)findViewById(R.id.tempEnfri9);
        final EditText txtTempEnfri10 = (EditText)findViewById(R.id.tempEnfri10);

        //Botón para calcular
        btnCalculaTanque = (Button)findViewById(R.id.btnCalculaTanque);

        btnCalculaTanque.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                //Acciones al darle click



            }
        });



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                practica.generarInforme("pdf1");
                // Configurar Toast
                // "RUTA: Descargas/Practicas
                Toast.makeText(this, "PDF GENERADO", Toast.LENGTH_LONG).show();
                break;
            case (R.id.verGuia):
                Intent intencion = new Intent(this, Guia.class);
                intencion.putExtra("nombrePractica", "tanqueAgitado.pdf");
                startActivity(intencion);
                break;
        }
        return (true);
    }
}
