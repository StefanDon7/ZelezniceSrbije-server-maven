/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOIzmeniPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOObrisiPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOUnesiPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiPolazakZaDatum;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiSvePolaske;

/**
 *
 * @author sleza
 */
public class TestSOPolazak {

    Polazak p;
    Klijent k = new Klijent();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        p = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Exception() throws Exception {
        AbstractGenericOperation ago = new SOVratiPolazak();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOIVratiSve_Exception1() throws Exception {
        AbstractGenericOperation ago = new SOVratiSvePolaske();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiPolazakZaDatum_Exception2() throws Exception {
        AbstractGenericOperation ago = new SOVratiPolazakZaDatum();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOIzmeni_Exception3() throws Exception {
        AbstractGenericOperation ago = new SOIzmeniPolazak();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOObrisi_Exception4() throws Exception {
        AbstractGenericOperation ago = new SOObrisiPolazak();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOUnesi_Exception5() throws Exception {
        AbstractGenericOperation ago = new SOUnesiPolazak();
        ago.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOObrisi_InvalidProductException() throws Exception {
        p = new Polazak(-1);
        AbstractGenericOperation ago = new SOObrisiPolazak();
        ago.templateExecute(p);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVrati_InvalidProductException() throws Exception {
        p = new Polazak(-1);
        AbstractGenericOperation ago = new SOVratiPolazak();
        ago.templateExecute(p);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_InvalidProductException() throws Exception {
        p = new Polazak(-1);
        AbstractGenericOperation ago = new SOIzmeniPolazak();
        ago.templateExecute(p);
    }

}
