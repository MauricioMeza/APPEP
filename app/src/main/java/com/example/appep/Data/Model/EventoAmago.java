package com.example.appep.Data.Model;

public class EventoAmago {

    private String id;
    private Evento evento;
    private double pesoOrglLodo, profVertical, profTotal, presReducidaBomba, desplBomba, presCierreTubo, presCierreRev, gananciaSuperficie;
    private double estrHastaBroca, estrFondoArrb, circTotalMatarPozo;

    public EventoAmago(Evento evento) { this.evento = evento; }

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
}
