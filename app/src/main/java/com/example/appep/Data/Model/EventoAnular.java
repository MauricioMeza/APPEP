package com.example.appep.Data.Model;

import java.util.ArrayList;

public class EventoAnular {
    //TODO: see if components work better on an Arraylist
    private String id;
    private Evento evento;
    private double volAnular;
    private double longAnular;
    private Componente revestimiento1;
    private Componente revestimiento2;
    private Componente hueco;

    public EventoAnular(Evento evento){
        this.evento = evento;
    }

    public void sumVolumenComp(){
        double suma = 0;
        if(!revestimiento1.equals(null)){
            suma += revestimiento1.getVolumen();
        }
        if(!revestimiento2.equals(null)){
            suma+= revestimiento2.getVolumen();
        }
        suma += hueco.getVolumen();

        this.volAnular = suma;
    }

    public void sumLongitudComp(){
        double suma = 0;
        if(!revestimiento1.equals(null)){
            suma += revestimiento1.getLongitud();
        }
        if(!revestimiento2.equals(null)){
            suma+= revestimiento2.getLongitud();
        }
        suma += hueco.getLongitud();

        this.volAnular = suma;
    }

    public String getId() { return id; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public double getVolAnular() { return volAnular; }
    public void setVolAnular(double volAnular) { this.volAnular = volAnular; }

    public double getLongAnular() { return longAnular; }
    public void setLongAnular(double longAnular) { this.longAnular = longAnular; }

    public Componente getRevestimiento1() { return revestimiento1; }
    public void setRevestimiento1(Componente revestimiento1) { this.revestimiento1 = revestimiento1; }

    public Componente getRevestimiento2() { return revestimiento2; }
    public void setRevestimiento2(Componente revestimiento2) { this.revestimiento2 = revestimiento2; }

    public Componente getHueco() { return hueco; }
    public void setHueco(Componente hueco) { this.hueco = hueco; }

    public ArrayList<Componente> getComponentesInterno(){
        ArrayList<Componente> componentes = new ArrayList<>();

        if(revestimiento1 != null){
            componentes.add(revestimiento1);
        }
        if(this.revestimiento2 != null){
            componentes.add(revestimiento2);
        }

        componentes.add(this.hueco);

        return componentes;
    }

}
