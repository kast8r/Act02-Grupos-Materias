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
    String descripcionCurso, abreviatura, departamento;
    int codigoCurso;
    List<Grupo> grupos;
    List<Materia> materias;
    
    public Curso(String descripcionCurso, String abreviatura, String departamento, int codigoCurso, List<Grupo> grupos, List<Materia> materias) {
        this.descripcionCurso = descripcionCurso;
        this.abreviatura = abreviatura;
        this.departamento = departamento;
        this.codigoCurso = codigoCurso;
        this.grupos = grupos;
        this.materias = materias;
    }
    

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
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
