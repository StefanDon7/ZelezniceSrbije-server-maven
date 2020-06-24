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
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;

/**
 *
 * @author Stefan
 */
public class TestMedjustanica {
    
    MedjuStanica m;
   MedjuStanica m2;
    

    @Before
    public void setUp() {
       m=new MedjuStanica();
       m2=new MedjuStanica();
    }

    @After
    public void tearDown() {
        m=null;
        m2=null;
    }
    
    
    @Test
    public void testMedjustanicaKonstruktor(){
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        assertTrue(m.getStanica().equals(s3) && m.getLinija().equals(l) && m.getRedniBroj()==redniBroj);
    }
   
    
    @Test
    public void testMedjustanicaEquals () {
       Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        assertTrue(m.getStanica().equals(s3) && m.getLinija().equals(l) && m.getRedniBroj()==redniBroj);
        
        Stanica s4=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s5=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip2=new TipLinije(12, "tip");
        Linija l2=new Linija(10, "naziv", 123, 123, s4, s5, tip2);
        Stanica s6=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj2=5;
        m2=new MedjuStanica(s6, l2, redniBroj2);
        assertTrue(m.equals(m2));
    }

    @Test
    public void testMedjustanicaNonEquals() {
        Stanica s1=new Stanica(10, "stanica2", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        
        Stanica s4=new Stanica(10, "stanica", new Mesto(10, "mesto3"));
        Stanica s5=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip2=new TipLinije(12, "tip");
        Linija l2=new Linija(10, "naziv", 123, 123, s4, s5, tip2);
        Stanica s6=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj2=5;
        m2=new MedjuStanica(s6, l2, redniBroj2);
        assertFalse(m.equals(m2));
    }
    
        @Test
    public void testMedjustanicaNonEquals2() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica23", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        
        Stanica s4=new Stanica(10, "stanica", new Mesto(10, "mesto3"));
        Stanica s5=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip2=new TipLinije(12, "tip");
        Linija l2=new Linija(10, "naziv", 123, 123, s4, s5, tip2);
        Stanica s6=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj2=5;
        m2=new MedjuStanica(s6, l2, redniBroj2);
        assertFalse(m.equals(m2));
    }
            @Test
    public void testMedjustanicaNonEquals3() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica23", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(123, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        
        Stanica s4=new Stanica(10, "stanica", new Mesto(10, "mesto3"));
        Stanica s5=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip2=new TipLinije(12, "tip");
        Linija l2=new Linija(10, "naziv", 123, 123, s4, s5, tip2);
        Stanica s6=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj2=5;
        m2=new MedjuStanica(s6, l2, redniBroj2);
        assertFalse(m.equals(m2));
    }
    
                @Test
    public void testMedjustanicaNonEquals4() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica33", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        
        Stanica s4=new Stanica(10, "stanica", new Mesto(10, "mesto3"));
        Stanica s5=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip2=new TipLinije(12, "tip");
        Linija l2=new Linija(10, "naziv", 123, 123, s4, s5, tip2);
        Stanica s6=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj2=5;
        m2=new MedjuStanica(s6, l2, redniBroj2);
        assertFalse(m.equals(m2));
    }
    
    
    @Test
    public void testToString() {
        Stanica s1=new Stanica(10, "stanica", new Mesto(10, "mesto"));
        Stanica s2=new Stanica(101, "stanica2", new Mesto(101, "mesto2"));
        TipLinije tip=new TipLinije(12, "tip");
        Linija l=new Linija(10, "naziv", 123, 123, s1, s2, tip);
        Stanica s3=new Stanica(10, "stanica3", new Mesto(10, "mesto"));
        int redniBroj=5;
        m=new MedjuStanica(s3, l, redniBroj);
        String tostring=m.toString();
        assertTrue(tostring.contains(s3.getNaziv()) && tostring.contains("5"));
    }
}
