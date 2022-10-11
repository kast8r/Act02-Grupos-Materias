/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author usumaniana
 */
public class Curso {
    String descripcionCurso, abreviatura, departamento, nombre;
    int codigoCurso, clave;
    List<Grupo> grupos;
    List<Materia> materias;

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
        return "Curso{" + "descripcionCurso=" + descripcionCurso + ", abreviatura=" + abreviatura + ", departamento=" + departamento + ", codigoCurso=" + codigoCurso + ", grupos=" + grupos + ", materias=" + materias + '}';
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public List<Materia> getMaterias() {
        return materias;
    }


    
}
