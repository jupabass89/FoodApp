package com.example.juan.foodapp.modelo.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * CLASE ENCARGADA DE LA CREACION DE LA BD
 */

public class ManejaSQL extends SQLiteOpenHelper {
    
    private static final String NOMBRE_BASE_DATOS = "practicas.db";
    private static final int VERSION_ACTUAL = 1;
    private final Context contexto;

    public ManejaSQL(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    interface Tablas{
        String TBL_REGENERACION_PLACAS = "tbl_regeneracion_placas";
        String TBL_PASTEURIZACION_PLACAS = "tbl_pasteurizacion_placas";
        String TBL_ENFRIAMIENTO_PLACAS = "tbl_enfriamiento_placas";
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        String s;
        
        // REGENERACION
        s = String.format("CREATE TABLE %s ( %s VARCHAR(25) ," +
                        "%s FLOAT, %s FLOAT, %s FLOAT, %s FLOAT )",
                Tablas.TBL_REGENERACION_PLACAS, ContratoPracticas.RegeneracionPlacas.REG_P_NOMBRE, ContratoPracticas.RegeneracionPlacas.REG_P_TEMP_ALIMENTO_ENTRADA,
                ContratoPracticas.RegeneracionPlacas.REG_P_TEMP_ALIMENTO_SALIDA, ContratoPracticas.RegeneracionPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA,
                ContratoPracticas.RegeneracionPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA);
        db.execSQL(s);

        // PASTEURIZACION
        s = String.format("CREATE TABLE %s ( %s VARCHAR(25) ," +
                        "%s FLOAT, %s FLOAT, %s FLOAT, %s FLOAT )",
                Tablas.TBL_PASTEURIZACION_PLACAS, ContratoPracticas.PasteurizacionPlacas.REG_P_NOMBRE, ContratoPracticas.PasteurizacionPlacas.REG_P_TEMP_ALIMENTO_ENTRADA,
                ContratoPracticas.PasteurizacionPlacas.REG_P_TEMP_ALIMENTO_SALIDA, ContratoPracticas.PasteurizacionPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA,
                ContratoPracticas.PasteurizacionPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA);
        db.execSQL(s);
        
        // REGENERACION
        s = String.format("CREATE TABLE %s ( %s VARCHAR(25) ," +
                        "%s FLOAT, %s FLOAT, %s FLOAT, %s FLOAT )",
                Tablas.TBL_ENFRIAMIENTO_PLACAS, ContratoPracticas.EnfriamientoPlacas.REG_P_NOMBRE, ContratoPracticas.EnfriamientoPlacas.REG_P_TEMP_ALIMENTO_ENTRADA,
                ContratoPracticas.EnfriamientoPlacas.REG_P_TEMP_ALIMENTO_SALIDA, ContratoPracticas.EnfriamientoPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA,
                ContratoPracticas.EnfriamientoPlacas.REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA);
        db.execSQL(s);     
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_REGENERACION_PLACAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_PASTEURIZACION_PLACAS);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_ENFRIAMIENTO_PLACAS);

        onCreate(db);
    }
    
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }
}
