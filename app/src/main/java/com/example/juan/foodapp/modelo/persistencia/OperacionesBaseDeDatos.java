
package com.example.juan.foodapp.modelo.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.juan.foodapp.modelo.practicaPlacas.PracticaPlacas;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase auxiliar que implementa a {@link ManejaSQL} para llevar a cabo el CRUD
 * sobre las entidades existentes.
 */

public final class OperacionesBaseDeDatos {
    
    private static ManejaSQL baseDatos;

    private static OperacionesBaseDeDatos instancia = new OperacionesBaseDeDatos();

    private OperacionesBaseDeDatos() {
    }

    public static OperacionesBaseDeDatos obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new ManejaSQL(contexto);
        }
        return instancia;
    }

    /**
     * Metodo que permite insertar un registro de la zona de pasterizacion correspondiente a la practica
     * de pasteurizacion con placas.
     * @param data Pruebas De Datos que se van a guardar.
     */
    public void insertarDatosZonaPasterizacionPlacas(ArrayList<Object> data){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        String nombre = data.get(0).toString();
        String alimento = data.get(1).toString();
        String tempEntradaAlimento = data.get(2).toString();
        String tempEntradaFluidoServicio = data.get(3).toString();
        String caudalEntradaAlimento = data.get(4).toString();
        String coefGlobalTCDiseñoSupuesto = data.get(5).toString();
        String coefIncrustacionAlimento = data.get(6).toString();
        String coefIncrustacionFluidoServicio = data.get(7).toString();

        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_NOMBRE, nombre);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_ALIMENTO, alimento);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_TEMP_ENTRADA_ALIMENTO, tempEntradaAlimento);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_TEMP_ENTRADA_FLUIDO_SERVICIO, tempEntradaFluidoServicio);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_CAUDAL_ENTRADA_ALIMENTO, caudalEntradaAlimento);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_GLOBAL_TC_DISEÑO_ASUMIDO, coefGlobalTCDiseñoSupuesto);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_INCRUSTACION_ALIMENTO, coefIncrustacionAlimento);
        valores.put(ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_INCRUSTACION_FLUIDO_SERVICIO, coefIncrustacionFluidoServicio);
        db.insertOrThrow(ManejaSQL.Tablas.TBL_ZONA_PASTERIZACION_PLACAS, null, valores);
    }

    /**
     * Se recupera un registro de la tabla de zonaPasterizacion correspondiente a la practica de
     * pasteurizacion con placas.
     * @param nombre Registro que se va a recuperar.
     * @return Los datos de entrada para la practica(zona de pasterizacion).
     */
    public ArrayList<Object> obtenerRegistroZonaPasterizacionPlacas(String nombre){
        String consulta = ("SELECT * FROM tbl_zonaPasterizacion_placas WHERE " + ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_NOMBRE
                + "='" + nombre + "'");
        Cursor cursor = obtenerDataDB(consulta);
        NumberFormat format = new DecimalFormat("0E0");
        if(cursor.getCount() == 1){
            ArrayList<Object> data = new ArrayList();
            cursor.moveToFirst();
            data.add(cursor.getString(0));
            data.add(cursor.getString(1));
            data.add(cursor.getString(2));
            data.add(cursor.getString(3));
            data.add(cursor.getString(4));
            data.add(cursor.getString(5));
            data.add(cursor.getString(6));
            data.add(cursor.getString(7));
            return (data);
        }return(null);
    }

    /**
     * Se obtienen todos los registros de practicas de placas (Zona de pasterizacion) que haya
     * guardado el estudiante.
     * @return ArrayList con los nombres de las practicas recuperadas.
     */
    public ArrayList<String> obtenerRegistrosGuardadosZonaPasterizacionPlacas(){
        String consulta = "SELECT * FROM tbl_zonaPasterizacion_placas";
        Cursor registros = obtenerDataDB(consulta);
        ArrayList<String> data = new ArrayList<>();
        if(registros.getCount() != 0){
            if(registros.moveToFirst()){
                do{
                    data.add(registros.getString(0));
                }while (registros.moveToNext());
            }
        }return (data);
    }
    
    /**
     * Método para obtener datos de la base de datos es necesario pasar por parametro
     * la secuencia que indica que datos se deberían retornar.
     */
    private Cursor obtenerDataDB(String sentence) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        return db.rawQuery(sentence, null);
    }
}
