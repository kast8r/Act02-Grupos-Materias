/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usumaniana
 */
public class Curso implements Serializable {
    String descripcionCurso, abreviatura, departamento, nombre, abreviaturaCurso;
    int codigoCurso, clave;
    List<Grupo> grupos = new ArrayList();
    List<Materia> materias = new ArrayList();
    
    public String getAbreviaturaCurso() {
        return abreviaturaCurso;
    }

    public void setAbreviaturaCurso(String abreviaturaCurso) {
        this.abreviaturaCurso = abreviaturaCurso;
    }


    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }
    


    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getDescripcionCurso() {
        return descripcionCurso;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    @Override
    public String toString() {

        return " " + nombre;
    }

    public void addMaterias(Materia materia) {
        this.materias.add(materia);
    }
    
    public void addGrupos(Grupo grupo) {
        this.grupos.add(grupo);
    }

    public List<Materia> getMaterias() {
        return materias;
    }


    
}
