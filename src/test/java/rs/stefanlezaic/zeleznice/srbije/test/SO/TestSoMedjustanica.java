///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package rs.stefanlezaic.zeleznice.srbije.test.SO;
//
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
//import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
//import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
//import java.sql.SQLException;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
//import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOIzmeniKlijenta;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOIzmeniMedjustanicu;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOObrisiMedjustanicu;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOUnesiMedjustanicu;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiMedjustanicu;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiSveMedjustanice;
//import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiSveMedjustaniceZaLiniju;
//
///**
// *
// * @author sleza
// */
//public class TestSoMedjustanica {
//
//    MedjuStanica m;
//
//    public TestSoMedjustanica() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//
//    }
//
//    @Before
//    public void setUp() {
//        m = new MedjuStanica();
//    }
//
//    @After
//    public void tearDown() {
//        m = null;
//
//    }
//
//    @Test(expected = java.lang.Exception.class)
//    public void testSOIzmeniIliUnesi_Nije_instanca_klase() throws Exception {
//        Klijent k = new Klijent();
//        AbstractGenericOperation ago = new SOIzmeniMedjustanicu();
//        ago.templateExecute(k);
//    }
//
//    @Test(expected = java.lang.Exception.class)
//    public void testSOVratiSve_Nije_instanca_klase() throws Exception {
//        Klijent k = new Klijent();
//        AbstractGenericOperation ago = new SOVratiSveMedjustanice();
//        ago.templateExecute(k);
//    }
//
//    @Test(expected = java.lang.Exception.class)
//    public void testSOVratiSve_Za_Liniju_Nije_instanca_klase() throws Exception {
//        Klijent k = new Klijent();
//        AbstractGenericOperation ago = new SOVratiSveMedjustaniceZaLiniju();
//        ago.templateExecute(k);
//    }
//
//    @Test(expected = java.lang.Exception.class)
//    public void testSOVrati_Nije_instanca_klase() throws Exception {
//        Klijent k = new Klijent();
//        AbstractGenericOperation ago = new SOVratiMedjustanicu();
//        ago.templateExecute(k);
//    }
//
//    @Test(expected = java.lang.Exception.class)
//    public void testSOObrisi_Nije_instanca_klase() throws Exception {
//        Klijent k = new Klijent();
//        AbstractGenericOperation ago = new SOObrisiMedjustanicu();
//        ago.templateExecute(k);
//    }
//
//    //UNESI ILI DODAJ MEDJUSTANICU
//    @Test(expected = InvalidProductException.class)
//    public void testSOUnesi_InvalidProduct1() throws Exception {
//        m = new MedjuStanica(new Stanica(10), new Linija(0, "", 0, 0, new Stanica(10), null, null), 5);
//        AbstractGenericOperation ago = new SOUnesiMedjustanicu();
//        ago.templateExecute(m);
//    }
//
//    @Test(expected = InvalidProductException.class)
//    public void testSOUnesi_InvalidProduct2() throws Exception {
//        m = new MedjuStanica(new Stanica(10), new Linija(0, "", 0, 0, null, new Stanica(10), null), 5);
//        AbstractGenericOperation ago = new SOUnesiMedjustanicu();
//        ago.templateExecute(m);
//    }
//
//    @Test(expected = SQLException.class)
//    public void testSOUnesi() throws Exception {
//        m = new MedjuStanica(new Stanica(10), new Linija(5), 5);
//        AbstractGenericOperation ago = new SOUnesiMedjustanicu();
//        ago.templateExecute(m);
//    }
//    //UNESI ILI DODAJ MEDJUSTANICU
//
//    //IZMENI ILI DODAJ MEDJUSTANICU
//    @Test(expected = Exception.class)
//    public void testSOIzmeni() throws Exception {
//        m = new MedjuStanica(new Stanica(555), new Linija(5), 5);
//        AbstractGenericOperation ago = new SOIzmeniKlijenta();
//        ago.templateExecute(m);
//    }
//
//    //IZMENI ILI DODAJ MEDJUSTANICU
//    //VRATI ZA LINIJU
//    @Test(expected = Exception.class)
//    public void testSOVratiZaLiniju() throws Exception {
//        m = new MedjuStanica(new Stanica(1), new Linija(555), 5);
//        AbstractGenericOperation ago = new SOObrisiMedjustanicu();
//        ago.templateExecute(m);
//    }
//    //VRATI ZA LINIJU
//}
