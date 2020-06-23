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

/**
 *
 * @author sleza
 */
public class TestKlijent {

    Klijent k;
    Klijent k2;
    

    @Before
    public void setUp() {
        k = new Klijent();
        k2 = new Klijent();
    }

    @After
    public void tearDown() {
        k = null;
        k2 = null;
    }
    
    
    @Test
    public void testKlijentKonstruktora(){
        k=new Klijent(10);
        assertTrue(k.getKlijentID()==10);
    }
    
     @Test
    public void testKlijentKonstruktora2(){
        k=new Klijent("zikazikic@gmail.com", "lozinka123");
        assertTrue(k.getEmail()=="zikazikic@gmail.com" && k.getLozinka()=="lozinka123");
    }
     @Test
    public void testKlijentKonstruktora3(){
        k=new Klijent(1234, "zikazikic", "lozinka123", "Zika", "Zikic", "zikazikic@gmail.com");
        assertTrue(k.getEmail()=="zikazikic@gmail.com" && k.getLozinka()=="lozinka123" && 
                k.getIme()=="Zika" && k.getPrezime()=="Zikic" && k.getKorisnickoIme()=="zikazikic" && k.getKlijentID()==1234);
    }
    

    @Test
    public void testKlijentiSuIsti() {
        k.setEmail("email@gmail.com");
        k2.setEmail("email@gmail.com");
        assertTrue(k.equals(k2));
    }

    @Test
    public void testKlijentiNisuIsti() {
        k.setEmail("gmail@gmail.com");
        k2.setEmail("email@gmail.com");
        assertFalse(k.equals(k2));
    }

    @Test
    public void testKlijentiJedanNull() {
        assertFalse(k.equals(null));
    }

    @Test
    public void testKlijentiNijeInstanca() {
        assertFalse(k.equals(new Object()));
    }

    @Test
    public void testToString() {
        k.setIme("Mika");
        k.setPrezime("Mikic");
        String s = k.toString();
        assertTrue(s.contains("Mika"));
        assertTrue(s.contains("Mikic"));
    }
   
}
