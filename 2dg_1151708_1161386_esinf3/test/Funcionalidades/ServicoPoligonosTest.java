/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

import Entidades.Polygon;
import PL.AVL;
import Utilitarios.LeituraFicheiros;
import java.util.ArrayList;
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
            String expResult = instance.nomePoligonoPorNumero(i+1, true);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of arvoreBalanceadaPoligonos method, of class ServicoPoligonos.
     */
    @Test
    public void testArvoreBalanceadaPoligonos() {
        System.out.println("arvoreBalanceadaPoligonos");
        AVL<Polygon> novaArvore = null;
        ServicoPoligonos instance = new ServicoPoligonos();
        instance.arvoreBalanceadaPoligonos(novaArvore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of numeroPoligonoPorNome method, of class ServicoPoligonos.
     */
    @Test
    public void testNumeroPoligonoPorNumero() {
         for (int i = 0; i < 999; i++) {
            String strresult = polygonResult.get(i).split(";")[1];
            int result = Integer.parseInt(strresult);
            String nome = polygonResult.get(i).split(";")[0];
            int expResult = instance.numeroPoligonoPorNome(nome);
            assertEquals(result, expResult);
        }
    }

}
