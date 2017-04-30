package com.example.juan.foodapp.modelo.persistencia;

import java.util.UUID;

/**
 * CLASE ENCARGADA DE GUARDAR LOS METADATOS CORRESPONDIENTEAS A LAS TABLAS DE LA DB
 */
public class ContratoPracticas {
    
    interface ColumnasRegeneracionPlacas{
        String REG_P_NOMBRE = "reg_p_nombre";
        String REG_P_TEMP_ALIMENTO_ENTRADA = "reg_p_temp_alimento_entrada";
        String REG_P_TEMP_ALIMENTO_SALIDA = "reg_p_temp_alimento_salida";
        String REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA = "reg_p_temp_fluido_de_servicio_entrada";
        String REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA = "reg_p_temp_fluido_de_servicio_salida";
    }
    
    interface ColumnasPasteurizacionPlacas{
        String REG_P_NOMBRE = "reg_p_nombre";
        String REG_P_TEMP_ALIMENTO_ENTRADA = "reg_p_temp_alimento_entrada";
        String REG_P_TEMP_ALIMENTO_SALIDA = "reg_p_temp_alimento_salida";
        String REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA = "reg_p_temp_fluido_de_servicio_entrada";
        String REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA = "reg_p_temp_fluido_de_servicio_salida";
    }
    
    interface ColumnasEnfriamientoPlacas{
        String REG_P_NOMBRE = "reg_p_nombre";
        String REG_P_TEMP_ALIMENTO_ENTRADA = "reg_p_temp_alimento_entrada";
        String REG_P_TEMP_ALIMENTO_SALIDA = "reg_p_temp_alimento_salida";
        String REG_P_TEMP_FLUIDO_DE_SERVICIO_ENTRADA = "reg_p_temp_fluido_de_servicio_entrada";
        String REG_P_TEMP_FLUIDO_DE_SERVICIOS_ENTRADA = "reg_p_temp_fluido_de_servicio_salida";
    }
    
    public static class RegeneracionPlacas implements ColumnasRegeneracionPlacas{}

    public static class PasteurizacionPlacas implements ColumnasPasteurizacionPlacas{}

    public static class EnfriamientoPlacas implements ColumnasEnfriamientoPlacas{}
    
    private ContratoPracticas(){}
}
