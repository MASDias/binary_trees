/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funcionalidades;

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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of nomePoligonoPorNumero method, of class ServicoPoligonos.
     */
    @Test
    public void testNomePoligonoPorNumero() {
        String nome = instance.nomePoligonoPorNumero(9);
        System.out.println(nome);
    }

}
