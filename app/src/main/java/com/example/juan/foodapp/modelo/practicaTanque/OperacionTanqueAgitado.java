package com.example.juan.foodapp.modelo.practicaTanque;

public class OperacionTanqueAgitado {


    public double nusChCalentamiento(double mldt){
        return (0.15*Math.pow(1.9087,0.33)*(Math.pow(((965.6666*9.8*0.0006*mldt)/(Math.pow(0.0003,2))),0.33)));
    }

    public double nusChEnfriamiento(double mldt, double viscosidadServicio, double densidadServicio,
                                    double betaServicio, double cpServicio, double conductividadServicio){
        //0.15*Math.pow((4177*0.0008/0.611),0.33)*(Math.pow(((994*9.8*0.0002*64.57)/(Math.pow(0.0008,2))),0.33))
        double parte1 = Math.pow((cpServicio*viscosidadServicio/conductividadServicio),0.33);

        return (0.15*parte1*(Math.pow(((densidadServicio*9.8*betaServicio*mldt)/((Math.pow(viscosidadServicio,2)))),0.33)));
    }

    //depende de nusCh
    public double hch(double conductividad,double nusCh,double diametroInternoTanque, double diametroExternoTanque){
        return nusCh*conductividad/(diametroExternoTanque-diametroInternoTanque);
    }

    public double Prf(double viscocidadAlimento, double cpAlimento, double conductividadAlimento){
        return (cpAlimento*viscocidadAlimento)/conductividadAlimento;
    }

    public double Re(double densidadAlimento, double viscocidadAlimento, double diametroAgitador, double velocidaAgitador){
        double parte1=velocidaAgitador*densidadAlimento;
        double parte2=(diametroAgitador*diametroAgitador);

        return (parte1*parte2)/viscocidadAlimento;
    }

    //depende de Re y Prf
    public double nusF(double Prf, double Re,double aAgitador, double bAgitador,double mAgitador){
        return aAgitador*(Math.pow(Re,bAgitador))*(Math.pow(Prf,0.3333))*(Math.pow(1,mAgitador));
    }

    //depende de nusF
    public double ht(double nusF, double conductividadAlimento,double diametroInternoTanque){
        return nusF*conductividadAlimento/diametroInternoTanque;
    }

    //depende de ht y ch
    public double ut(double espesorTanque, double conductividadTanque,double factorIncrustacionTanque,double ht,double hch){
        return 1/((1/hch)+(espesorTanque/conductividadTanque)+(1/(ht))+factorIncrustacionTanque);
    }


    /*
    *ECUACIÓN 5 DE LA GUÍA DE TANQUE AGITADO:
    * Calcula MLTD: es la temperatura media logarítmica (°C)
    * Variables:
     *  Tce: la temperatura de entrada del fluido caliente (temperatura chaqueta)
    *  Tfe: la temperatura de entrada del fluido frio (temperatura inicial alimento)
    *  Tfs: la temperatura de salida del fluido frio (temperatura experimental)
    *  Tcs: la temperatura de salida del fluido caliente
    */
    public double calcularMLDTEnfriamiento(double temperaturaInicialAlimentoEnfriamiento,double temperaturaExperimental, double temperaturaFluidoSalidaFrio, double temperturaFluidoEntradaFrio){
        //se dividió en partes la ecuación por efectos de mantenibilidad
        double MLTD;
        double parte1;
        double parte2;

        parte1 = (temperaturaInicialAlimentoEnfriamiento-temperaturaFluidoSalidaFrio)-(temperaturaExperimental-temperturaFluidoEntradaFrio);
        parte2= (Math.log((temperaturaInicialAlimentoEnfriamiento-temperaturaFluidoSalidaFrio)/(temperaturaExperimental-temperturaFluidoEntradaFrio)));
        MLTD = parte1/parte2;

        return MLTD;
    }

    /*
    *ECUACIÓN 5 DE LA GUÍA DE TANQUE AGITADO:
    * Calcula MLTD: es la temperatura media logarítmica (°C)
    * Variables:
     *  Tce: la temperatura de entrada del fluido caliente (temperatura chaqueta)
    *  Tfe: la temperatura de entrada del fluido frio (temperatura inicial alimento)
    *  Tfs: ¿ (temperatura experimental)
    *  Tcs: la temperatura de salida del fluido caliente
    */
    public double calcularMLDTCalentamiento(double temperaturaInicialAlimento, double temperaturaExperimental, double temperaturaChaqueta){
        //se dividió en partes la ecuación por efectos de mantenibilidad
        double MLTD;
        double parte1;
        double parte2;

        parte1 = (temperaturaInicialAlimento-temperaturaExperimental);
        parte2= (Math.log((temperaturaChaqueta-temperaturaExperimental)/(temperaturaChaqueta-temperaturaInicialAlimento)));
        MLTD = parte1/parte2;
        return MLTD;
    }


    public double calcularTiempoEstimadCalentamiento(double volumenAlimento, double densidadAlimento,double alimentoTempInicial, double tanqueTempChaqueta, double tempI, float areaTanque, double cp, double ut) {
        double masa = (volumenAlimento/1000)*densidadAlimento;
        double log = (tanqueTempChaqueta-alimentoTempInicial)/(tanqueTempChaqueta-tempI);
        return ((masa*cp)/(ut*areaTanque)*Math.log(log));
    }

    public double calculark2(double servicioEnfriamientoCalorEspecifico, double servicioEnfriamientoFlujoMasico, double tanqueArea, double ut) {
        double exp = ut*tanqueArea/(servicioEnfriamientoFlujoMasico*servicioEnfriamientoCalorEspecifico);
        return Math.pow(Math.E,exp);
    }

    public double calcularTiempoEstimadEnfriamiento(double alimentoVolumen, double densidad, double tempI, double cp, double calorEspecificoServicio, float tempEntradaServicio, float tempInicial, float flujoMasico, double k2) {
        double masa = (alimentoVolumen/1000)*densidad;
        double parte1 = (masa*cp/(flujoMasico*calorEspecificoServicio));
        double parte2 = k2/(k2-1);
        double log = (tempInicial-tempEntradaServicio)/(tempI-tempEntradaServicio);

        return parte1*parte2*(Math.log(log));
    }
}
