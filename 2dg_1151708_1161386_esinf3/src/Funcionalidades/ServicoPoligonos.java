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
    private final int CASO_ESPECIAL_DEZENAS_MAIOR = 29;
    private final int DEZENAS_MENOR = 10;
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

    public String nomePoligonoPorNumero(int numero, boolean construcao) {
        String nome = "";
        if(!construcao && numero <3){
            return "Não é um poligono";
        }
        int centenas = (numero / 100) * 100;
        nome += poligonoCentenas(centenas, nome);
        numero = numero % 100;
        int dezenas = (numero/10)*10;
        nome += poligonoDezenas(dezenas, nome);
        if (numero >= DEZENAS_MENOR && numero <= CASO_ESPECIAL_DEZENAS_MAIOR) {
            return nome + "gon";
        }
        numero = numero%10;
        return nome + poligonoUnidades(numero % 10, nome) + "gon";
    }

    private String poligonoUnidades(int numero, String nome) {
        if (numero <= UNIDADES_MAIOR && numero >= UNIDADES_MENOR) {
            Polygon p = new Polygon("", numero);
            return arvoreUnidades.find(p).getNome();
        }
        return nome;
    }

    private String poligonoDezenas(int numero, String nome) {
        if (numero >= DEZENAS_MENOR && numero <= DEZENAS_MAIOR) {
            Polygon p = new Polygon("", numero);
            return arvoreDezenas.find(p).getNome();
        }
        return nome;
    }

    private String poligonoCentenas(int numero, String nome) {
        if (numero >= CENTENAS_MENOR && numero <= CENTENAS_MAIOR) {
            Polygon p = new Polygon("", numero);
            return arvoreCentenas.find(p).getNome();
        }
        return nome;
    }

    public void arvoreBalanceadaPoligonos(AVL<Polygon> novaArvore){
        for (int i = 1; i < 1000; i++) {
            String nome = nomePoligonoPorNumero(i, true);
            Polygon p = new Polygon(nome, i);
            novaArvore.insert(p);
        }
    }
    public int numeroPoligonoPorNumero(String nome) {
        int numero = 0;
        Polygon p = new Polygon(nome, 0);
        numero = arvoreUnidades.find(p).getNumeroLados();
        return numero;
    }
    
}
