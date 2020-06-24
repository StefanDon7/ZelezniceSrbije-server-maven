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
public class TestTipLinije {
    
    TipLinije tl;
    TipLinije tl2;
    

    @Before
    public void setUp() {
        tl=new TipLinije();
        tl2=new TipLinije();
    }

    @After
    public void tearDown() {
        tl=null;
        tl2=null;
    }
    
    
    @Test
    public void testTipLinijeKonstruktor(){
        tl=new TipLinije(10);
        assertTrue(tl.getTipLinijeID()==10);
    }
    
    @Test
    public void testTipLinijeKonstruktor2(){
        tl=new TipLinije(10, "naziv");
        assertTrue(tl.getTipLinijeID()==10 && tl.getNaziv()=="naziv");
    }
    
    @Test
    public void testTipLinijeEquals () {
          tl=new TipLinije(10, "naziv");
          tl2=new TipLinije(10, "naziv");
        assertTrue(tl.equals(tl));
    }

    @Test
    public void testVozNonEquals() {
        Voz v=new Voz(10, "naziv", 10);
        Voz v2=new Voz(101, "naziv", 10);
        assertFalse(v.equals(v2));
    }

    @Test
    public void testToString() {
        Voz v=new Voz(10, "naziv", 10);
        String tostring=v.toString();
        assertTrue(tostring.contains("naziv"));
     
    }
}
