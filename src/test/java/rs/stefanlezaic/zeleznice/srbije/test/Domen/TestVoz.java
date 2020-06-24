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
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;

/**
 *
 * @author Stefan
 */
public class TestVoz {
    
    Voz v;
    Voz  v2;
    

    @Before
    public void setUp() {
        v=new Voz();
        v2=new Voz();
    }

    @After
    public void tearDown() {
        v=null;
        v2=null;
    }
    
    
    @Test
    public void testVozKonstruktor(){
        v=new Voz(10);
        assertTrue(v.getVozID()==10);
    }
    
     @Test
    public void testVozKonstruktor2(){
        v=new Voz(10, "naziv", 10);
        assertTrue(v.getVozID()==10 && v.getNaziv()=="naziv" && v.getBrojSedista()==10);
        
    }
    
    @Test
    public void testVozEquals () {
        Voz v=new Voz(10, "naziv", 10);
        Voz v2=new Voz(10, "naziv", 10);
        assertTrue(v.equals(v2));
    }

    @Test
    public void testVozNonEquals() {
        Voz v=new Voz(101, "naziv", 10);
        Voz v2=new Voz(10, "naziv", 10);
        assertFalse(v.equals(v2));
    }

    @Test
    public void testToString() {
         Voz v=new Voz(101, "naziv", 10);
        String tostring=v.toString();
        assertTrue(tostring.contains("naziv"));
     
    }
}
