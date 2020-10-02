///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rs.stefanlezaic.zeleznice.srbije.test.SO;
//
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
//import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
//import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
//import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOObrisiRezervaciju;
//import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOUnesiRezervaciju;
//import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervacijeZaKlijenta;
//import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervacijeZaPolazak;
//import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervaciju;
//
///**
// *
// * @author sleza
// */
//public class TestSORezervacija {
//
//    Rezervacija r;
//    Klijent k = new Klijent();
//
//    @Before
//    public void setUp() {
//
//    }
//
//    @After
//    public void tearDown() {
//        r = null;
//
//    }
//
//    @Test(expected = EntityNotFoundException.class)
//    public void testVratiRezervaciju_EntityNotFoundException() throws Exception {
//        r = new Rezervacija(new Klijent(1), new Polazak(10), null);
//        AbstractGenericOperation ago = new SOVratiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testVratiRezervaciju_InvalidProductException1() throws Exception {
//        r = new Rezervacija(new Klijent(-1), new Polazak(10), null);
//        AbstractGenericOperation ago = new SOVratiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testVratiRezervaciju_InvalidProductException2() throws Exception {
//        r = new Rezervacija(new Klijent(1), new Polazak(-1), null);
//        AbstractGenericOperation ago = new SOVratiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    //obrisi
//    @Test(expected = java.lang.Exception.class)
//    public void testObrisiRezervaciju() throws Exception {
//        r = new Rezervacija(new Klijent(10), new Polazak(10), null);
//        AbstractGenericOperation ago = new SOObrisiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testObrisiRezervaciju_InvalidProductException1() throws Exception {
//        r = new Rezervacija(new Klijent(-1), new Polazak(10), null);
//        AbstractGenericOperation ago = new SOObrisiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void ttestObrisiRezervaciju_InvalidProductException2() throws Exception {
//        r = new Rezervacija(new Klijent(1), new Polazak(-1), null);
//        AbstractGenericOperation ago = new SOObrisiRezervaciju();
//        ago.templateExecute(r);
//    }
//    //unesi
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException1() throws Exception {
//        r = new Rezervacija(new Klijent(-1), new Polazak(10), null);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException2() throws Exception {
//        r = new Rezervacija(new Klijent(1), new Polazak(-1), null);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException3() throws Exception {
//        r = new Rezervacija(new Klijent(1), new Polazak(1, "", null, null, null, null, "OTKAZANO"), null);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException4() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        String datum = "01.01.2001";
//        Date d = sdf.parse(datum);
//        r = new Rezervacija(new Klijent(150), new Polazak(150, "", d, d, new Linija(5), new Voz(3), ""), d);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException5() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        String datum = "30.5.2001";
//        Date d = sdf.parse(datum);
//        r = new Rezervacija(new Klijent(15), new Polazak(1, "", d, d, new Linija(5), new Voz(3), ""), d);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testUnesiRezervaciju_InvalidProductException6() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        String datum = "30.5.2055";
//        Date d = sdf.parse(datum);
//        r = new Rezervacija(new Klijent(15), new Polazak(170, "", d, d, new Linija(5), new Voz(3), "OTKAZANO"), null);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = Exception.class)
//    public void testUnesiRezervaciju_Excepiton() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//        String datum = "30.5.2055";
//        Date d = sdf.parse(datum);
//        r = new Rezervacija(new Klijent(15), new Polazak(72, "", d, d, new Linija(2, "", 1, 1, new Stanica(9), new Stanica(2), new TipLinije(1)), new Voz(3), ""), d);
//        AbstractGenericOperation ago = new SOUnesiRezervaciju();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testVratiRezervacijeKlijenta_InvalidProductException() throws Exception {
//        r = new Rezervacija(new Klijent(-1), null, null);
//        AbstractGenericOperation ago = new SOVratiRezervacijeZaKlijenta();
//        ago.templateExecute(r);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testVratiRezervacijePolska_Excepiton() throws Exception {
//        r = new Rezervacija(null, new Polazak(-1), null);
//        AbstractGenericOperation ago = new SOVratiRezervacijeZaPolazak();
//        ago.templateExecute(r);
//    }
//
//}
