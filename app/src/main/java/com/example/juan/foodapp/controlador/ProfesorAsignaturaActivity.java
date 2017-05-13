package com.example.juan.foodapp.controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juan.foodapp.R;

public class ProfesorAsignaturaActivity extends AppCompatActivity {

    private String practicaSeleccionada;
    private String asignatura;
    private String profesor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_asignatura);
        Bundle datos = getIntent().getExtras();
        practicaSeleccionada = datos.getString("practicaSeleccionada");
    }

    private void continuar(){
        Intent intencion;
        switch (practicaSeleccionada){
            case ("Tanque Agitado"):
                intencion = new Intent(this, VistaTanquesPrincipal.class);
                intencion.putExtra("Asignatura", asignatura);
                intencion.putExtra("Profesor", profesor);
                startActivity(intencion);
                break;
            case ("Pasteurizador de Placas"):
                intencion = new Intent(this, VistaPlacasPrincipal.class);
                intencion.putExtra("Asignatura", asignatura);
                intencion.putExtra("Profesor", profesor);
                startActivity(intencion);
                break;
        }
    }
}
