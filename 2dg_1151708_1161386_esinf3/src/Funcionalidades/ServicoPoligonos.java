/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Entidades.Polygon;
import PL.AVL;
import javafx.scene.Node;

public class ServicoPoligonos {
    
    private AVL<Polygon> arvoreUnidades;
    private AVL<Polygon> arvoreDezenas;
    private AVL<Polygon> arvoreCentenas;

    public ServicoPoligonos() {
        this.arvoreUnidades = new AVL();
        this.arvoreDezenas = new AVL<>();
        this.arvoreCentenas = new AVL<>();
    }
    
    public String nomePoligonoPorNumero(String numero){
        String nome = null;
        
        char[] charArray = nome.toCharArray();
        
        
        
        
        return nome;
    }
    
}
