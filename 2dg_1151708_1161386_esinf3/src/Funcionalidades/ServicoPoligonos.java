/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Entidades.Polygon;
import PL.AVL;
import PL.BST;
import Utilitarios.LeituraFicheiros;

public class ServicoPoligonos {

    private AVL<Polygon> arvoreUnidades;
    private AVL<Polygon> arvoreDezenas;
    private AVL<Polygon> arvoreCentenas;
    private final int UNIDADES_MENOR = 1;
    private final int UNIDADES_MAIOR = 9;
    private final int CASO_ESPECIAL_DEZENAS_MENOR = 10;
    private final int CASO_ESPECIAL_DEZENAS_MAIOR = 29;
    private final int DEZENAS_MENOR = 30;
    private final int DEZENAS_MAIOR = 90;
    private final int CENTENAS_MENOR = 100;
    private final int CENTENAS_MAIOR = 900;

    public ServicoPoligonos() {
        this.arvoreUnidades = new AVL();
        this.arvoreDezenas = new AVL<>();
        this.arvoreCentenas = new AVL<>();
        LeituraFicheiros l = new LeituraFicheiros();
        l.construcaoArvore(arvoreUnidades, "poligonos_prefixo_unidades.txt");
        l.construcaoArvore(arvoreDezenas, "poligonos_prefixo_dezenas.txt");
        l.construcaoArvore(arvoreCentenas, "poligonos_prefixo_centenas.txt");
    }

    public String nomePoligonoPorNumero(int numero) {
        String nome = "";
        if (numero <= UNIDADES_MAIOR && numero >= UNIDADES_MENOR) {
            Polygon p = new Polygon("", numero);
            nome = arvoreUnidades.find(p).getNome();
        }
        
        return nome;
    }

    public AVL<Polygon> getArvoreUnidades() {
        return arvoreUnidades;
    }

}
