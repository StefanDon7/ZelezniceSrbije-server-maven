/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOObrisiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOUnesiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLinijuBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiSveLinije;

/**
 *
 * @author sleza
 */
public class TestSOLinija {

    Linija l;
    Klijent k = new Klijent();

    ;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        l = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Exception() throws Exception {
        AbstractGenericOperation ago = new SOVratiLiniju();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Exception() throws Exception {
        AbstractGenericOperation ago = new SOVratiSveLinije();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiBezPrimarnog_Exception() throws Exception {
        AbstractGenericOperation ago = new SOVratiLinijuBezPrimarnogKljuca();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOUnesi_Exception() throws Exception {
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(k);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiObrisi_Exception() throws Exception {
        AbstractGenericOperation ago = new SOObrisiLiniju();
        ago.templateExecute(k);
    }

    //VRATILINIJU
    @Test(expected = InvalidProductException.class)
    public void testSOVrati_InvalidProductException() throws Exception {
        Linija l = new Linija(-1);
        AbstractGenericOperation ago = new SOVratiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_EntityNotFoundException() throws Exception {
        Linija l = new Linija(10000);
        AbstractGenericOperation ago = new SOVratiLiniju();
        ago.templateExecute(l);
    }
    //VRATILINIJU

    //UNESILINIJU
    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductException1() throws Exception {
        Linija l = new Linija(-1, "naziv", 0, 0, new Stanica(-1), new Stanica(-1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductException2() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 0, new Stanica(-1), new Stanica(-1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductException3() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 1, new Stanica(-1), new Stanica(-1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductException4() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 1, new Stanica(1), new Stanica(-1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductExceptio5() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 1, new Stanica(1), new Stanica(-1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_InvalidProductException6() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 1, new Stanica(1), new Stanica(1), new TipLinije(-1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOUnesi_InsertEntityException() throws Exception {
        Linija l = new Linija(-1, "naziv", 1, 1, new Stanica(9), new Stanica(2), new TipLinije(1));
        AbstractGenericOperation ago = new SOUnesiLiniju();
        ago.templateExecute(l);
    }

    //UNESILINIJU
    //OBRISI LINIJU
    @Test(expected = InvalidProductException.class)
    public void testSOObrisi_InvalidProductException6() throws Exception {
        Linija l = new Linija(-1, "", 0, 0, null, null, null);
        AbstractGenericOperation ago = new SOObrisiLiniju();
        ago.templateExecute(l);
    }

    @Test(expected = Exception.class)
    public void testSOObrisi_DeleteEntityException() throws Exception {
        Linija l = new Linija(10000, "", 0, 0, null, null, null);
        AbstractGenericOperation ago = new SOObrisiLiniju();
        ago.templateExecute(l);
    }
    //OBRISI LINIJU

    //VRATI BEZ PRIMARNOG KLJUCA
    @Test(expected =InvalidProductException.class)
    public void testSOVratiBezPrimarnog_InvalidProductException1() throws Exception {
        Linija l = new Linija(-1, "", 0, 0, new Stanica(-1), new Stanica(5), new TipLinije(5));
        AbstractGenericOperation ago = new SOVratiLinijuBezPrimarnogKljuca();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnog_InvalidProductException2() throws Exception {
        Linija l = new Linija(-1, "", 0, 0, new Stanica(5), new Stanica(-1), new TipLinije(5));
        AbstractGenericOperation ago = new SOVratiLinijuBezPrimarnogKljuca();
        ago.templateExecute(l);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnog_InvalidProductException3() throws Exception {
        Linija l = new Linija(-1, "", 0, 0, new Stanica(5), new Stanica(5), new TipLinije(-1));
        AbstractGenericOperation ago = new SOVratiLinijuBezPrimarnogKljuca();
        ago.templateExecute(l);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVratiBezPrimarnog_EntityNotFoundException() throws Exception {
        Linija l = new Linija(-1, "", 0, 0, new Stanica(5), new Stanica(5), new TipLinije(5));
        AbstractGenericOperation ago = new SOVratiLinijuBezPrimarnogKljuca();
        ago.templateExecute(l);
    }
    //VRATI BEZ PRIMARNOG KLJUCA

}
