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
        String TBL_ZONA_PASTERIZACION_PLACAS = "tbl_zonaPasterizacion_placas";
    }
    
    @Override
    public void onCreate(SQLiteDatabase db){
        String s;

        // ZONA_PASTERIZACION
        s = String.format("CREATE TABLE %s ( %s VARCHAR(25) ," +
                        "%s VARCHAR(25),%s VARCHAR(25),%s VARCHAR(25),%s VARCHAR(25),%s VARCHAR(25),%s VARCHAR(25),%s VARCHAR(25) )",
                Tablas.TBL_ZONA_PASTERIZACION_PLACAS, ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_NOMBRE, ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_ALIMENTO,
                ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_TEMP_ENTRADA_ALIMENTO,
                ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_TEMP_ENTRADA_FLUIDO_SERVICIO, ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_CAUDAL_ENTRADA_ALIMENTO,
                ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_GLOBAL_TC_DISEÑO_ASUMIDO,
                ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_INCRUSTACION_ALIMENTO, ContratoPracticas.ZonaPasterizacionPlacas.PLACAS_PAST_COEF_INCRUSTACION_FLUIDO_SERVICIO);
        db.execSQL(s);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.TBL_ZONA_PASTERIZACION_PLACAS);

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
