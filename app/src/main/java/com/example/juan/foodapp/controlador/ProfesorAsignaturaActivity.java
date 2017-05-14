package com.example.juan.foodapp.controlador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.foodapp.R;

public class ProfesorAsignaturaActivity extends AppCompatActivity {

    private Context contexto;

    private String asignatura;
    private String profesor;
    private String practicaSeleccionada;

    private Button botonContinuar;
    private EditText entradaAsignatura, entradaProfesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_asignatura);
        contexto = this.getApplicationContext();
        Bundle datos = getIntent().getExtras();
        practicaSeleccionada = datos.getString("practicaSeleccionada");
        botonContinuar = (Button)findViewById(R.id.btnContinuar);
        entradaAsignatura = (EditText)findViewById(R.id.asignatura);
        entradaProfesor = (EditText)findViewById(R.id.profesor);


        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposCompletos()) continuar();
                else Toast.makeText(contexto,"Campos obligatorios",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void continuar(){
        asignatura = entradaAsignatura.getText().toString();
        profesor = entradaProfesor.getText().toString();
        Intent intencion;
        switch (practicaSeleccionada){
            case ("Tanque Agitado"):
                intencion = new Intent(this, ControlTanquesPrincipal.class);
                intencion.putExtra("Asignatura", asignatura);
                intencion.putExtra("Profesor", profesor);
                startActivity(intencion);
                break;
            case ("Pasteurizador de Placas"):
                intencion = new Intent(this, ControlPlacasPrincipal.class);
                intencion.putExtra("Asignatura", asignatura);
                intencion.putExtra("Profesor", profesor);
                startActivity(intencion);
                break;
        }
    }

    private boolean camposCompletos(){
        boolean campo1 = !entradaAsignatura.getText().toString().equals("");
        boolean campo2 = !entradaProfesor.getText().toString().equals("");
        return (campo1 && campo2);
    }
}
