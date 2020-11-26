package com.example.appep.Data.Model;

import java.util.ArrayList;

public class EventoInterno {
    //TODO: see if components work better on an Arraylist (specially here)
    private String id;
    private Evento evento;
    private double volInterno, longSarta;
    private Componente drillPipe1, drillPipe2, drillPipe3, hwdp, hrrAdc,
                        drillCollar, drillCollar2, broca, estabilizador;

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

    public Componente getDrillPipe3() { return drillPipe3; }
    public void setDrillPipe3(Componente drillPipe3) { this.drillPipe3 = drillPipe3; }

    public Componente getHwdp() { return hwdp; }
    public void setHwdp(Componente hwdp) { this.hwdp = hwdp; }

    public Componente getDrillCollar() { return drillCollar; }
    public void setDrillCollar(Componente drillCollar) { this.drillCollar = drillCollar; }

    public Componente getDrillCollar2() { return drillCollar2; }
    public void setDrillCollar2(Componente drillCollar2) { this.drillCollar2 = drillCollar2; }

    public Componente getBroca() { return broca; }
    public void setBroca(Componente broca) { this.broca = broca; }

    public Componente getEstabilizador() { return estabilizador; }
    public void setEstabilizador(Componente estabilizador) { this.estabilizador = estabilizador; }

    //Get an ArrayList of Componentes with all the components of this EventoInterno
    public ArrayList<Componente> getComponentesInterno(){
        ArrayList<Componente> componentes = new ArrayList<>();

        if(this.drillPipe1 != null){
            componentes.add(drillPipe1);
        }
        if(this.drillPipe2 != null){
            componentes.add(drillPipe2);
        }
        if(this.drillPipe3 != null){
            componentes.add(drillPipe3);
        }
        if(this.hwdp != null){
            componentes.add(hwdp);
        }
        if(this.drillCollar != null){
            componentes.add(drillCollar);
        }
        if(this.drillCollar2 != null){
            componentes.add(drillCollar2);
        }
        if(this.hrrAdc != null){
            componentes.add(hrrAdc);
        }
        if(this.estabilizador != null){
            componentes.add(estabilizador);
        }

        componentes.add(this.broca);

        return componentes;
    }

    //From an ArrayList of components fill the components and calculations of this EventoInterno
    public void fillComponentesInterno(ArrayList<Componente> internalComponents) {
        this.volInterno = 0.0;
        this.longSarta = 0.0;
        for (Componente comp : internalComponents) {
            this.volInterno += comp.getVolumen();
            this.longSarta += comp.getLongitud();

            switch(comp.getType()){
                case "Broca":
                    this.broca = comp;
                    break;
                case "Herramientas Adicionales":
                    this.hrrAdc = comp;
                    break;
                case "Estabilizador":
                    this.estabilizador = comp;
                    break;
                case "Drill Colar":
                    this.drillCollar = comp;
                    break;
                case "Drill Colar 2":
                    this.drillCollar2 = comp;
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
                case "Drill Pipe 3":
                    this.drillPipe3 = comp;
                    break;
            }
        }
    }

    //Validate no Ceros, Negatives, ID>OD and blanks returning error String if found
    public String internalEventValidation(){
        ArrayList<Componente> comps = this.getComponentesInterno();
        String componente = "";
        String error = "OK";
        boolean errorChecker = false;

        for (Componente comp : comps) {
            if(comp.getLongitud() == 0 || comp.getDiamID() == 0 || comp.getDiamOD() == 0){
                componente = comp.getType();
                error = "Valor no puede ser vacio o cero en ";
                errorChecker = true;
                break;
            }
            if(comp.getLongitud() < 0 || comp.getDiamID() < 0 || comp.getDiamOD() < 0){
                componente = comp.getType();
                error = "Valor no puede ser negativo en ";
                errorChecker = true;
                break;
            }
            if(comp.getDiamOD() < comp.getDiamID()){
                componente = comp.getType();
                error = "ID no puede ser mayor que OD en ";
                errorChecker = true;
                break;
            }
        }

        if(errorChecker){
            return error + componente;
        }else{
            return "OK";
        }
    }
}
