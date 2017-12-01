/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import Entidades.Polygon;
import PL.AVL;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class LeituraFicheiros {
    private ArrayList<String> lerFicheiro(String nomeFicheiro) {
        ArrayList<String> linhas = new ArrayList<>();
        Scanner read = null;
        try {
            read = new Scanner(new java.io.FileReader(nomeFicheiro));
            while (read.hasNext()) {
                String next = read.next();
                linhas.add(next);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado");
        } finally {
            if (read != null) {
                read.close();
            }
        }
        return linhas;
    }

    /**
     *
     */
    public void construcaoArvore(AVL<Polygon> arvore, String nomeFicheiro){ 
        ArrayList<String> list = lerFicheiro(nomeFicheiro);
        
        for (String linha : list) {
            String[] campos = linha.split(";");
            Polygon p = new Polygon(campos[1],Integer.parseInt(campos[0]) );           
            arvore.insert(p);
        }   
    }
    
}


