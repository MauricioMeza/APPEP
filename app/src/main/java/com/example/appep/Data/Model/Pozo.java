package com.example.appep.Data.Model;

import java.io.Serializable;
import java.util.Date;

public class Pozo implements Serializable {
    private int id;
    private String nombre;
    private String campo;
    private Date fecha_creacion;
    private boolean abierto;

    public Pozo(int id) {
        this.id = id;
        this.fecha_creacion = new Date();
        this.abierto = true;
    }
    public Pozo(String nombre, String campo) {
        this.nombre = nombre;
        this.campo = campo;
        this.fecha_creacion = new Date();
        this.abierto = true;
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
}
