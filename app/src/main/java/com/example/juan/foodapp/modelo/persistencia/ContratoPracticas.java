package com.example.juan.foodapp.modelo.persistencia;

import java.util.UUID;

/**
 * CLASE ENCARGADA DE GUARDAR LOS METADATOS CORRESPONDIENTEAS A LAS TABLAS DE LA DB
 */
public class ContratoPracticas {

    interface ColumnasZonaPasterizacionPlacas{
        String PLACAS_PAST_NOMBRE = "placas_past_nombre";
        String PLACAS_PAST_ALIMENTO = "placas_past_alimento";
        String PLACAS_PAST_TEMP_ENTRADA_ALIMENTO = "placas_past_temp_entrada_alimento";
        String PLACAS_PAST_TEMP_ENTRADA_FLUIDO_SERVICIO = "placas_past_temp_entrada_fluido_servicio";
        String PLACAS_PAST_CAUDAL_ENTRADA_ALIMENTO = "placas_past_caudal_entrada_alimento";
        String PLACAS_PAST_COEF_GLOBAL_TC_DISEÑO_ASUMIDO = "placas_past_coef_global_tc_diseño_asumido";
        String PLACAS_PAST_COEF_INCRUSTACION_ALIMENTO = "placas_past_coef_incrustacion_alimento";
        String PLACAS_PAST_COEF_INCRUSTACION_FLUIDO_SERVICIO = "placas_past_coef_incrustacion_fluido_servicio";
    }

    public static class ZonaPasterizacionPlacas implements ColumnasZonaPasterizacionPlacas{}
    
    private ContratoPracticas(){}
}
