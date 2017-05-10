
package com.example.juan.foodapp.modelo.practicaTanque;

public class OperacionTanqueAgitado {




    /*
    * ECUACIÓN 2 DE LA GUÍA DE TANQUE AGITADO:
    * calcula ht: coeficiente individual de transferencia de calor al interior del tanque
    * Variables:
    * Dti: el diámetro interno del tanque
    * k: es la conductividad térmica del alimento (W/m °C)
    * a:    parámetro del agitador  // **especificar nombres
    * b:    parámetro del agitador  // **especificar nombres
    * m:    parámetro del agitador  // **especificar nombres
    * Da: es el diámetro del agitador (m)
    * p: ρ es la densidad del alimento (kg/m3
    * N: N es la velocidad de giro del agitador (rps)
    * M: μ es la viscosidad del alimento en el centro
    * Mw: (μw) es la viscosidad del alimento en la pared del tanque
    * cp: es el calor específico del alimento (J/kg°C)
    */
    public float calcularCoeficienteIndividualTransferenciaCalorInteriorTanque (float Dti, float k, float a, float b, float m, float Da, float p, float N, float M, float Mw, float cp ){
        //se dividió en partes la ecuación por efectos de mantenibilidad
        double parte1;
        double parte2;
        double parte3;
        float ht;

        parte1=  a*Math.pow(((Math.pow(Da,2)*N*p)/M),b);
        parte2 =  Math.pow(((cp*M)/k), (1/3));
        parte3 = Math.pow((M/Mw),m);

        ht=(float)(((parte1*parte2*parte3)*k)/Dti);

        return ht;
    }

    /*
    * ECUACIÓN 3 DE LA GUÍA DE TANQUE AGITADO
    * Calcula hch : coeficiente de transferencia de calor del fluido al interior del la chaqueta
    *
    * Variables:
    *  Dch: es el diámetro interno de la chaqueta
    *  Dto: es el diámetro externo del tanque
    *  Dti:el diámetro interno del tanque
    *  Tce: la temperatura de entrada del fluido caliente (temperatura chaqueta)
    *  Tfe: la temperatura de entrada del fluido frio (temperatura inicial alimento)
    *  Tfs: la temperatura de salida del fluido frio (temperatura experimental)
    *  Tcs: la temperatura de salida del fluido caliente
    *  cp: es el calor específico del fluido en chaqueta(J/kg°C)
    *  M: (μ) es la viscosidad del fluido en el centro del tanque
    *  k: es la conductividad térmica del fluido calefactor (W/m °C)
    *  p: (ρ) es la densidad del fluido en chaqueta(kg/m3)
    *  g: es la gravedad específica en chaqueta(m/s2
    *  B: es el coeficiente de expansión térmica del fluido en chaqueta(1/°C)
    */
    public float calcularCoeficienteIndividualTransferenciaCalorChaqueta(float Dch, float Dto, float Dti, float Tce, float Tfe, float Tfs, float Tcs, float cp, float M, float k, float p, float g, float B ){
        //se dividió en partes la ecuación por efectos de mantenibilidad
        float hch;
        double parte1;
        double parte2;
        float Dequ = calcularDequ(Dch, Dto, Dti);
        float MLDT = calcularMLDT(Tce, Tfs, Tcs, Tfe);

        parte1= 0.15*(Math.pow(((cp*M)/k),(1/3)));
        parte2= Math.pow((((Math.pow(p,2))* (Math.pow(Dequ,3))*g*B*MLDT)/(Math.pow(M,2))),0.33);

        hch= (float)((parte1*parte2*k)/Dequ);
        return hch;
    }

    /*
    *ECUACIÓN 4 DE LA GUÍA DE TANQUE AGITADO:
    * Calcula Dequ:
    * Variables:
    *  Dch: es el diámetro interno de la chaqueta
    *  Dto: es el diámetro externo del tanque
    *  Dti:el diámetro interno del tanque
    */
    private float calcularDequ(float Dch, float Dto, float Dti){
        float Dequ;
        Dequ = (float)((Math.pow(Dch,2) - Math.pow(Dto,2))/Dto);        //**rectificar!!
        return Dequ;
    }

    /*
    *ECUACIÓN 5 DE LA GUÍA DE TANQUE AGITADO:
    * Calcula MLTD: es la temperatura media logarítmica (°C)
    * Variables:
    *  Tce: la temperatura de entrada del fluido caliente
    *  Tfe: la temperatura de entrada del fluido frio
    *  Tfs: la temperatura de salida del fluido frio
    *  Tcs: la temperatura de salida del fluido caliente
    */
    private float calcularMLDT(float Tce, float Tfs, float Tcs, float Tfe){
        //se dividió en partes la ecuación por efectos de mantenibilidad
        float MLTD;
        float parte1;
        float parte2;

        parte1 = (Tce-Tfs)-(Tcs-Tfe);
        parte2= (float)(Math.log((Tce-Tfs)/(Tcs-Tfe)));
        MLTD = parte1/parte2;

        return MLTD;
    }

    /*
     * Calula el Uc (Ut).
     * coeficienteIndividual es ht.
     *conductividadParedTanque es conductividad del material al interior del tanque.
     * se necesita haber calculado previamente coeficienteTransfereciainteriorTanque y coeficienteIndividual
     */
    public float calcularCoeficienteGlobalTrasnferenciaDeCalor(float conductividadTanque, float coeficienteIndividual,
            float diametroInternoTanque, float diametroExternoTanque, float factorObstruccionPorIncustracion,
                                                               float hch){
        double exponente = Math.pow((diametroInternoTanque/diametroExternoTanque),2);
        float Ut= (1/coeficienteIndividual)
                    +((diametroInternoTanque/(diametroExternoTanque*conductividadTanque))*(diametroExternoTanque-diametroInternoTanque))
                    +((float)exponente*(1/hch))+factorObstruccionPorIncustracion;

        return (1/Ut);
    }

    /*
     * este metodo corresponde a la ecuacion nro 7 de tanque agitado.
     * falta parametros de temperatura de entrada y salida del alimento a procesar(igual que la ecuacion de abajo)
     * se necesita haber calculado previamiente ut y areaTransferenciaDeCalor;
     */

    /**
     *
     * @param volumenAlimento - en litros
     * @param densidadAlimento - en kilogramos
     * @param calorEspeficicoAlimiento
     * @param diametroInternoTanque
     * @param temperaturaInicialLiquidoCalefactor
     * @param temperaturaEntradaAlimento
     * @param temperaturaSalidaAlimento
     * @param coeficienteGlobalTransferenciaDeCalor
     * @return
     */
    public float calcularTimepoEstimadoCalentamiento(float volumenAlimento,float densidadAlimento,float calorEspeficicoAlimiento,
                                                     float diametroInternoTanque, float temperaturaInicialLiquidoCalefactor,
                                                     float temperaturaEntradaAlimento,
                                                     float temperaturaSalidaAlimento, float coeficienteGlobalTransferenciaDeCalor){
        double log = (temperaturaInicialLiquidoCalefactor-temperaturaEntradaAlimento)/(temperaturaInicialLiquidoCalefactor-temperaturaSalidaAlimento);
        double area = 2*Math.PI*(Math.pow((diametroInternoTanque/2),2));
        float masaAlimento = (volumenAlimento/1000)*(densidadAlimento);//volumen en litros
        return (masaAlimento*calorEspeficicoAlimiento)/(coeficienteGlobalTransferenciaDeCalor*((float)area))*((float)Math.log(log));
    }

    /*
    * este metodo corresponde a la ecuación nro 8 de tanque agitado.
    * faltan parametros de temperatura de entrada y salida del alimento(existe duda en esos parametros).
    * fata metodo para calcular K sub 2, de la misma ecuacion (hay duda en el significado de Uf).
    * falta hacer el calculo y retornarlo.
    * se necesita haber calculado previamiente ut y areaTransferenciaDeCalor;
    */
    public float calcularTiempoEstimadoEnfriamiento(float volumenAlimento,float densidadAlimento, float calorEspecificoAlimento,
                                                    float calorEspeficicoRefrigerante, float flujoMasicoRefrigerante,
                                                    float areaTransferenciaDeCalor, float temperaturaInicialFluidoRefrigerante,
                                                    float temperaturaDeEntradaAlimento, float temperaturaSalidaAlimento, float uf){
        double log = (temperaturaDeEntradaAlimento-temperaturaInicialFluidoRefrigerante)/(temperaturaSalidaAlimento-temperaturaInicialFluidoRefrigerante);
        float k2 = (kSub2(uf,areaTransferenciaDeCalor,flujoMasicoRefrigerante,calorEspecificoAlimento));

        return (((volumenAlimento/1000)*densidadAlimento)*calorEspecificoAlimento)/(flujoMasicoRefrigerante*calorEspeficicoRefrigerante)*(k2/(k2-1))*
                (float)(Math.log(log));
    }

    private float kSub2(float uf,float areaTransferenciaDeCalor, float flujoMasicoRefrigerante, float calorEspecificoAlimento){
        return (float)Math.pow(Math.E, uf*areaTransferenciaDeCalor)/(flujoMasicoRefrigerante*calorEspecificoAlimento);
    }
}
