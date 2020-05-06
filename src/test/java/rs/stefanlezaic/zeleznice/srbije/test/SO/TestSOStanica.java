/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOUnesiMedjustanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOUnesiStanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicuBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiSveStanice;

/**
 *
 * @author sleza
 */
public class TestSOStanica {

    Stanica s;
    Klijent k = new Klijent();

    ;

    @Before
    public void setUp() {
        s = new Stanica();
    }

    @After
    public void tearDown() {
        s = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOVratiSveStanice();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOVratiStanicu();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiBezPrimarnog_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOVratiStanicuBezPrimarnogKljuca();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOUnesi_Nije_Instanca() throws Exception {
        AbstractGenericOperation ago = new SOUnesiMedjustanicu();
        ago.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVrati_Pogresni_parametri() throws Exception {
        s = new Stanica(-1);
        AbstractGenericOperation ago = new SOVratiStanicu();
        ago.templateExecute(s);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_Ne_postoji() throws Exception {
        s = new Stanica(1500);
        AbstractGenericOperation ago = new SOVratiStanicu();
        ago.templateExecute(s);
    }

    //VRATI BEZ PRIMARNOG KLJUCA!
    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnogKljuca_Naziv_je_empty() throws Exception {
        s = new Stanica(-1, "", new Mesto(5));
        AbstractGenericOperation ago = new SOVratiStanicuBezPrimarnogKljuca();
        ago.templateExecute(s);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnogKljuca_Mesto_je_Pogresno() throws Exception {
        s = new Stanica(-1, "Primer", new Mesto(-1));
        AbstractGenericOperation ago = new SOVratiStanicuBezPrimarnogKljuca();
        ago.templateExecute(s);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVratiBezPrimarnogKljuca_Ne_postoji() throws Exception {
        s = new Stanica(-1, "Primer", new Mesto(5));
        AbstractGenericOperation ago = new SOVratiStanicuBezPrimarnogKljuca();
        ago.templateExecute(s);
    }
    //VRATI BEZ PRIMARNOG KLJUCA!

    //UNESISTANICU
    @Test(expected = InvalidProductException.class)
    public void testUnesiStanicu_invalidProduct() throws Exception {
        s = new Stanica(-1, "", new Mesto(3));
        AbstractGenericOperation ago = new SOUnesiStanicu();
        ago.templateExecute(s);
    }

    @Test(expected = InvalidProductException.class)
    public void testUnesiStanicu_invalidProduct2() throws Exception {
        s = new Stanica(-1, "Novi Sad", new Mesto(-1));
        AbstractGenericOperation ago = new SOUnesiStanicu();
        ago.templateExecute(s);
    }

    @Test(expected = Exception.class)
    public void testUnesiStanicu() throws Exception {
        s = new Stanica(-1, "Novi Sad", new Mesto(3));
        AbstractGenericOperation ago = new SOUnesiStanicu();
        ago.templateExecute(s);
    }
    //UNESISTANICU
}
