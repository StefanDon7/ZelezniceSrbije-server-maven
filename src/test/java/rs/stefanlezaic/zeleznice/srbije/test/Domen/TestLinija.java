/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.Domen;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;

/**
 *
 * @author Stefan
 */
public class TestLinija {
    
    Linija l;
    Linija  l2;
    

    @Before
    public void setUp() {
        l=new Linija();
        l2=new Linija();
    }

    @After
    public void tearDown() {
        l=null;
        l2=null;
    }
    
    
    @Test
    public void testLinijaKonstruktor(){
        l=new Linija(10);
        assertTrue(l.getLinijaID()==10);
    }
    
     @Test
    public void testLinijaKonstruktor2(){
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        assertTrue(l.getLinijaID()==10 && l.getNaziv()=="naziv" && l.getKilometraza()==123 && l.getMinutaza()==123 
                && l.getStanicaPocetna().equals(s1) && l.getStanicaKrajnja().equals(s2) && l.getTipLinije().equals(tip));
    }
    
    @Test
    public void testLinijaEquals () {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        l2=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        
        assertTrue(l.equals(l2));
    }

    @Test
    public void testKlijentiNisuIsti() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s12=new Stanica(1012, "stanica12", new Mesto(1012, "mesto12"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        l2=new Linija(10, "naziv", 123, 123, s12, s2, tip);
        assertFalse(l.equals(l2));
    }
     @Test
    public void testKlijentiNisuIsti2() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s12=new Stanica(1012, "stanica12", new Mesto(1012, "mesto12"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        l2=new Linija(10, "naziv", 123, 123, s1, s12, tip);
        assertFalse(l.equals(l2));
    }
       @Test
    public void testKlijentiNisuIsti3() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        TipLinije tip2=new TipLinije(123, "tip2");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        l2=new Linija(10, "naziv", 123, 123, s1, s2, tip2);
        assertFalse(l.equals(l2));
    }
    
    @Test
    public void testToString() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        String linija=l.toString();
        assertTrue(linija.contains("naziv"));
     
    }
}
