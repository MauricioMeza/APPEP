package com.example.appep.Data.Model;

import com.example.appep.R;

import java.util.Date;

public class Evento {
    private int id;
    private Pozo pozo;
    private Date fechaCreacion;
    private double pesoLodo;
    private double tablaEstr[][];
    private double volTotal, longTotal;
    private EventoInterno eventoInterno;
    private EventoAnular eventoAnular;
    private EventoAmago eventoAmago;

    public Evento(boolean[] arregloComponentes){
        this.fechaCreacion = new Date();
        this.eventoInterno = new EventoInterno(this);
        this.eventoAnular = new EventoAnular(this);
        this.eventoAmago = new EventoAmago(this);

        //TODO: Arreglar este machetazo (crear objetos para el tipo de componente???)
        this.eventoInterno.setBroca(new Componente(R.drawable.f_broca,"Broca", this));
        this.eventoAnular.setHueco(new Componente(R.drawable.f_hueto, "Hueco", this));

        if(arregloComponentes[0]){
            this.eventoAnular.setRevestimiento1(new Componente(R.drawable.f_rev1,"Resvestimiento 1", this));
        }
        if(arregloComponentes[1]){
            this.eventoAnular.setRevestimiento2(new Componente(R.drawable.f_rev2 ,"Resvestimiento 2", this));
        }
        if(arregloComponentes[2]){
            this.eventoAnular.setRevestimiento3(new Componente(R.drawable.f_rev3 ,"Resvestimiento 3", this));
        }
        if(arregloComponentes[3]){
            this.eventoInterno.setDrillPipe1(new Componente(R.drawable.f_dp1, "Drill Pipe 1", this));
        }
        if(arregloComponentes[4]){
            this.eventoInterno.setDrillPipe2(new Componente(R.drawable.f_dp2, "Drill Pipe 2", this));
        }
        if(arregloComponentes[5]){
            this.eventoInterno.setDrillPipe3(new Componente(R.drawable.f_dp3, "Drill Pipe 3", this));
        }
        if(arregloComponentes[6]){
            this.eventoInterno.setHwdp(new Componente(R.drawable.f_hwdc, "HeavyWeight Drill Pipe", this));
        }
        if(arregloComponentes[7]){
            this.eventoInterno.setDrillCollar(new Componente(R.drawable.f_drillcollar, "Drill Colar",this));
        }
        if(arregloComponentes[8]){
            this.eventoInterno.setDrillCollar2(new Componente(R.drawable.f_drillcollar2, "Drill Colar 2",this));
        }
        if(arregloComponentes[9]){
            this.eventoInterno.setHrrAdc(new Componente(R.drawable.f_hrradicionales, "Herramientas Adicionales",this));
        }
        if(arregloComponentes[10]){
            this.eventoInterno.setEstabilizador(new Componente(R.drawable.f_estabilizador, "Estabilizador", this));
        }
    }

    public Evento(int id){
        this.id = id;
    }

    //Clacuations for total long and total volume to be saved in the event
    public void calcsTotales(){
        this.volTotal = this.eventoAnular.getVolAnular() + this.eventoInterno.getVolInterno();
        if(this.eventoAnular.getLongAnular() == this.eventoInterno.getLongSarta()){
            this.longTotal = this.eventoAnular.getLongAnular();
        }
    }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public EventoInterno getEventoInterno() { return eventoInterno; }
    public void setEventoInterno(EventoInterno eventoInterno) { this.eventoInterno = eventoInterno; }

    public EventoAnular getEventoAnular() { return eventoAnular; }
    public void setEventoAnular(EventoAnular eventoAnular) { this.eventoAnular = eventoAnular; }

    public EventoAmago getEventoAmago() { return eventoAmago; }
    public void setEventoAmago(EventoAmago eventoAmago) { this.eventoAmago = eventoAmago; }

    public double getPesoLodo() { return pesoLodo; }
    public void setPesoLodo(double pesoLodo) { this.pesoLodo = pesoLodo; }

    public double[][] getTablaEstr() { return tablaEstr; }
    public void setTablaEstr(double[][] tablaEstr) { this.tablaEstr = tablaEstr; }

    public double getVolTotal() { return volTotal; }
    public void setVolTotal(double volTotal) { this.volTotal = volTotal; }

    public double getLongTotal() { return longTotal; }
    public void setLongTotal(double longTotal) { this.longTotal = longTotal; }

    public Pozo getPozo() { return pozo; }
    public void setPozo(Pozo pozo) { this.pozo = pozo; }
}
