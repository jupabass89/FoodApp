package com.example.juan.foodapp.modelo.serviciosPractica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.juan.foodapp.R;
import com.github.barteksc.pdfviewer.PDFView;

public class Guia extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_practica);
        Bundle datos = getIntent().getExtras();
        String archivo = datos.getString("nombrePractica");
        PDFView pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset(archivo).load();
    }
}
