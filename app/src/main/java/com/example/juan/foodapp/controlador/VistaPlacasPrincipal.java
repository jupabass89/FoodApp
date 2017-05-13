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
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

public class VistaPlacasPrincipal extends AppCompatActivity {

    private PracticaPlacas practica;
    private Context contexto;


    //Botones de la vista

    Button capturaDatos;

    //Campos de texto de la vista

    EditText campo1;
    EditText campo2;
    EditText campo3;
    EditText campo4;
    EditText campo5;
    EditText campo6;
    EditText campo7;

    //variables para obtener los datos ingresados


    private String tempAlimentoEntrada;
    private String caudalEntradaAlimento;
    private String coefIncrustacionFrio;
    private String tempFluidoServicioEntrada;
    private String coefIncrustacionCaliente;
    private String coefGlobalTransfer;
    private String nombreDatosGuardados;


    public String getNombreDatosGuardados() {
        return nombreDatosGuardados;
    }

    public String getCoefGlobalTransfer() {
        return coefGlobalTransfer;
    }

    public String getCoefIncrustacionCaliente() {
        return coefIncrustacionCaliente;
    }

    public String getTempFluidoServicioEntrada() {
        return tempFluidoServicioEntrada;
    }

    public String getCoefIncrustacionFrio() {
        return coefIncrustacionFrio;
    }

    public String getCaudalEntradaAlimento() {
        return caudalEntradaAlimento;
    }

    public String getTempAlimentoEntrada() {
        return tempAlimentoEntrada;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_placas_principal);
        contexto = this.getApplicationContext();

        //Muestra barra de acción
        ActionBar ab = getSupportActionBar();

        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        //Asociación de la captura de datos con los campos de la vista
        campo1 = (EditText)findViewById(R.id.campo1);
        campo2 = (EditText)findViewById(R.id.campo2);
        campo3 = (EditText)findViewById(R.id.campo3);
        campo4 = (EditText)findViewById(R.id.campoFibra);
        campo5 = (EditText)findViewById(R.id.campoProteina);
        campo6 = (EditText)findViewById(R.id.campo6);
        campo7 = (EditText)findViewById(R.id.campoNombre);

       //Asociación de los botnoes con los botones de la vista

        capturaDatos = (Button)findViewById(R.id.btnCalcula);

        capturaDatos.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                tempAlimentoEntrada = campo1.getText().toString();
                caudalEntradaAlimento = campo2.getText().toString();
                coefIncrustacionFrio = campo3.getText().toString();
                tempFluidoServicioEntrada = campo4.getText().toString();
                coefIncrustacionCaliente = campo5.getText().toString();
                coefGlobalTransfer = campo6.getText().toString();
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
                intencion.putExtra("nombrePractica", "pasterizacionPlacas.pdf");
                startActivity(intencion);
                break;
        }
        return (true);
    }
}
