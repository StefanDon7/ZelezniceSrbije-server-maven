/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soTipLinije.SOVratiSveTipoveLinija;
import rs.stefanlezaic.zeleznice.srbije.server.soTipLinije.SOVratiTipLinije;

/**
 *
 * @author sleza
 */
public class TestSOTipLinije {

    TipLinije tl;
    MedjuStanica m;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        tl = new TipLinije();
        m = new MedjuStanica();
    }

    @After
    public void tearDown() {
        tl = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Nije_instanca_klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiSveTipoveLinija();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiTipLinije();
        ago.templateExecute(m);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_Ne_postoji() throws Exception {
        tl = new TipLinije(1000);
        AbstractGenericOperation ago = new SOVratiTipLinije();
        ago.templateExecute(tl);
    }

}
