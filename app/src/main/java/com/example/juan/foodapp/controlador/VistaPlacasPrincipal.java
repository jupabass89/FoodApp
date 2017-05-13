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
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.*;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

public class VistaPlacasPrincipal extends AppCompatActivity {

    private PracticaPlacas practica;
    private Context contexto;

    private String asignatura;
    private String profesor;

    //Botones de la vista

    Button capturaDatos;

    //Campos de texto de la vista

    EditText entrada1;
    EditText entrada2;
    EditText entrada3;
    EditText entrada4;
    EditText entrada5;
    EditText entrada6;
    EditText entradaNombre;

    //Variables para obtener los datos ingresados

    private String tempAlimentoEntrada;
    private String caudalEntradaAlimento;
    private String coefIncrustacionFLuidoFrio;
    private String tempFluidoServicioEntrada;
    private String coefIncrustacionCaliente;
    private String coefGlobalTransfer;
    private String nombreDatosGuardados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_placas_principal);
        contexto = this.getApplicationContext();
        Bundle datos = getIntent().getExtras();
        asignatura = datos.getString("Asignatura");
        profesor = datos.getString("Profesor");

        //Muestra barra de acción
        ActionBar ab = getSupportActionBar();

        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        //Asociación de la captura de datos con los campos de la vista
        entrada1 = (EditText)findViewById(R.id.entrada1);
        entrada2 = (EditText)findViewById(R.id.entrada2);
        entrada3 = (EditText)findViewById(R.id.entrada3);
        entrada4 = (EditText)findViewById(R.id.entrada4);
        entrada5 = (EditText)findViewById(R.id.entrada5);
        entrada6 = (EditText)findViewById(R.id.entrada6);
        entradaNombre = (EditText)findViewById(R.id.entradaNombre);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                practica = new PracticaPlacas(contexto, "Ejemplo", "Ejemplo2");
                practica.generarInforme("pdf1");
                // Configurar Toast
                // "RUTA: Descargas/Practicas
                Toast.makeText(this, "PDF GENERADO", Toast.LENGTH_LONG).show();
                break;
            case (R.id.verGuia):
                Intent intencion = new Intent(this, Guia.class);
                intencion.putExtra("nombrePractica", "pasterizacionPlacas.pdf");
                startActivity(intencion);
                break;
        }
        return (true);
    }

    private boolean camposCompletos(){
        boolean entrada1V = !entrada1.getText().toString().equals("");
        boolean entrada2V = !entrada2.getText().toString().equals("");
        boolean entrada3V = !entrada3.getText().toString().equals("");
        boolean entrada4V = !entrada4.getText().toString().equals("");
        boolean entrada5V = !entrada5.getText().toString().equals("");
        boolean entrada6V = !entrada6.getText().toString().equals("");
        return (entrada1V && entrada2V && entrada3V && entrada4V && entrada5V && entrada6V);
    }

    private void realizarCalculos(){
        if(!camposCompletos()){
            mostarMensaje("Debe llenar todos los campos para la practica");
            return;
        }
        //
        practica = new PracticaPlacas(contexto, asignatura, profesor);
        practica.configurarPractica(configurarDatosPractica());
        practica.calcularDatosZonaPasterizacion();
        //
    }

    private void mostarMensaje(String texto){
        Toast.makeText(this, texto,Toast.LENGTH_LONG).show();
    }

    private Object[] configurarDatosPractica(){
        Object[] datos = new Object[6];
        datos[0] = entrada1.getText().toString();
        datos[1] = entrada2.getText().toString();
        datos[2] = entrada3.getText().toString();
        datos[3] = entrada4.getText().toString();
        datos[4] = entrada5.getText().toString();
        datos[5] = entrada6.getText().toString();
        return (datos);
    }
}
