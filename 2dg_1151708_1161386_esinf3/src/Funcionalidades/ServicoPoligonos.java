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
import java.util.LinkedList;

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
    private final int ZERO = 0;

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
        if (!construcao && numero < 3) {
            return "Não é um poligono";
        }
        if (numero >= CENTENAS_MENOR) {
            int centenas = (numero / 100) * 100;
            nome += poligonoCentenas(centenas);
        }
        numero = numero % 100;
        if (numero >= DEZENAS_MENOR && numero <= CASO_ESPECIAL_DEZENAS_MAIOR) {
            nome += poligonoDezenas(numero);
            return nome + "gon";
        } else {
            int dezenas = (numero / 10) * 10;
            nome += poligonoDezenas(dezenas);
        }
        numero = numero % 10;
        nome += poligonoUnidades(numero);
        return nome + "gon";
    }

    private String poligonoUnidades(int numero) {
        if (numero <= UNIDADES_MAIOR && numero >= UNIDADES_MENOR) {
            Polygon p = new Polygon("", numero);
            return arvoreUnidades.find(p).getNome();
        }
        return "";
    }

    private String poligonoDezenas(int numero) {
        if (numero >= DEZENAS_MENOR && numero <= DEZENAS_MAIOR) {
            Polygon p = new Polygon("", numero);
            return arvoreDezenas.find(p).getNome();
        }
        return "";
    }

    private String poligonoCentenas(int numero) {
        if (numero >= CENTENAS_MENOR && numero <= CENTENAS_MAIOR) {
            Polygon p = new Polygon("", numero);
            return arvoreCentenas.find(p).getNome();
        }
        return "";
    }

    public void arvoreBalanceadaPoligonos(AVL<Polygon> novaArvore) {
        for (int i = 1; i < 1000; i++) {
            String nome = nomePoligonoPorNumero(i, true);
            Polygon p = new Polygon(nome, i);
            novaArvore.insert(p);
        }
    }

    public int numeroPoligonoPorNome(String nome, AVL<Polygon> arvoreCompleta) {
        if (arvoreCompleta == null) {
            arvoreBalanceadaPoligonos(arvoreCompleta);
        }
        int numero = procuraPorNome(arvoreCompleta, nome);
        return numero;
    }

    private int procuraPorNome(AVL<Polygon> arvore, String nome) {
        if (nome != null) {
            if (nome.equalsIgnoreCase("")) {
                return -1;
            }
            for (Polygon p : arvore.inOrder()) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    return p.getNumeroLados();
                }
            }
        }
        return -1;
    }

    public LinkedList<String> poligonosPorIntervalo(int intervaloEsquerda, int intervaloDireita) {
        if (intervaloDireita < intervaloEsquerda || intervaloDireita <= ZERO || intervaloEsquerda <= ZERO) {
            return null;
        }
        AVL<Polygon> arvoreParcial = new AVL<>();
        for (int i = intervaloEsquerda; i < intervaloDireita + 1; i++) {
            String nome = nomePoligonoPorNumero(i, true);
            Polygon p = new Polygon(nome, i);
            arvoreParcial.insert(p);
        }
        LinkedList<String> lista = new LinkedList<>();
        popularLista(lista, arvoreParcial);
        return lista;
    }

    private void popularLista(LinkedList<String> lista, AVL<Polygon> arvore) {
        for (Polygon polygon : arvore.inOrder()) {
            lista.addFirst(polygon.getNome());
        }
    }

    public Polygon antecessorComumMaisProximo(AVL<Polygon> arvore, Polygon a, Polygon b) {
        if (a == null || b == null) {
            return null;
        }
        if (arvore == null) {
            arvore = new AVL<Polygon>();
            arvoreBalanceadaPoligonos(arvore);
        }
        Polygon aFind = arvore.find(a);
        Polygon bFind = arvore.find(b);
        if (aFind == null || bFind == null) {
            return null;
        }
        Polygon result = arvore.lowestCommonAncestor(a, b);
        return result;
    }
}
