/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soMesta.SOVratiMesto;
import rs.stefanlezaic.zeleznice.srbije.server.soMesta.SOVratiSvaMesta;

/**
 *
 * @author sleza
 */
public class TestSOMesto {

    Mesto m;
    Klijent k;

    @Before
    public void setUp() {
        k = new Klijent();

    }

    @After
    public void tearDown() {
        m = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOVratiMesto();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOVratiSvaMesta();
        ago.templateExecute(k);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_ne_postoji() throws Exception {
        m = new Mesto(1000);
        AbstractGenericOperation ago = new SOVratiMesto();
        ago.templateExecute(m);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVrati_Pogresni_parametri() throws Exception {
        m = new Mesto(-1);
        AbstractGenericOperation ago = new SOVratiMesto();
        ago.templateExecute(m);
    }

}
