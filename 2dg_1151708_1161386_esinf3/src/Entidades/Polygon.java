/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Objects;

public class Polygon implements Comparable<Polygon> {

    private String nome;
    private int numeroLados;

    public Polygon(String nome, int numeroLados) {
        this.nome = nome;
        this.numeroLados = numeroLados;
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroLados() {
        return numeroLados;
    }

    @Override
    public String toString() {
        return nome + " " + numeroLados;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.nome);
        hash = 11 * hash + this.numeroLados;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Polygon other = (Polygon) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.numeroLados != other.numeroLados) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Polygon o) {
        if (numeroLados > o.numeroLados) {
            return 1;
        } else if (numeroLados < o.numeroLados) {
            return -1;
        }
        
        return 0;    
    }

}
