package com.example.appep.Data.Model;

public class Componente {
    private double longitud;
    private double diamOD;
    private double diamID;

    private double volumen;
    private double capacidad;

    public Componente() {
    }

    public void calcCapacidad(){
    }
    public void calcVolumen(){

    }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public double getDiamOD() { return diamOD; }
    public void setDiamOD(double diamOD) { this.diamOD = diamOD; }

    public double getDiamID() { return diamID; }
    public void setDiamID(double diamID) { this.diamID = diamID; }

    public double getVolumen() { return volumen; }
    public void setVolumen(double volumen) { this.volumen = volumen; }

    public double getCapacidad() { return capacidad; }
    public void setCapacidad(double capacidad) { this.capacidad = capacidad; }

}
