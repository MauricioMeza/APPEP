package com.example.appep.Data.Model;

public class EventoAnular {
    //TODO: see if components work better on an Arraylist
    private String id;
    private Evento evento;
    private double volAnular;
    private double longAnular;
    private Componente revestimiento1;
    private Componente revestimiento2;
    private Componente hueco;

    public EventoAnular(Evento event){
        this.evento = event;
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

}
