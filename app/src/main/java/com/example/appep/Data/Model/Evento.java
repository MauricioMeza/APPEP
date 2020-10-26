package com.example.appep.Data.Model;

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

        this.eventoInterno.setBroca(new Componente("Broca", this));
        this.eventoAnular.setHueco(new Componente("Broca", this));

        if(arregloComponentes[0]){
            this.eventoAnular.setRevestimiento1(new Componente("Resvestimiento 1", this));
        }
        if(arregloComponentes[1]){
            this.eventoAnular.setRevestimiento2(new Componente("Resvestimiento 2", this));
        }
        if(arregloComponentes[2]){
            this.eventoInterno.setDrillPipe1(new Componente("Drill Pipe 1", this));
        }
        if(arregloComponentes[3]){
            this.eventoInterno.setDrillPipe2(new Componente("Drill Pipe 2", this));
        }
        if(arregloComponentes[4]){
            this.eventoInterno.setHwdp(new Componente("HeavyWeight Drill Pipe", this));
        }
        if(arregloComponentes[5]){
            this.eventoInterno.setDrillColar(new Componente("Drill Colar",this));
        }
        if(arregloComponentes[6]){
            this.eventoInterno.setEstabilizador(new Componente("Estabilizador", this));
        }
    }




}
