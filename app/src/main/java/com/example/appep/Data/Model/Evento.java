package com.example.appep.Data.Model;

import com.example.appep.R;

import java.util.ArrayList;
import java.util.Date;

public class Evento {
    private String id;
    private Date fechaCreacion;
    private double pesoLodo;
    private double tablaEstr[][];
    private EventoInterno eventoInterno;
    private EventoAnular eventoAnular;
    private EventoAmago eventoAmago;

    public Evento(boolean[] arregloComponentes){
        this.fechaCreacion = new Date();
        this.eventoInterno = new EventoInterno(this);
        this.eventoAnular = new EventoAnular(this);
        this.eventoAmago = new EventoAmago(this);

        //TODO: Arreglar este machetazo
        this.eventoInterno.setBroca(new Componente(R.drawable.f_broca,"Broca", this));
        this.eventoAnular.setHueco(new Componente(R.drawable.f_hueto, "Hueco", this));

        if(arregloComponentes[0]){
            this.eventoAnular.setRevestimiento1(new Componente(R.drawable.f_rev1,"Resvestimiento 1", this));
        }
        if(arregloComponentes[1]){
            this.eventoAnular.setRevestimiento2(new Componente(R.drawable.f_rev2 ,"Resvestimiento 2", this));
        }
        if(arregloComponentes[2]){
            this.eventoInterno.setDrillPipe1(new Componente(R.drawable.f_dp1, "Drill Pipe 1", this));
        }
        if(arregloComponentes[3]){
            this.eventoInterno.setDrillPipe2(new Componente(R.drawable.f_dp2, "Drill Pipe 2", this));
        }
        if(arregloComponentes[4]){
            this.eventoInterno.setHwdp(new Componente(R.drawable.f_hwdc, "HeavyWeight Drill Pipe", this));
        }
        if(arregloComponentes[5]){
            this.eventoInterno.setDrillColar(new Componente(R.drawable.f_drillcollar, "Drill Colar",this));
        }
        if(arregloComponentes[6]){
            this.eventoInterno.setEstabilizador(new Componente(R.drawable.f_estabilizador, "Estabilizador", this));
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
}
