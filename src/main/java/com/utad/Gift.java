package com.utad;

public class Gift {

    private final String nombre;
    private final String regalo;
    private final double precio;

    public Gift(String nombre, String regalo, double precio) {
        this.nombre = nombre;
        this.regalo = regalo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegalo() {
        return regalo;
    }

    public double getPrecio() {
        return precio;
    }


}
