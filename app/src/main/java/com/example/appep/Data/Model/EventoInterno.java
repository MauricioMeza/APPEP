package com.example.appep.Data.Model;

import java.util.ArrayList;

public class EventoInterno {
    //TODO: see if components work better on an Arraylist (specially here)
    private String id;
    private Evento evento;
    private double volInterno, longSarta;
    private Componente drillPipe1, drillPipe2, hwdp, drillColar, broca, estabilizador;

    public EventoInterno(Evento evento){
        this.evento = evento;
    }

    public String getId() { return id; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public double getVolInterno() { return volInterno; }
    public void setVolInterno(double volInterno) { this.volInterno = volInterno; }

    public double getLongSarta() { return longSarta; }
    public void setLongSarta(double longSarta) { this.longSarta = longSarta; }

    public Componente getDrillPipe1() {return drillPipe1; }
    public void setDrillPipe1(Componente drillPipe1) { this.drillPipe1 = drillPipe1; }

    public Componente getDrillPipe2() { return drillPipe2; }
    public void setDrillPipe2(Componente drillPipe2) { this.drillPipe2 = drillPipe2; }

    public Componente getHwdp() { return hwdp; }
    public void setHwdp(Componente hwdp) { this.hwdp = hwdp; }

    public Componente getDrillColar() { return drillColar; }
    public void setDrillColar(Componente drillColar) { this.drillColar = drillColar; }

    public Componente getBroca() { return broca; }
    public void setBroca(Componente broca) { this.broca = broca; }

    public Componente getEstabilizador() { return estabilizador; }
    public void setEstabilizador(Componente estabilizador) { this.estabilizador = estabilizador; }


    public ArrayList<Componente> getComponentesInterno(){
        ArrayList<Componente> componentes = new ArrayList<>();

        if(this.drillPipe1 != null){
            componentes.add(drillPipe1);
        }
        if(this.drillPipe2 != null){
            componentes.add(drillPipe2);
        }
        if(this.hwdp != null){
            componentes.add(hwdp);
        }
        if(this.drillColar != null){
            componentes.add(drillColar);
        }
        if(this.estabilizador != null){
            componentes.add(estabilizador);
        }

        componentes.add(this.broca);

        return componentes;
    }

    public void fillComponentesInterno(ArrayList<Componente> internalComponents) {
        for (Componente comp : internalComponents) {
            switch(comp.getType()){
                case "Broca":
                    this.broca = comp;
                    break;
                case "Estabilizador":
                    this.estabilizador = comp;
                    break;
                case "Drill Colar":
                    this.drillColar = comp;
                    break;
                case "HeavyWeight Drill Pipe":
                    this.hwdp = comp;
                    break;
                case "Drill Pipe 1":
                    this.drillPipe1 = comp;
                    break;
                case "Drill Pipe 2":
                    this.drillPipe2 = comp;
                    break;
            }
        }
    }
}
