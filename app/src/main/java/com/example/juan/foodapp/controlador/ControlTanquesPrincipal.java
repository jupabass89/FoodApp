package com.example.juan.foodapp.controlador;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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
import java.util.StringTokenizer;

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

    private InputMethodManager teclado;

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
        datos = new Object[14];
        datosB = new ArrayList<>();

        //configurando metodo de entrada del teclado
        teclado = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
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
                int action = v.getId();
                if (action == tempCalentamiento.getId()) {
                    ingresarTemperaturasCalentamiento(null);
               } else {
                    if (action == tempEnfriamiento.getId()) {
                        ingresarTemperaturasEnfriamiento(null);
                    }
               }
            }
            return false;
        }
    }

    public void ingresarTemperaturasCalentamiento(View view) {
        String t = tempCalentamiento.getText().toString();
        float f = Float.parseFloat(t);
        if (f < 78f) {
            if (temperaturasDeCalentamiento.isEmpty()) {
                temperaturasDeCalentamiento.add(f);
                contadorTiemposCalentamiento=contadorTiemposCalentamiento+intervaloTiempo;
                tiempoCalentamiento.setText(contadorTiemposCalentamiento + " s");
                tempCalentamiento.setText("");
                mensaje("Ingrese la siguiente temperatura");
            } else {
                float last = temperaturasDeCalentamiento.get(temperaturasDeCalentamiento.size()-1);
                if (f > last) {
                    temperaturasDeCalentamiento.add(f);
                    contadorTiemposCalentamiento=contadorTiemposCalentamiento+intervaloTiempo;
                    tiempoCalentamiento.setText(contadorTiemposCalentamiento + " s");
                    tempCalentamiento.setText("");
                    mensaje("Ingrese la siguiente temperatura");
                } else {
                    mensaje("Debe ingresar una temperatura mayor a la anterior");
                }
            }
        }else{
                mensaje("Ha superado la temperatura de calentamiento límite");
            }
        }

    public void ingresarTemperaturasEnfriamiento(View view) {
        String t = tempEnfriamiento.getText().toString();
        float f = Float.parseFloat(t);
        if (f > 0f && f<78f) {
            if (temperaturasEnfriamiento.isEmpty()) {
                temperaturasEnfriamiento.add(f);
                tempEnfriamiento.setText("");
                contadorTiemposEnfriamiento=contadorTiemposEnfriamiento+intervaloTiempo;
                tiempoEnfriamiento.setText(contadorTiemposEnfriamiento + " s");
                mensaje("Ingrese la siguiente temperatura");
            } else {
                float last = temperaturasEnfriamiento.get(temperaturasEnfriamiento.size()-1);
                if (f < last) {
                    temperaturasEnfriamiento.add(f);
                    contadorTiemposEnfriamiento++;
                    tiempoEnfriamiento.setText((contadorTiemposEnfriamiento * intervaloTiempo) + " s");
                    tempEnfriamiento.setText("");
                    mensaje("Ingrese la siguiente temperatura");
                } else {
                    mensaje("Debe ingresar una temperatura menor a la anterior");
                }
            }
        }else{
            mensaje("Ha superado la temperatura de enfriamiento límite");
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
            datos[13]=intervaloTiempo;

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


