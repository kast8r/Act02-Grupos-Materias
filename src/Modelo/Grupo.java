/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author usumaniana
 */
public class Grupo  implements Serializable {
    String nombre;
    int clave;

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public Grupo() {
    }


    @Override
    public String toString() {
        return   nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    
}
