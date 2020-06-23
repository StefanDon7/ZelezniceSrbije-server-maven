/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.Domen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;

/**
 *
 * @author Stefan
 */
public class TestPolazak {
        
    Polazak p;
    Polazak p2;
    

    @Before
    public void setUp() {
        p=new Polazak();
        p2=new Polazak();
    }

    @After
    public void tearDown() {
      p=null;
      p2=null;
    }
    
    @Test
    public void testKonstruktorPolazak() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Voz voz=new Voz(10, "naziv", 1);
        SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy");
        String datum11="28:05:2020";
        String datum22="30:05:2020";
        Date datum1 = null;
        Date datum2 = null;
        try {
            datum1=sdf.parse(datum11);
            datum2=sdf.parse(datum22);
        } catch (ParseException ex) {
            Logger.getLogger(TestPolazak.class.getName()).log(Level.SEVERE, null, ex);
        }
        p=new Polazak(10, "naziv", datum1, datum2, l, voz);
        assertTrue(p.getPolazakID()==10 && p.getNaziv()=="naziv"  && p.getDatumPolaska()==datum1 && p.getDatumDolaska()==datum2 && p.getVoz().equals(voz));
    }

    @Test
    public void testPolazakIstiSu() {
       Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Voz voz=new Voz(10, "naziv", 1);
        SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy");
        String datum11="28:05:2020";
        String datum22="30:05:2020";
        Date datum1 = null;
        Date datum2 = null;
        try {
            datum1=sdf.parse(datum11);
            datum2=sdf.parse(datum22);
        } catch (ParseException ex) {
            Logger.getLogger(TestPolazak.class.getName()).log(Level.SEVERE, null, ex);
        }
//        p=new Polazak(10);
//        p2=new Polazak(10);
        p=new Polazak(10, "naziv", datum1, datum2, l, voz);
        p2=new Polazak(10,"naziv", datum1,datum2,l,voz);
        assertTrue(p.equals(p2));
    }
    @Test
    public void testPolazakNisuIsti() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Voz voz=new Voz(10, "naziv", 1);
        SimpleDateFormat sdf=new SimpleDateFormat("dd:MM:yyyy");
        String datum11="28:05:2020";
        String datum22="30:05:2020";
        Date datum1 = null;
        Date datum2 = null;
        try {
            datum1=sdf.parse(datum11);
            datum2=sdf.parse(datum22);
        } catch (ParseException ex) {
            Logger.getLogger(TestPolazak.class.getName()).log(Level.SEVERE, null, ex);
        }
//        p=new Polazak(10);
//        p2=new Polazak(123);
        p=new Polazak(10, "naziv", datum1, datum2, l, voz);
        p2=new Polazak(123,"naziv", datum1,datum2,l,voz);
        assertFalse(p.equals(p2));
    }
    @Test
    public void testToString() {
       Polazak p=new Polazak(-1, "naziv", null, null, null, null);
       String tostring=p.toString();
       assertTrue(tostring.contains("naziv"));

    }

}
