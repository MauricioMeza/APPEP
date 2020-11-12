package com.example.appep.Data.Model;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Componente {
    private double longitud;
    private double diamOD;
    private double diamID;
    private String type;
    private int img;

    private double volumen;
    private double capacidad;
    private Evento evento;

    public Componente(int img, String type, Evento evento) {
        this.type = type;
        this.img = img;
        this.evento = evento;
        this.volumen = 0.0;
        this.capacidad = 0.0;
    }

    public double calcCapacidad(){
        double capacidad = this.diamID * this.diamID / 1029.4;
        this.capacidad = capacidad;
        return capacidad;
    }
    public double calcVolumen(){
        double volumen = this.capacidad * this.longitud;
        this.volumen = volumen;
        return volumen;
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

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getImg() { return img; }
    public void setImg(int img) { this.img = img; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }



}
