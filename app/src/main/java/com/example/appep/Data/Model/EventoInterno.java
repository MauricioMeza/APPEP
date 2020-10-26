package com.example.appep.Data.Model;

import java.util.ArrayList;

public class EventoInterno {
    //TODO: see if components work better on an Arraylist (specially here)
    private String id;
    private Evento evento;
    private double volInterno;
    private double longSarta;
    private Componente drillPipe1;
    private Componente drillPipe2;
    private Componente hwdp;
    private Componente drillColar;
    private Componente broca;
    private Componente estabilizador;

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


    public ArrayList<Componente> getComponentesAnular(){
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
}
