package com.example.juan.foodapp.controlador;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.juan.foodapp.R;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;
import java.util.ArrayList;

public class ControlTanquesPrincipal extends AppCompatActivity {
    private Context contexto;
    private ArrayList<Float> temperaturasDeCalentamiento;
    private ArrayList<Float> temperaturasEnfriamiento;
    private Object datos[];

    private int intervaloTiempo;
    private Spinner spinnerAgitador;
    protected Spinner spinnerIntervalos;
    private CheckBox agua;                 //controla el tipo de alimento que se trabajará
    private ArrayList<Object> datosB;      //paquete con los datos a pasar para la siguiente actividad

    //Contadores para los tiempos textView de los inputs de las temperaturas
    private int contadorTiemposCalentamiento;
    private int contadorTiemposEnfriamiento;

    //Alimento
    private EditText txtNomAlim;
    private EditText txtCarb;
    private EditText txtCeniza;
    private EditText txtFibra;
    private EditText txtProtein;
    private EditText txtGrasa;
    private EditText txtViscosidad;
    private EditText txtVolumen;
    private EditText txtTempInicialAlimen;

    //Fluido de servicio
    private EditText txtEntradaFlui;
    private EditText txtSalidaFlui;
    private EditText txtFlujoMas;

    //Tanque
    private EditText txtAltProd;
    private EditText txtEspesor;
    private EditText txtDiamInter;
    private EditText txtDiameExt;
    private EditText txtConduc;
    private EditText txtIncrustac;
    private EditText txtTempChaqueta;

    //Agitador
    private EditText txtAltAgit;
    private EditText txtDiamAgit;
    private EditText txtRps;

    //Temperaturas de calientamiento input
    private EditText tempCalentamiento;

    //Temp de enfriamiento input
    private EditText tempEnfriamiento;

    //Tiempos de calentamiento y de enfriamiento labels
    private TextView tiempoCalentamiento;
    private TextView tiempoEnfriamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_tanques_principal);

        //Contadores para los tiempos textView de los inputs de las temperaturas
        contadorTiemposCalentamiento = 0;
        contadorTiemposEnfriamiento = 0;

        //Alimento
        txtNomAlim = (EditText) findViewById(R.id.campoNombreAlimento);
        txtCarb = (EditText) findViewById(R.id.campoCarbohi);
        txtCeniza = (EditText) findViewById(R.id.campoCeniza);
        txtFibra = (EditText) findViewById(R.id.campoFibra);
        txtProtein = (EditText) findViewById(R.id.campoProteina);
        txtGrasa = (EditText) findViewById(R.id.campoGrasa);
        txtViscosidad = (EditText) findViewById(R.id.campoVisco);
        txtVolumen = (EditText) findViewById(R.id.campoVolumen);
        txtTempInicialAlimen = (EditText) findViewById(R.id.campoTempIniciAlimento);

        //Fluido de servicio
        txtEntradaFlui = (EditText) findViewById(R.id.tempEntrFlu);
        txtSalidaFlui = (EditText) findViewById(R.id.tempSaliFlu);
        txtFlujoMas = (EditText) findViewById(R.id.flujoMasico);

        //Tanque
        txtAltProd = (EditText) findViewById(R.id.campoAltProd);
        txtEspesor = (EditText) findViewById(R.id.campoEspesor);
        txtDiamInter = (EditText) findViewById(R.id.campoDiamInt);
        txtDiameExt = (EditText) findViewById(R.id.campoDiameExt);
        txtConduc = (EditText) findViewById(R.id.campoConduc);
        txtIncrustac = (EditText) findViewById(R.id.campoFactIncru);
        txtTempChaqueta = (EditText) findViewById(R.id.campoTempChaqueta);

        //Agitador
        txtAltAgit = (EditText) findViewById(R.id.campoAltAgit);
        txtDiamAgit = (EditText) findViewById(R.id.campoDiametroAgit);
        txtRps = (EditText) findViewById(R.id.campoRps);

        //Temperaturas de calientamiento input
        tempCalentamiento = (EditText) findViewById(R.id.tempCal);

        //Temp de enfriamiento input
        tempEnfriamiento = (EditText) findViewById(R.id.tempEnfri);

        //Tiempos de calentamiento y de enfriamiento labels
        tiempoCalentamiento = (TextView) findViewById(R.id.tiempoCalentamiento);
        tiempoEnfriamiento = (TextView) findViewById(R.id.tiempoEnfriamiento);

        //Configurando logo accion bar
        ActionBar ab = getSupportActionBar();
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        contexto = this.getApplicationContext();
        temperaturasDeCalentamiento = new ArrayList<>();
        temperaturasEnfriamiento = new ArrayList<>();
        datos = new Object[13];
        datosB = new ArrayList<>();

        //configurando metodo de entrada del teclado

        InputMethodManager teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(tempCalentamiento.getWindowToken(), 0);  //ocultando teclado al dar ok
        teclado.hideSoftInputFromWindow(tempEnfriamiento.getWindowToken(), 0);   //ocultando teclado al dar ok




        // configurando tipo de alimento
        agua = (CheckBox) findViewById(R.id.esAgua);
        agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (agua.isChecked()) {
                    txtNomAlim.setEnabled(false);
                    txtCarb.setEnabled(false);
                    txtGrasa.setEnabled(false);
                    txtCeniza.setEnabled(false);
                    txtFibra.setEnabled(false);
                    txtProtein.setEnabled(false);
                    txtViscosidad.setEnabled(false);
                    txtNomAlim.setText("Agua");
                } else {
                    txtCarb.setEnabled(true);
                    txtGrasa.setEnabled(true);
                    txtCeniza.setEnabled(true);
                    txtFibra.setEnabled(true);
                    txtProtein.setEnabled(true);
                    txtViscosidad.setEnabled(true);
                    txtNomAlim.setText("");
                }
            }
        });

        //configurando spiner en alimento
        spinnerAgitador = (Spinner) findViewById(R.id.spinner_agitador);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_tipo_agitador, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAgitador.setAdapter(adapter);

        //configurando spiner de intervalos
        spinnerIntervalos = (Spinner) findViewById(R.id.intervalosCalentamientoSpiner);
        ArrayAdapter<CharSequence> adapterIntervals = ArrayAdapter.createFromResource(this, R.array.spinner_Intervalos, android.R.layout.simple_spinner_item);
        adapterIntervals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIntervalos.setAdapter(adapterIntervals);
        spinnerIntervalos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) spinnerIntervalos.getSelectedItem();
                int i = Integer.parseInt(s);
                intervaloTiempo = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Entradas de temperaturas al teclado
                EventoTeclado eventoT = new EventoTeclado();
                tempCalentamiento.setOnEditorActionListener(eventoT);
                tempEnfriamiento.setOnEditorActionListener(eventoT);
    }

    //Clase que se encarga de ejecutar el método de entrada de temperaturas mediante el imput metod manager (sin botón)
    public class EventoTeclado implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //int action = v.getId();
                if (actionId == tempCalentamiento.getId()) {
                    ingresarTemperaturasCalentamiento(null);
                } else {
                    if (actionId == tempEnfriamiento.getId()) {
                        ingresarTemperaturasEnfriamiento(null);
                    }
                }
            }
            return false;
        }
    }

    public void ingresarTemperaturasCalentamiento(View view) {
        String t = tempCalentamiento.getText().toString();
        Float f = Float.parseFloat(t);
        if (f > 78f) {
            if (f > temperaturasDeCalentamiento.get(temperaturasDeCalentamiento.size())) {
                temperaturasDeCalentamiento.add(f);
                contadorTiemposCalentamiento++;
                tiempoCalentamiento.setText((contadorTiemposCalentamiento * intervaloTiempo) + " s");
                tempCalentamiento.setText("");
            } else {
                mensaje("Debe ingresar una temperatura mayor a la anterior");
            }
        } else{
            mensaje("Ha superado la temperatura límite");
        }
    }

    public void ingresarTemperaturasEnfriamiento(View view) {
        String t = tempEnfriamiento.getText().toString();
        Float f = Float.parseFloat(t);

        //if(f<0)
        if (f < temperaturasDeCalentamiento.get(temperaturasDeCalentamiento.size())) {
            temperaturasEnfriamiento.add(f);
            contadorTiemposEnfriamiento++;
            tiempoEnfriamiento.setText(contadorTiemposEnfriamiento * intervaloTiempo + " s");
            tempEnfriamiento.setText("");
        } else {
            mensaje("Debe ingresar una temperatura menor a la anterior");
        }
    }

    public void graficar(View view) {
        boolean control = false;
        try {
            if (temperaturasEnfriamiento.size() == 0) {
                mensaje("Ingrese algunas temperaturas de calentamiento.");
                return;
            }
            if (temperaturasEnfriamiento.size() == 0) {
                mensaje("Ingrese algunas temperaturas de enfriamiento.");
                return;
            }
            if (!spinnerIntervalos.isSelected()) {
                mensaje("Elija un intervalo de tiempo.");
                return;
            }

            datos[0] = agua.isChecked();
            datos[1] = temperaturasDeCalentamiento;
            datos[2] = temperaturasEnfriamiento;
            if (!agua.isChecked()) {
                float porcentajes[] = new float[5];
                porcentajes[0] = Float.parseFloat(txtGrasa.getText().toString());
                porcentajes[1] = Float.parseFloat(txtProtein.getText().toString());
                porcentajes[2] = Float.parseFloat(txtFibra.getText().toString());
                porcentajes[3] = Float.parseFloat(txtCeniza.getText().toString());
                porcentajes[4] = Float.parseFloat(txtCarb.getText().toString());
                datos[7] = Float.parseFloat(txtViscosidad.getText().toString());
                datos[3] = porcentajes;
            }
            datos[4] = spinnerAgitador.getSelectedItemPosition();
            float agitador[] = {Float.parseFloat(txtAltAgit.getText().toString()),
                    Float.parseFloat(txtDiamAgit.getText().toString()), Float.parseFloat(txtRps.getText().toString())};

            datos[5] = agitador;
            float tanque[] = {Float.parseFloat(txtAltProd.getText().toString()), Float.parseFloat(txtEspesor.getText().toString()),
                    Float.parseFloat(txtDiamInter.getText().toString()), Float.parseFloat(txtDiameExt.getText().toString()),
                    0, Float.parseFloat(txtTempChaqueta.getText().toString()), Float.parseFloat(txtConduc.getText().toString()),
                    Float.parseFloat(txtIncrustac.getText().toString())};
            datos[6] = tanque;

            datos[8] = Float.parseFloat(txtTempInicialAlimen.getText().toString());
            datos[9] = Float.parseFloat(txtVolumen.getText().toString());
            datos[10] = Float.parseFloat(txtEntradaFlui.getText().toString());
            datos[11] = Float.parseFloat(txtSalidaFlui.getText().toString());
            datos[12] = Float.parseFloat(txtFlujoMas.getText().toString());

        } catch (Exception e) {
            mensaje("Faltan campos por llenar.");
            control = true;
        }
        if (!control) {
            datosB.add(datos);
            Intent intencion = new Intent(contexto, GraficaTanqueActivity.class);
            intencion.putExtra("datos", datosB);
            startActivity(intencion);
        }
    }

    private void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                mensaje("Esta opción no se encuentra disponible");
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
/*
    //Temperaturas de calentamient
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

    //Temperaturas de enfriamiento
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
*/
    //Tiempos temperaturas Calentamiento
        /*
        final TextView txtTempCalenta1 = (TextView)findViewById(R.id.tempCalentam1);
        final TextView txtTempCalenta2 = (TextView)findViewById(R.id.tempCalentam2);
        final TextView txtTempCalenta3 = (TextView)findViewById(R.id.tempCalentam3);
        final TextView txtTempCalenta4 = (TextView)findViewById(R.id.tempCalentam4);
        final TextView txtTempCalenta5 = (TextView)findViewById(R.id.tempCalentam5);
        final TextView txtTempCalenta6 = (TextView)findViewById(R.id.tempCalentam6);
        final TextView txtTempCalenta7 = (TextView)findViewById(R.id.tempCalentam7);
        final TextView txtTempCalenta8 = (TextView)findViewById(R.id.tempCalentam8);
        final TextView txtTempCalenta9 = (TextView)findViewById(R.id.tempCalentam9);
        final TextView txtTempCalenta10 = (TextView)findViewById(R.id.tempCalentam10);
        */
        /*
        //Tiempos temperaturas enfriamiento
        final TextView txtTempEnfria1 = (TextView) findViewById(R.id.tempEnfria1);
        final TextView txtTempEnfria2 = (TextView)findViewById(R.id.tempEnfria2);
        final TextView txtTempEnfria3 = (TextView)findViewById(R.id.tempEnfria3);
        final TextView txtTempEnfria4 = (TextView)findViewById(R.id.tempEnfria4);
        final TextView txtTempEnfria5 = (TextView)findViewById(R.id.tempEnfria5);
        final TextView txtTempEnfria6 = (TextView)findViewById(R.id.tempEnfria6);
        final TextView txtTempEnfria7 = (TextView)findViewById(R.id.tempEnfria7);
        final TextView txtTempEnfria8 = (TextView)findViewById(R.id.tempEnfria8);
        final TextView txtTempEnfria9 = (TextView)findViewById(R.id.tempEnfria9);
        final TextView txtTempEnfria10 = (TextView)findViewById(R.id.tempEnfria10);
        */
/*
        //Botón para calcular
        graficarButton = (Button)findViewById(R.id.graficarButton);
        graficarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acciones al darle click

               boolean control = false;
                try{
                    if(temperaturasEnfriamiento.size()==0){
                        mensaje("Ingrese algunas temperaturas de calentamiento.");
                        return;
                    }
                    if(temperaturasEnfriamiento.size()==0){
                        mensaje("Ingrese algunas temperaturas de enfriamiento.");
                        return;
                    }

                    datos[0] = agua.isChecked();
                    datos[1] = temperaturasDeCalentamiento;
                    datos[2] = temperaturasEnfriamiento;
                    if(!agua.isChecked()){
                        float porcentajes[] = new float[5];
                        porcentajes[0] = Float.parseFloat(txtGrasa.getText().toString());
                        porcentajes[1] = Float.parseFloat(txtProtein.getText().toString());
                        porcentajes[2] = Float.parseFloat(txtFibra.getText().toString());
                        porcentajes[3] = Float.parseFloat(txtCeniza.getText().toString());
                        porcentajes[4] = Float.parseFloat(txtCarb.getText().toString());
                        datos[7] = Float.parseFloat(txtViscosidad.getText().toString());
                        datos[3] = porcentajes;
                    }
                    datos[4] = spinnerAgitador.getSelectedItemPosition();
                    float agitador[] = {Float.parseFloat(txtAltAgit.getText().toString()),
                            Float.parseFloat(txtDiamAgit.getText().toString()), Float.parseFloat(txtRps.getText().toString())};

                    datos[5] = agitador;
                    float tanque[] = {Float.parseFloat(txtAltProd.getText().toString()),Float.parseFloat(txtEspesor.getText().toString()),
                            Float.parseFloat(txtDiamInter.getText().toString()),Float.parseFloat(txtDiameExt.getText().toString()),
                            0,Float.parseFloat(txtTempChaqueta.getText().toString()),Float.parseFloat(txtConduc.getText().toString()),
                            Float.parseFloat(txtIncrustac.getText().toString())};
                    datos[6] = tanque;

                    datos[8] = Float.parseFloat(txtTempInicialAlimen.getText().toString());
                    datos[9] = Float.parseFloat(txtVolumen.getText().toString());
                    datos[10] = Float.parseFloat(txtEntradaFlui.getText().toString());
                    datos[11] = Float.parseFloat(txtSalidaFlui.getText().toString());
                    datos[12] = Float.parseFloat(txtFlujoMas.getText().toString());

                }catch (Exception e){
                    mensaje("Faltan campos por llenar.");
                    control = true;
                }
                if(!control){
                    datosB.add(datos);
                    Intent intencion = new Intent(contexto,GraficaTanqueActivity.class);
                    intencion.putExtra("datos", datosB);
                    startActivity(intencion);
                }
            }
        });
        //Botón temperaturas calentamieno
        tempCalentamientoButton = (Button)findViewById(R.id.btnTempsCal);
        tempCalentamientoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] temperaturas = new String[10];
                temperaturas[0] = txtTempcal1.getText().toString();
                temperaturas[1] = txtTempcal2.getText().toString();
                temperaturas[2] = txtTempcal3.getText().toString();
                temperaturas[3] = txtTempcal4.getText().toString();
                temperaturas[4] = txtTempcal5.getText().toString();
                temperaturas[5] = txtTempcal6.getText().toString();
                temperaturas[6] = txtTempcal7.getText().toString();
                temperaturas[7] = txtTempcal8.getText().toString();
                temperaturas[8] = txtTempcal9.getText().toString();
                temperaturas[9] = txtTempcal10.getText().toString();
                for (int i = 0; i <temperaturas.length ; i++) {
                    if(temperaturas[i].compareTo("")==0){
                        mensaje("Faltan campos en temperaturas por llenar.");
                        return;
                    }
                    else if(i>0){
                        if (temperaturas[i].compareTo(temperaturas[i-1])<0){
                            mensaje("Temperatura en el campo: "+i+", debe ser menor que temperatura en el campo "+(i+1)+".");
                            return;
                        }
                    }
                }
                for (int i = 0; i <temperaturas.length ; i++) {
                    temperaturasDeCalentamiento.add(Float.parseFloat(temperaturas[i]));
                }
                mensaje("Ingrese las siguientes temperaturas de calentamiento");
                txtTempcal1.setText("");
                txtTempcal2.setText("");
                txtTempcal3.setText("");
                txtTempcal4.setText("");
                txtTempcal5.setText("");
                txtTempcal6.setText("");
                txtTempcal7.setText("");
                txtTempcal8.setText("");
                txtTempcal9.setText("");
                txtTempcal10.setText("");
                txtTempCalenta1.setText((tiempoTempCalenta*60)+"s");
                txtTempCalenta2.setText(""+(tiempoTempCalenta+1)*60+"s");
                txtTempCalenta3.setText(""+(tiempoTempCalenta+2)*60+"s");
                txtTempCalenta4.setText(""+(tiempoTempCalenta+3)*60+"s");
                txtTempCalenta5.setText(""+(tiempoTempCalenta+4)*60+"s");
                txtTempCalenta6.setText(""+(tiempoTempCalenta+5)*60+"s");
                txtTempCalenta7.setText(""+(tiempoTempCalenta+6)*60+"s");
                txtTempCalenta8.setText(""+(tiempoTempCalenta+7)*60+"s");
                txtTempCalenta9.setText(""+(tiempoTempCalenta+8)*60+"s");
                txtTempCalenta10.setText(""+(tiempoTempCalenta+9)*60+"s");
                if(temperaturasDeCalentamiento.size()>40){
                    tempCalentamientoButton.setEnabled(false);
                }
                tiempoTempCalenta += 10;

            }
        });

        //Botón temperaturas enfriamiento
        tempEnfriamientoButton = (Button)findViewById(R.id.btnTempsEnfri);
        tempEnfriamientoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String [] temperaturas = new String[10];
                temperaturas[0] = txtTempEnfri1.getText().toString();
                temperaturas[1] = txtTempEnfri2.getText().toString();
                temperaturas[2] = txtTempEnfri3.getText().toString();
                temperaturas[3] = txtTempEnfri4.getText().toString();
                temperaturas[4] = txtTempEnfri5.getText().toString();
                temperaturas[5] = txtTempEnfri6.getText().toString();
                temperaturas[6] = txtTempEnfri7.getText().toString();
                temperaturas[7] = txtTempEnfri8.getText().toString();
                temperaturas[8] = txtTempEnfri9.getText().toString();
                temperaturas[9] = txtTempEnfri10.getText().toString();
                for (int i = 0; i <temperaturas.length ; i++) {
                    if(temperaturas[i].compareTo("")==0){
                        mensaje("Faltan campos en temperaturas por llenar.");
                        return;
                    }
                    else if(i>0){
                        if (temperaturas[i].compareTo(temperaturas[i-1])>0){
                            mensaje("Temperatura en el campo: "+i+", debe ser mayor que temperatura en el campo "+(i+1)+".");
                            return;
                        }
                    }
                }
                for (int i = 0; i <temperaturas.length ; i++) {
                    temperaturasEnfriamiento.add(Float.parseFloat(temperaturas[i]));
                    Toast.makeText(contexto,temperaturasEnfriamiento.get(i)+"",Toast.LENGTH_LONG);
                }
                mensaje("Ingrese las siguientes temperaturas de calentamiento");
                txtTempEnfri1.setText("");
                txtTempEnfri2.setText("");
                txtTempEnfri3.setText("");
                txtTempEnfri4.setText("");
                txtTempEnfri5.setText("");
                txtTempEnfri6.setText("");
                txtTempEnfri7.setText("");
                txtTempEnfri8.setText("");
                txtTempEnfri9.setText("");
                txtTempEnfri10.setText("");
                txtTempEnfria1.setText(""+(tiempoTempEnfria*60)+"s");
                txtTempEnfria2.setText(""+(tiempoTempEnfria+1)*60+"s");
                txtTempEnfria3.setText(""+(tiempoTempEnfria+2)*60+"s");
                txtTempEnfria4.setText(""+(tiempoTempEnfria+3)*60+"s");
                txtTempEnfria5.setText(""+(tiempoTempEnfria+4)*60+"s");
                txtTempEnfria6.setText(""+(tiempoTempEnfria+5)*60+"s");
                txtTempEnfria7.setText(""+(tiempoTempEnfria+6)*60+"s");
                txtTempEnfria8.setText(""+(tiempoTempEnfria+7)*60+"s");
                txtTempEnfria9.setText(""+(tiempoTempEnfria+8)*60+"s");
                txtTempEnfria10.setText(""+(tiempoTempEnfria+9)*60+"s");
                if(temperaturasEnfriamiento.size()>40){
                    tempEnfriamientoButton.setEnabled(false);
                }
                tiempoTempEnfria+=10;
            }
        });
        agua = (CheckBox)findViewById(R.id.esAgua);
        agua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agua.isChecked()){
                    txtCarb.setEnabled(false);
                    txtGrasa.setEnabled(false);
                    txtCeniza.setEnabled(false);
                    txtFibra.setEnabled(false);
                    txtProtein.setEnabled(false);
                    txtViscosidad.setEnabled(false);
                    txtNomAlim.setText("Agua");
                }
                else{
                    txtCarb.setEnabled(true);
                    txtGrasa.setEnabled(true);
                    txtCeniza.setEnabled(true);
                    txtFibra.setEnabled(true);
                    txtProtein.setEnabled(true);
                    txtViscosidad.setEnabled(true);
                    txtNomAlim.setText("");
                }
            }
        });
    }*/

