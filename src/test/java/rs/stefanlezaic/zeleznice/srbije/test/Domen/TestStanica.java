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
public class TestStanica {
    
    Stanica s;
    Stanica  s2;
    

    @Before
    public void setUp() {
        s=new Stanica();
        s2=new Stanica();
    }

    @After
    public void tearDown() {
        s=null;
        s2=null;
    }
    
    
    @Test
    public void testStaniceKonstruktor(){
        s=new Stanica(10);
        assertTrue(s.getStanicaID()==10);
    }
    
     @Test
    public void testStaniceKonstrukor(){
        Mesto m=new Mesto(10, "mesto");
        Stanica s=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        assertTrue(s.getStanicaID()==10 && s.getNaziv()=="naziv" && s.getMesto().equals(m));
        
    }
    
    @Test
    public void testStaniceEquals () {
        Stanica s=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        assertTrue(s.equals(s2));
    }

    @Test
    public void testStaniceEquals2() {
         Stanica s=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(10, "naziv2", new Mesto(10, "mesto"));
        assertFalse(s.equals(s2));
    }
     @Test
    public void testStaniceEquals3() {
        Stanica s=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(10, "naziv", new Mesto(101, "mesto2"));
        assertFalse(s.equals(s2));
    }
   
    @Test
    public void testToString() {
        Stanica s1=new Stanica(10, "naziv", new Mesto(10, "mesto"));
        String tostring=s1.toString();
        assertTrue(tostring.contains("naziv"));
     
    }
}
