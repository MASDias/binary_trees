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
        AVL<Polygon> resultTree = new AVL<>();

        for (String linha : polygonResult) {
            String nome = linha.split(";")[0];
            int lados = Integer.parseInt(linha.split(";")[1]);
            Polygon p = new Polygon(nome, lados);
            resultTree.insert(p);
        }
        
        AVL<Polygon> expResultTree = new AVL<>();
        instance.arvoreBalanceadaPoligonos(expResultTree);
        
        while (!expResultTree.isEmpty() && !resultTree.isEmpty()) {
            Polygon result = resultTree.smallestElement();
            Polygon expResult = expResultTree.smallestElement();
            assertEquals(result, expResult);
            resultTree.remove(result);
            expResultTree.remove(expResult);
        }

    }

    /**
     * Test of numeroPoligonoPorNome method, of class ServicoPoligonos.
     */
    @Test
    public void testNumeroPoligonoPorNome() {
        for (int i = 0; i < 999; i++) {
            String strresult = polygonResult.get(i).split(";")[1];
            int expResult = Integer.parseInt(strresult);
            String nome = polygonResult.get(i).split(";")[0];
            int result = instance.numeroPoligonoPorNome(nome);
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
        Polygon a = new Polygon("pentagon",5);
        Polygon b = new Polygon("tetragon",4);
        Polygon expResult = null;
        Polygon result = instance.antecessorComumMaisProximo(a, b);
        assertEquals(expResult, result);
    }

}
