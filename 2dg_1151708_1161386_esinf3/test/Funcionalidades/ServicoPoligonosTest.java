package Funcionalidades;

import Entidades.Polygon;
import PL.AVL;
import Utilitarios.LeituraFicheiros;
import java.util.ArrayList;
import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MarioDias
 */
public class ServicoPoligonosTest {

    ServicoPoligonos instance;
    ArrayList<String> polygonResult;

    public ServicoPoligonosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new ServicoPoligonos();
        LeituraFicheiros l = new LeituraFicheiros();
        polygonResult = l.lerFicheiro("teste_nome_lados.txt");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of nomePoligonoPorNumero method, of class ServicoPoligonos.
     */
    @Test
    public void testNomePoligonoPorNumero() {
        for (int i = 0; i < 999; i++) {
            String result = polygonResult.get(i).split(";")[0];
            String expResult = instance.nomePoligonoPorNumero(i + 1, true);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of arvoreBalanceadaPoligonos method, of class ServicoPoligonos.
     */
    @Test
    public void testArvoreBalanceadaPoligonos() {
        LinkedList<Polygon> expResultListPolygon = new LinkedList<>();
        LinkedList<Polygon> resultListPolygon = new LinkedList<>();
        for (String linha : polygonResult) {
            String nome = linha.split(";")[0];
            int lados = Integer.parseInt(linha.split(";")[1]);
            Polygon p = new Polygon(nome, lados);
            expResultListPolygon.add(p);
        }
        AVL<Polygon> resultTree = new AVL<>();
        instance.arvoreBalanceadaPoligonos(resultTree);
        for (Polygon polygon : resultTree.inOrder()) {
            resultListPolygon.add(polygon);
        }
        assertEquals(resultListPolygon, expResultListPolygon);
    }

    /**
     * Test of numeroPoligonoPorNome method, of class ServicoPoligonos.
     */
    @Test
    public void testNumeroPoligonoPorNome() {
        AVL<Polygon> arvoreCompletaResult = new AVL<>();
        instance.arvoreBalanceadaPoligonos(arvoreCompletaResult);
        for (int i = 0; i < 999; i++) {
            String strresult = polygonResult.get(i).split(";")[1];
            int expResult = Integer.parseInt(strresult);
            String nome = polygonResult.get(i).split(";")[0];
            int result = instance.numeroPoligonoPorNome(nome, arvoreCompletaResult);
            assertEquals(expResult, result);
        }
    }

    /**
     * Test of poligonosPorIntervalo method, of class ServicoPoligonos.
     */
    @Test
    public void testPoligonosPorIntervalo() {
        int intervaloEsquerda = 9;
        int intervaloDireita = 15;
        LinkedList<String> expResult = new LinkedList<>();
        for (String poligonoStr : polygonResult) {
            String nome = poligonoStr.split(";")[0];
            expResult.addFirst(nome);
        }
        LinkedList<String> result = instance.poligonosPorIntervalo(intervaloEsquerda, intervaloDireita);
        assertEquals(result, result);
    }

    /**
     * Test of antecessorComumMaisProximo method, of class ServicoPoligonos.
     */
    @Test
    public void testAntecessorComumMaisProximo() {
        System.out.println("antecessorComumMaisProximo");
        AVL<Integer> arvore = new AVL<>();
        for (int i = 1; i < 16; i++) {
            arvore.insert(i);
        }
        int result = arvore.lowestCommonAncestor(1, 3);
        assertEquals(2, result);
        result = arvore.lowestCommonAncestor(5, 7);
        assertEquals("Resultado deve ser 6 ", 6, result);
        result = arvore.lowestCommonAncestor(9, 11);
        assertEquals("Resultado deve ser 10 ", 10, result);
        result = arvore.lowestCommonAncestor(13, 15);
        assertEquals("Resultado deve ser 14 ", 14, result);
        result = arvore.lowestCommonAncestor(10, 14);
        assertEquals("Resultado deve ser 12 ", 12, result);
        result = arvore.lowestCommonAncestor(2, 6);
        assertEquals("Resultado deve ser 4 ", 4, result);
        result = arvore.lowestCommonAncestor(4, 12);
        assertEquals("Resultado deve ser 8 ", 8, result);
        result = arvore.lowestCommonAncestor(1, 2);
        assertEquals("Resultado deve ser 2 ", 2, result);
        result = arvore.lowestCommonAncestor(1, 15);
        assertEquals("Resultado deve ser 8 ", 8, result);
        result = arvore.lowestCommonAncestor(7, 9);
        assertEquals("Resultado deve ser 8 ", 8, result);

        AVL<Polygon> resultTree = new AVL<>();
        LinkedList<Polygon> resultList = new LinkedList<>();
        resultTree.insert(new Polygon("henagon",1));
        resultTree.insert(new Polygon("digon",2));
        resultTree.insert(new Polygon("trigon",3));
        resultTree.insert(new Polygon("tetragon",4));
        resultTree.insert(new Polygon("pentagon",5));
        resultTree.insert(new Polygon("hexagon",6));
        resultTree.insert(new Polygon("heptagon",7));
        resultTree.insert(new Polygon("octagon",8));
        resultTree.insert(new Polygon("enneagon",9));
        resultTree.insert(new Polygon("decagon",10));
        resultTree.insert(new Polygon("hendecagon",11));
        resultTree.insert(new Polygon("dodecagon",12));
        resultTree.insert(new Polygon("triskaidecagon",13));
        resultTree.insert(new Polygon("tetrakaidecagon",14));
        resultTree.insert(new Polygon("pentakaidecagon",15));
        resultTree.insert(new Polygon("hexakaidecagon",16));

        for (Polygon polygon : resultTree.inOrder()) {    
            resultList.add(polygon);
        }
        System.out.println(resultList);
        
        Polygon a = resultList.getFirst();
        Polygon b = resultList.getLast();
        
        Polygon resultPolygon = instance.antecessorComumMaisProximo(resultTree, a, b);
        Polygon expResult = new Polygon("octagon", 8);
        assertTrue(resultPolygon.equals(expResult));
    }
}
