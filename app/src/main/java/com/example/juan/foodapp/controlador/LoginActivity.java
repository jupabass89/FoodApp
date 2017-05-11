package com.example.juan.foodapp.controlador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.Estudiante;

public class LoginActivity extends AppCompatActivity {

    Context contexto;
    EditText campoNombre, campoID, campoCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        contexto = this.getApplicationContext();

        campoNombre = (EditText)findViewById(R.id.userName);
        campoID = (EditText)findViewById(R.id.userId);
        campoCorreo = (EditText)findViewById(R.id.userMail);

        final Button login = (Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean datosDiligenciados = validarCampos();
                //if(datosDiligenciados){
                if(true){
                    guardarDatosEstudiante();
                    Intent intent = new Intent(v.getContext(), SeleccionarLaboratorioActivity.class);
                    startActivityForResult(intent, 0);
                }else
                    Toast.makeText(contexto, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *  Se valida que los campos de entrada del estudiante esten diligenciados.
     * @return True si los campos estan diligenciados, False de lo contrario.
     */
    private boolean validarCampos(){
        boolean campo1 = !campoNombre.getText().toString().equals("");
        boolean campo2 = !campoID.getText().toString().equals("");
        boolean campo3 = !campoCorreo.getText().toString().equals("");
        return (campo1 && campo2 && campo3);
    }

    private void guardarDatosEstudiante(){
        Estudiante estudiante = Estudiante.getEstudiante();
        estudiante.setNombre(campoNombre.getText().toString());
        estudiante.setId(campoID.getText().toString());
        estudiante.setMail(campoCorreo.getText().toString());
    }
}
