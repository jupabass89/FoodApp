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
import com.example.juan.foodapp.modelo.persistencia.OperacionesBaseDeDatos;
import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;
import com.example.juan.foodapp.modelo.serviciosPractica.Guia;

import java.util.ArrayList;

public class ControlPlacasPrincipal extends AppCompatActivity {

    private PracticaPlacas practica;
    private OperacionesBaseDeDatos operacionesDB;
    private Context contexto;

    private String asignatura;
    private String profesor;

    //Campos de texto de la vista
    EditText alimento;
    EditText tempEntradaAlimento;
    EditText caudalEntradaAlimento;
    EditText coefIncrustacionAlimento;
    EditText tempEntradaFluidoServicio;
    EditText coefIncrustacionFluidoServicio;
    EditText coefDiseñoSupuesto;
    EditText entradaSalvar;
    EditText entradaInforme;

    private Object[] datosActuales;
    private Object[] ultimosDatos;
    private ArrayList<String> practicasGuardadas;
    private static String practicaSeleccionada;
    private Button boton;
    private boolean calculosRealizados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_vista_placas_principal);
        contexto = this.getApplicationContext();
        calculosRealizados = false;
        operacionesDB = OperacionesBaseDeDatos.obtenerInstancia(getApplicationContext());
        Bundle datosBundle = getIntent().getExtras();
        asignatura = datosBundle.getString("Asignatura");
        profesor = datosBundle.getString("Profesor");
        practica = new PracticaPlacas(contexto, asignatura, profesor);
        datosActuales = new Object[6];
        ultimosDatos = new Object[6];

        //Muestra barra de acción
        ActionBar ab = getSupportActionBar();

        //Muestra logo
        ab.setLogo(R.mipmap.ic_icon);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        //Asociación de la captura de datos con los campos de la vista
        alimento = (EditText)findViewById(R.id.campoNombreAlimento);
        tempEntradaAlimento = (EditText)findViewById(R.id.entrada1);
        caudalEntradaAlimento = (EditText)findViewById(R.id.entrada2);
        coefIncrustacionAlimento = (EditText)findViewById(R.id.entrada3);
        tempEntradaFluidoServicio = (EditText)findViewById(R.id.entrada4);
        coefIncrustacionFluidoServicio = (EditText)findViewById(R.id.entrada5);
        coefDiseñoSupuesto = (EditText)findViewById(R.id.entrada6);
        entradaSalvar = (EditText)findViewById(R.id.entradaSalvar);
        entradaInforme = (EditText)findViewById(R.id.entradaInforme);

        boton = (Button)findViewById(R.id.btnRecuperar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                practicasGuardadas = operacionesDB.obtenerRegistrosGuardadosZonaPasterizacionPlacas();
                Intent intencion = new Intent(contexto, PracticasGuardadasPlacas.class);
                intencion.putExtra("datos", practicasGuardadas);
                startActivity(intencion);
            }
        });

        boton = (Button)findViewById(R.id.btnSalvarDatos);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entradaSalvar.getText().toString().equals("")){
                    mostarMensaje("Debe ingresar un nombre identificador para guardar los datos");
                }else{
                    if (realizarCalculos()){
                        if (validarNombreSalvarPractica(entradaSalvar.getText().toString())) persistirDatos();
                        else  mostarMensaje("Este nombre ya se uso para guardar un conjunto de datos");
                    }
                }
            }
        });

        boton = (Button)findViewById(R.id.btnGrafica);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(realizarCalculos()){
                    Intent intencion = new Intent(contexto, GraficaPlacasActivity.class);
                    ArrayList<Object> listas = practica.calcularDatosGrafica(datosActuales);
                    intencion.putExtra("datos", listas);
                    startActivity(intencion);
                }
            }
        });

        boton = (Button)findViewById(R.id.btnTabla);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(realizarCalculos()) {
                    String[][] datos = practica.getResultados();
                    Intent intencion = new Intent(contexto, TablaResultados.class);
                    intencion.putExtra("datos", TablaResultados.tableToArray(datos));
                    startActivity(intencion);
                }else
                    mostarMensaje("No se pueden mostrar los resultados");
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
         if(practicaSeleccionada != null){
            setearDatosEnVista(operacionesDB.obtenerRegistroZonaPasterizacionPlacas(practicaSeleccionada));
             calculosRealizados = false;
         }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.generaInforme):
                if(realizarCalculos()){
                    String nombreArchivo = entradaInforme.getText().toString();
                    if(!nombreArchivo.equals("")){
                        practica.generarInforme(nombreArchivo);
                        mostarMensaje("Informe generado");
                        mostarMensaje("RUTA: Descargas/Practicas");
                    }else{
                        mostarMensaje("Debe agregar un nombre para el archivo en el campo correspondiente");
                    }
                }
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
        boolean entrada1V = !tempEntradaAlimento.getText().toString().equals("");
        boolean entrada2V = !caudalEntradaAlimento.getText().toString().equals("");
        boolean entrada3V = !coefIncrustacionAlimento.getText().toString().equals("");
        boolean entrada4V = !tempEntradaFluidoServicio.getText().toString().equals("");
        boolean entrada5V = !coefIncrustacionFluidoServicio.getText().toString().equals("");
        boolean entrada6V = !coefDiseñoSupuesto.getText().toString().equals("");
        return (entrada1V && entrada2V && entrada3V && entrada4V && entrada5V && entrada6V);
    }

    private boolean realizarCalculos(){
        if(!camposCompletos()){
            mostarMensaje("Debe llenar todos los campos para la practica");
            return (false);
        }
        configurarDatosPractica();
        if(validarCambiosEnDatosDeEntrada()){
            practica.configurarPractica(datosActuales);
            practica.calcularDatosZonaPasterizacion();
            practica.guardarResultados();
            ultimosDatos = datosActuales.clone();
            calculosRealizados = true;
            return (true);
        }
        return (calculosRealizados);
    }

    private void mostarMensaje(String texto){
        Toast.makeText(this, texto,Toast.LENGTH_LONG).show();
    }

    private void configurarDatosPractica(){
        datosActuales[0] = tempEntradaAlimento.getText().toString();
        datosActuales[1] = caudalEntradaAlimento.getText().toString();
        datosActuales[2] = coefIncrustacionAlimento.getText().toString();
        datosActuales[3] = tempEntradaFluidoServicio.getText().toString();
        datosActuales[4] = coefIncrustacionFluidoServicio.getText().toString();
        datosActuales[5] = coefDiseñoSupuesto.getText().toString();
    }

    /**
     * Se ejecuta el metodo que permite guardar en la BD un connjunto de datos ingresados.
     */
    private void persistirDatos(){
        ArrayList<Object> data = new ArrayList<>();
        data.add(entradaSalvar.getText().toString());
        data.add(alimento.getText().toString());
        data.add(tempEntradaAlimento.getText().toString());
        data.add(caudalEntradaAlimento.getText().toString());
        data.add(coefIncrustacionAlimento.getText().toString());
        data.add(tempEntradaFluidoServicio.getText().toString());
        data.add(coefIncrustacionFluidoServicio.getText().toString());
        data.add(coefDiseñoSupuesto.getText().toString());
        operacionesDB.insertarDatosZonaPasterizacionPlacas(data);
        mostarMensaje("Se guardaron correctamente los datos");
    }

    /**
     * Se setean en la vista un conjunto de datos recuperados de la BD.
     * @param data Datos recuperados.
     */
    private void setearDatosEnVista(ArrayList<Object> data){
        alimento.setText(data.get(1).toString());
        tempEntradaAlimento.setText(data.get(2).toString());
        caudalEntradaAlimento.setText(data.get(3).toString());
        coefIncrustacionAlimento.setText(data.get(4).toString());
        tempEntradaFluidoServicio.setText(data.get(5).toString());
        coefIncrustacionFluidoServicio.setText(data.get(6).toString());
        coefDiseñoSupuesto.setText(data.get(7).toString());
    }

    /**
     * Se comprueba que al guardar los datos de una practica, el nombre ingresado como identificador no este ya
     * asociado a un registro.
     * @return True si el nombre se puede usar, False de lo contrario.
     */
    private boolean validarNombreSalvarPractica(String entrada){
        practicasGuardadas = operacionesDB.obtenerRegistrosGuardadosZonaPasterizacionPlacas();
        for(String nombrePractica : practicasGuardadas){
            if(nombrePractica.compareToIgnoreCase(entrada) == 0) return (false);
        }return (true);
    }

    /**
     * Se valida si se han variado datos de la practica en la vista.
     * @return True si vario algun dato, False de lo contrario.
     */
    private boolean validarCambiosEnDatosDeEntrada(){
        for(int i=0; i<datosActuales.length; i++){
            if(ultimosDatos[i] == null) return (true);
            if(Float.compare(Float.parseFloat(datosActuales[i].toString()),
                    Float.parseFloat(ultimosDatos[i].toString())) != 0)
                return (true);
        }return (false);
    }

    public static void setPracticaSeleccionada(String seleccion){
        practicaSeleccionada = seleccion;
    }
}
