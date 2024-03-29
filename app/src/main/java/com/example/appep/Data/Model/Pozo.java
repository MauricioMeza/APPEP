package com.example.appep.Data.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Pozo implements Serializable {
    private int id;
    private String nombre;
    private String campo;
    private Date fechaCreacion;
    private boolean abierto;
    private boolean vertical;
    private ArrayList<Evento> eventos;

    //For Update Pozo
    public Pozo(int id) {
        this.id = id;
        this.fechaCreacion = new Date();
        this.abierto = true;
        this.eventos = new ArrayList<>();
    }
    //For Create new Pozo
    public Pozo(String nombre, String campo, boolean type) {
        this.nombre = nombre;
        this.campo = campo;
        this.vertical = type;
        this.fechaCreacion = new Date();
        this.abierto = true;
        this.eventos = new ArrayList<>();
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCampo() { return campo; }
    public void setCampo(String campo) { this.campo = campo; }

    public boolean isAbierto() { return abierto; }
    public void setAbierto(boolean abierto) { this.abierto = abierto;}

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion;}

    public boolean isVertical() { return vertical; }
    public void setVertical(boolean vertical) { this.vertical = vertical; }


    public ArrayList<Evento> getEventos() { return eventos; }
    public void setEventos(ArrayList<Evento> eventos) { this.eventos = eventos; }

    //Set a new Evento in the Pozo when created in order of creation date
    public void setNewEvento(Evento evento){
        evento.setPozo(this);
        this.eventos.add(evento);
        Collections.sort(this.eventos, new Comparator<Evento>() {
            @Override
            public int compare(Evento o1, Evento o2) {
                return o2.getFechaCreacion().compareTo(o1.getFechaCreacion());
            }
        });
    }
}
