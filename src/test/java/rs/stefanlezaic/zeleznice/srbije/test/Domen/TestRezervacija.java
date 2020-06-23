/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.test.Domen;

import java.util.Date;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;

/**
 *
 * @author Stefan
 */
public class TestRezervacija {
        
    Rezervacija r;
    Rezervacija r2;
    

    @Before
    public void setUp() {
        r=new Rezervacija();
        r2=new Rezervacija();
    }

    @After
    public void tearDown() {
      r=null;
      r2=null;
    }
    
    @Test
    public void testRezervacijeSuIsti() {
        r.setPolazak(new Polazak(1));
        r.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        r2.setPolazak(new Polazak(1));
        r2.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        assertTrue(r.equals(r2));
    }

    @Test
    public void testRezervacijeNisuIste() {
        r.setPolazak(new Polazak(1));
        r.setKlijent(new Klijent(-1, "", "", "", "", "zika@gmail.com"));
        r2.setPolazak(new Polazak(1));
        r2.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        assertFalse(r.equals(r2));
    }
    @Test
    public void testRezervacijeNisuIste2() {
        r.setPolazak(new Polazak(1));
        r.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        r2.setPolazak(new Polazak(2));
        r2.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        assertFalse(r.equals(r2));
    }
    @Test
      public void testRezervacijeNisuIste3() {
        r.setPolazak(new Polazak(1));
        r.setKlijent(new Klijent(-1, "", "", "", "", "zika@gmail.com"));
        r2.setPolazak(new Polazak(2));
        r2.setKlijent(new Klijent(-1, "", "", "", "", "mika@gmail.com"));
        assertFalse(r.equals(r2));
    }
      

    @Test
    public void testKlijentiJedanNull() {
        assertFalse(r.equals(null));
    }

    @Test
    public void testKlijentiNijeInstanca() {
        assertFalse(r.equals(new Object()));
    }

    @Test
    public void testToString() {
        r.setPolazak(new Polazak(-1, "Naziv", new Date(), new Date(), new Linija(), new Voz()));
        r.setKlijent(new Klijent(-1, "Zikica", "", "Zika", "Zikic", "zika@gmail.com"));
        String s = r.toString();
        assertTrue(s.contains("Zika"));
        assertTrue(s.contains("Zikic"));
        assertTrue(s.contains("Naziv"));
    }

}
