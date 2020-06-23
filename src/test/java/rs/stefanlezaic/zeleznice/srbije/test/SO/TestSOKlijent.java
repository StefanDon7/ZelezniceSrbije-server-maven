/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.SO;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.UpdateEntityException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOIzmeniKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOUnesiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijentaBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiSveKlijente;

/**
 *
 * @author sleza
 */
public class TestSOKlijent {
    //(id, korisnickoIme, lozinka, ime, prezime, email)

    Klijent k;
    MedjuStanica m;

    @Before
    public void setUp() {
        k = new Klijent();
        m = new MedjuStanica();
    }

    @After
    public void tearDown() {
        k = null;
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOIzmeni_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOIzmeniKlijenta();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOUnesi_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOUnesiKlijenta();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVrati_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiKlijenta();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiSve_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiSveKlijente();
        ago.templateExecute(m);
    }

    @Test(expected = java.lang.Exception.class)
    public void testSOVratiBezPrimarnogKljuca_Nije_instanca_Klase() throws Exception {
        m = new MedjuStanica();
        AbstractGenericOperation ago = new SOVratiSveKlijente();
        ago.templateExecute(m);
    }

    //********UNESI KLIJENTA************************************************************************
    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_Fali_KorisnickoIme() throws Exception {
        k = new Klijent(1000, "", "prazno", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_Fali_Lozinka() throws Exception {
        k = new Klijent(1000, "prazno", "", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_Fali_Ime() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_Fali_Prezime() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "", "prazno");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOUnesi_Fali_Email() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "prazno", "");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }

    
    @Test(expected = SQLException.class)
    public void testSOUnesi_Postoji() throws Exception {
        k = new Klijent(10, "prazno", "prazno", "prazno", "prazno", "s.lezaic95@gmail.com");
        AbstractGenericOperation ago1 = new SOUnesiKlijenta();
        ago1.templateExecute(k);
    }
    //********UNESI KLIJENTA************************************************************************

    //**************************Vrati KLIJENTA******************************************
    @Test(expected = InvalidProductException.class)
    public void testSOVrati_primarni_kljuc_manji_od_nule() throws Exception {
        k = new Klijent(0);
        AbstractGenericOperation ago1 = new SOVratiKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVrati_Ne_postoji() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOVratiKlijenta();
        ago1.templateExecute(k);
    }
    //**************************Vrati KLIJENTA******************************************

    //**************************Vrati KLIJENTA BEZ PRIMARNOG KLJUCA******************************************
    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnogKljuca_email_null() throws Exception {
        k = new Klijent(-1, "", "123", "", "", "");
        AbstractGenericOperation ago1 = new SOVratiKlijentaBezPrimarnogKljuca();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOVratiBezPrimarnogKljuca_lozinka_null() throws Exception {
        k = new Klijent(-1, "", "", "", "", "email");
        AbstractGenericOperation ago1 = new SOVratiKlijentaBezPrimarnogKljuca();
        ago1.templateExecute(k);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testSOVratiBezPrimarnogKljuca_Ne_postoji() throws Exception {
        k = new Klijent(-1, "", "agd", "", "", "das");
        AbstractGenericOperation ago1 = new SOVratiKlijentaBezPrimarnogKljuca();
        ago1.templateExecute(k);
    }

    //**************************Vrati KLIJENTA BEZ PRIMARNOG KLJUCA******************************************
//**********************IZMENI KLIJENTA*********************************************
    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_Fali_KorisnickoIme() throws Exception {
        k = new Klijent(1000, "", "prazno", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_Fali_Lozinka() throws Exception {
        k = new Klijent(1000, "prazno", "", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_Fali_Ime() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_Fali_Prezime() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "", "prazno");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = InvalidProductException.class)
    public void testSOIzmeni_Fali_Email() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "prazno", "");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    @Test(expected = UpdateEntityException.class)
    public void testSOIzmeni_Ne_postoji() throws Exception {
        k = new Klijent(1000, "prazno", "prazno", "prazno", "prazno", "prazno");
        AbstractGenericOperation ago1 = new SOIzmeniKlijenta();
        ago1.templateExecute(k);
    }

    //**********************IZMENI KLIJENTA*********************************************
}
