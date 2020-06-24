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
public class TestMesto {
    
    Mesto m;
    Mesto m2;
    

    @Before
    public void setUp() {
        m=new Mesto();
        m2=new Mesto();
    }

    @After
    public void tearDown() {
        m=null;
        m2=null;
    }
    
    
    @Test
    public void testMestoKonstruktor(){
        m=new Mesto(10);
        assertTrue(m.getMestoID()==10);
    }
    
     @Test
    public void testMestoKonstruktor2(){
        Mesto m=new Mesto(10, "mesto");
        assertTrue(m.getMestoID()==10 && m.getNaziv()=="mesto");
        
    }
    
    @Test
    public void testMestoEquals () {
        m=new Mesto(10, "mesto");
        m2= new Mesto(10, "mesto");
        assertTrue(m.equals(m2));
    }

    @Test
    public void testMestaNonEquals() {
         m=new Mesto(10, "mesto");
        m2=new Mesto(102, "mesto");
        assertFalse(m.equals(m2));
    }

    @Test
    public void testToString() {
        m=new Mesto(10, "mesto");
        String tostring=m.toString();
        assertTrue(tostring.contains("mesto"));
     
    }
}
