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
    String descripcionCurso, abreviaturaCurso ;
    int codigoCurso;
    List<Grupo> grupos = new ArrayList();
    List<Materia> materias = new ArrayList();
    
    public String getAbreviaturaCurso() {
        return abreviaturaCurso;
    }

    public void setAbreviaturaCurso(String abreviaturaCurso) {
        this.abreviaturaCurso = abreviaturaCurso;
    }

    public void setDescripcionCurso(String descripcionCurso) {
        this.descripcionCurso = descripcionCurso;
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



    public int getCodigoCurso() {
        return codigoCurso;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    @Override
    public String toString() {

        return " " + abreviaturaCurso + " nM: " + materias.size() + " cod: " + codigoCurso + "";
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
