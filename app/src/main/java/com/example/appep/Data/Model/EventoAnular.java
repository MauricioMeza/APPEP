package com.example.appep.Data.Model;

import java.util.ArrayList;

public class EventoAnular {
    //TODO: see if components work better on an Arraylist
    private Evento evento;
    private double volAnular, longAnular;
    private Componente revestimiento1, revestimiento2, revestimiento3, hueco;

    public EventoAnular(Evento evento){ this.evento = evento; }

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

    public Componente getRevestimiento3() { return revestimiento3; }
    public void setRevestimiento3(Componente revestimiento3) { this.revestimiento3 = revestimiento3; }

    public Componente getHueco() { return hueco; }
    public void setHueco(Componente hueco) { this.hueco = hueco; }

    //Get an Arraylist of Componentes with all the components of this EventoAnular
    public ArrayList<Componente> getComponentesAnular(){
        ArrayList<Componente> componentes = new ArrayList<>();

        if(revestimiento1 != null){
            componentes.add(revestimiento1);
        }
        if(this.revestimiento2 != null){
            componentes.add(revestimiento2);
        }
        if(this.revestimiento3 != null){
            componentes.add(revestimiento3);
        }


        componentes.add(this.hueco);

        return componentes;
    }

    //From an ArrayList of components fill the components and calculations of this EventoAnular
    public void fillComponentesAnular(ArrayList<Componente> componentes){
        this.volAnular = 0.0;
        this.longAnular = 0.0;
        for (Componente comp : componentes) {
            this.volAnular += comp.getVolumen();
            this.longAnular += comp.getLongitud();
            switch (comp.getType()){
                case "Hueco":
                    this.hueco = comp;
                    break;
                case "Revestimiento 1":
                    this.revestimiento1 = comp;
                    break;
                case "Revestimiento 2":
                    this.revestimiento2 = comp;
                    break;
                case "Revestimiento 3":
                    this.revestimiento3 = comp;
                    break;
            }
        }
    }

    //Validate no Ceros, Negatives, ID>OD, blanks and equal long returning error String if found
    public String anularEventValidation(){
        ArrayList<Componente> comps = this.getComponentesAnular();
        String componente = "";
        String error = "OK";
        boolean errorChecker = false;

        for (Componente comp : comps) {
            if(comp.getLongitud() == 0 || comp.getDiamID() == 0){
                componente = comp.getType();
                error = "Valor no puede ser vacio o cero en ";
                errorChecker = true;
                break;
            }
            if(comp.getLongitud() < 0 || comp.getDiamID() < 0 || comp.getDiamOD() < 0) {
                componente = comp.getType();
                error = "Valor no puede ser negativo en ";
                errorChecker = true;
                break;
            }
            //TODO: SOMEHOW CHECK IF ALL OF THE CALCULATIONS ARE VALID
            if(comp.getVolumen() < 0){
                componente = comp.getType();
                error = "ID no puede ser mayor que OD en ";
                errorChecker = true;
                break;
            }
        }
        if(this.longAnular != this.evento.getEventoInterno().getLongSarta()){
            componente = "";
            error = "Las longitudes deben ser iguales";
            errorChecker = true;
        }

        if(errorChecker){
            return error + componente;
        }else{
            return "OK";
        }
    }
}
