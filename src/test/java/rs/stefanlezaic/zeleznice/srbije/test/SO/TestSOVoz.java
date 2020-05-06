/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soVoz.SOVratiSveVozove;
import rs.stefanlezaic.zeleznice.srbije.server.soVoz.SOVratiVoz;

/**
 *
 * @author sleza
 */
public class TestSOVoz {

    Voz v;
    MedjuStanica m;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        v = new Voz();
        m = new MedjuStanica();
    }

    @After
    public void tearDown() {
        v = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Nije_instanca_klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiVoz();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiSveVozove();
        ago.templateExecute(m);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_Ne_postoji() throws Exception {
        Voz v = new Voz(1000);
        AbstractGenericOperation ago = new SOVratiVoz();
        ago.templateExecute(v);
    }

}
