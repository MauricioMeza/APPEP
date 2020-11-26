package com.example.appep.Data.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Pozo implements Serializable {
    private int id;
    private String nombre;
    private String campo;
    private Date fecha_creacion;
    private boolean abierto;
    private boolean vertical;
    private ArrayList<Evento> eventos;

    public Pozo(int id) {
        this.id = id;
        this.fecha_creacion = new Date();
        this.abierto = true;
        this.eventos = new ArrayList<>();
    }
    public Pozo(String nombre, String campo, boolean type) {
        this.nombre = nombre;
        this.campo = campo;
        this.vertical = type;
        this.fecha_creacion = new Date();
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

    public Date getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(Date fecha_creacion) { this.fecha_creacion = fecha_creacion;}

    public boolean isVertical() { return vertical; }
    public void setVertical(boolean vertical) { this.vertical = vertical; }


    public ArrayList<Evento> getEventos() { return eventos; }
    public void setEventos(ArrayList<Evento> eventos) { this.eventos = eventos; }
    public void setNewEvento(Evento evento){ this.eventos.add(evento); }
}
