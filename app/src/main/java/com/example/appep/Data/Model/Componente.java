package com.example.appep.Data.Model;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

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
        this.longitud = 0.0;
    }

    public ArrayList<Componente> calculosAnulares(ArrayList<Componente> anularComps){
        ArrayList<Componente> internalComps = this.evento.getEventoInterno().getComponentesInterno();

        int currentCompInternal = 0;
        int currentCompAnular = 0;
        double restoAn = 0.0;
        double restoIn = 0.0;
        int i = 0;

        while(currentCompAnular < anularComps.size() && currentCompInternal < internalComps.size()){
            if(restoAn == 0.0){
                anularComps.get(currentCompAnular).setVolumen(0.0);
            }
            double actualInterno =  internalComps.get(currentCompInternal).getLongitud() - restoIn;
            double actualAnular = anularComps.get(currentCompAnular).getLongitud() - restoAn;
            double diferencia = actualAnular-actualInterno;

            double cap = (Math.pow(anularComps.get(currentCompAnular).getDiamID(), 2)  - Math.pow(internalComps.get(currentCompInternal).getDiamOD(), 2)) / 1029.4;
            anularComps.get(currentCompAnular).setCapacidad(cap);

            if( diferencia < 0){
                anularComps.get(currentCompAnular).addVolumen(actualAnular);
                restoIn += actualAnular;
                restoAn = 0.0;
                currentCompAnular++;
            }else if(diferencia == 0){
                anularComps.get(currentCompAnular).addVolumen(actualAnular);
                restoAn = 0.0;
                restoIn = 0.0;
                currentCompAnular++;
                currentCompInternal++;
            }else if(diferencia > 0){
                anularComps.get(currentCompAnular).addVolumen(actualInterno);
                restoAn += actualInterno;
                restoIn = 0.0;
                currentCompInternal++;
            }
        }
        return anularComps;
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
    public double addVolumen(double currentLongitud){
        double volumen = this.volumen + (this.capacidad * currentLongitud);
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
