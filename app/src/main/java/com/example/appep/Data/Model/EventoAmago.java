package com.example.appep.Data.Model;

import com.example.appep.R;

public class EventoAmago {

    private String id;
    private Evento evento;
    private double pesoOrglLodo, profVertical, profTotal, presReducidaBomba, desplBomba, presCierreTubo, presCierreRev, gananciaSuperficie, prFractura, prPoro;
    private double estrHastaBroca, estrFondoArrb, estrKop, estrEob, circTotalMatarPozo;

    public EventoAmago(Evento evento) {
        this.evento = evento;
        this.pesoOrglLodo = 0.0;
        this.presCierreTubo = 0.0;
        this.profVertical = 0.000001;
    }

    //Calculations fo this Peso de lodo para matar
    public double calcPesoLodoPaMatar(){
        double res = this.pesoOrglLodo + (this.presCierreTubo/(this.profVertical*0.052));
        this.evento.setPesoLodo(res);
        return res;
    }

    //Calculations fo this Programa de Circulacion Matrix
    public double[][] calcPrgrmCircMtrix(){
        double tablaEstr[][];
        double fcp = presReducidaBomba * (evento.getPesoLodo()/pesoOrglLodo);
        double icp = presReducidaBomba + presCierreTubo;


        if(this.evento.getPozo().isVertical()){
            int size = 11;
            tablaEstr = new double[size][2];

            for(int i=0; i<size; i++){
                tablaEstr[i][0] = estrHastaBroca * (i* 0.1);
                tablaEstr[i][1] = ((fcp-icp)*(0.1*i))+icp;
            }
        }else{
            double kopMd = evento.getInfoHztl()[0];
            double kopVr = evento.getInfoHztl()[1];
            double eobMd = evento.getInfoHztl()[2];
            double eobVr = evento.getInfoHztl()[3];
            int size = 13;
            tablaEstr = new double[size][2];

            double kopCp = icp + ((fcp-presReducidaBomba)* kopMd/profTotal) - (presCierreTubo*kopVr/profVertical);
            double eobCp = icp + ((fcp-presReducidaBomba)* eobMd/profTotal) - (presCierreTubo*eobVr/profVertical);


            for(int i=0; i<size; i++){
                if(i < 5){
                    tablaEstr[i][0] = estrKop * (i* 0.25);
                    tablaEstr[i][1] = ((kopCp - icp) * (0.25*i)) + icp;
                }
                if(i >= 5 && i < 9){
                    tablaEstr[i][0] = ((estrEob - estrKop) * (0.25*(i-4))) + estrKop;
                    tablaEstr[i][1] = ((eobCp - kopCp) * (0.25*(i-4))) + kopCp;
                }
                if(i >= 9){
                    tablaEstr[i][0] = ((estrHastaBroca - estrEob) * (0.25*(i-8))) + estrEob;
                    tablaEstr[i][1] = ((fcp - eobCp) * (0.25*(i-8))) + eobCp;
                }
            }


        }

        this.evento.setTablaEstr(tablaEstr);
        return tablaEstr;
    }

    //Calculations fo this Estroques hasta broca
    public double calcEstroquesABroca(){
        double res = this.evento.getEventoInterno().getVolInterno() / this.desplBomba;
        this.setEstrHastaBroca(res);
        return res;
    }


    //Calculations fo this Estroques fondo Arriba
    public double calcEstroquesFndArriba(){
        double res = this.evento.getEventoAnular().getVolAnular() / this.desplBomba;
        this.setEstrFondoArrb(res);
        return res;
    }

    public double calcEstroquesKOP(){
        double res = this.evento.getEventoInterno().getVolKop() / this.desplBomba;
        this.setEstrKop(res);
        return res;
    }

    public double calcEstroquesEOB(){
        double res = this.evento.getEventoInterno().getVolEob() / this.desplBomba;
        this.setEstrEob(res);
        return res;
    }

    //Calculations fo this Circulacion Total para matar
    public double calcCircTotalPaMatarPozo(){
        double res = this.estrFondoArrb + this.estrHastaBroca;
        this.setCircTotalMatarPozo(res);
        return res;
    }


    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public double getPesoOrglLodo() { return pesoOrglLodo; }
    public void setPesoOrglLodo(double pesoOrglLodo) { this.pesoOrglLodo = pesoOrglLodo; }

    public double getProfVertical() { return profVertical; }
    public void setProfVertical(double profVertical) { this.profVertical = profVertical; }

    public double getProfTotal() { return profTotal; }
    public void setProfTotal(double profTotal) { this.profTotal = profTotal; }



    public double getPresReducidaBomba() { return presReducidaBomba; }
    public void setPresReducidaBomba(double presReducidaBomba) { this.presReducidaBomba = presReducidaBomba; }

    public double getDesplBomba() { return desplBomba; }
    public void setDesplBomba(double desplBomba) { this.desplBomba = desplBomba; }



    public double getPresCierreTubo() { return presCierreTubo; }
    public void setPresCierreTubo(double presCierreTubo) { this.presCierreTubo = presCierreTubo; }

    public double getPresCierreRev() { return presCierreRev; }
    public void setPresCierreRev(double presCierreRev) { this.presCierreRev = presCierreRev; }

    public double getGananciaSuperficie() { return gananciaSuperficie; }
    public void setGananciaSuperficie(double gananciaSuperficie) { this.gananciaSuperficie = gananciaSuperficie; }



    public double getEstrHastaBroca() { return estrHastaBroca; }
    public void setEstrHastaBroca(double estrHastaBroca) { this.estrHastaBroca = estrHastaBroca; }

    public double getEstrFondoArrb() { return estrFondoArrb; }
    public void setEstrFondoArrb(double estrFondoArrb) { this.estrFondoArrb = estrFondoArrb; }

    public double getCircTotalMatarPozo() { return circTotalMatarPozo; }
    public void setCircTotalMatarPozo(double circTotalMatarPozo) { this.circTotalMatarPozo = circTotalMatarPozo; }

    public double getPrFractura() { return prFractura; }
    public void setPrFractura(double prFractura) { this.prFractura = prFractura; }

    public double getPrPoro() { return prPoro; }
    public void setPrPoro(double prPoro) { this.prPoro = prPoro; }

    public double getEstrKop() { return estrKop; }
    public void setEstrKop(double estrKop) { this.estrKop = estrKop; }

    public double getEstrEob() { return estrEob; }
    public void setEstrEob(double estrEob) { this.estrEob = estrEob; }

    //TODO: See if i can aling the error with the textEdit it happened in
    public String amagoEventValidation(){
        double[] properties = {pesoOrglLodo, profVertical, profTotal, presReducidaBomba, desplBomba, presCierreTubo, presCierreRev, gananciaSuperficie, prFractura, prPoro};
        int[] propIds = {R.string.fnl_pol, R.string.fnl_pvv, R.string.fnl_ptm, R.string.fnl_prb, R.string.fnl_dsb, R.string.fnl_pctp, R.string.fnl_pcrv, R.string.fnl_gnsp, R.string.fnl_prfr, R.string.fnl_prpr};

        for (int i=0; i < properties.length; i++) {
            if(properties[i] <= 0.000001){
                return "No se permiten valores negativos, vacio o cero";
            }
        }

        /*
        if(prFractura < prPoro){
            return "La presion de fractura debe ser mayor a la presion de poro";
        }

        if(!(prFractura > evento.getPesoLodo() && prPoro < evento.getPesoLodo())){
            return "Los resultados no concuerdan con las presiones de fractura y poro";
        }
        */

        return "OK";
    }
}
