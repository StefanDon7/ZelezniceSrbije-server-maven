/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.kontroler;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOIzmeniKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOUnesiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijentaBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiSveKlijente;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOObrisiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOUnesiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLinijuBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiSveLinije;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOIzmeniMedjustanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOObrisiMedjustanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOUnesiMedjustanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiMedjustanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiSveMedjustanice;
import rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica.SOVratiSveMedjustaniceZaLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soMesta.SOVratiSvaMesta;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOIzmeniPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOObrisiPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOUnesiPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiPolazakZaDatum;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiSvePolaske;
import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOObrisiRezervaciju;
import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOUnesiRezervaciju;
import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervacijeZaKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervacijeZaPolazak;
import rs.stefanlezaic.zeleznice.srbije.server.soRezervacija.SOVratiRezervaciju;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOUnesiStanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicuBezPrimarnogKljuca;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiSveStanice;
import rs.stefanlezaic.zeleznice.srbije.server.soTipLinije.SOVratiSveTipoveLinija;
import rs.stefanlezaic.zeleznice.srbije.server.soVoz.SOVratiSveVozove;

/**
 *
 * @author sleza
 */
public class Kontroler {

    private static Kontroler instanca;

    private Kontroler() {
    }

    public static Kontroler getInstance() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public List<GeneralEntity> vratiMiSvePolaske(Date datum) throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveStanice() throws Exception {
        AbstractGenericOperation op5 = new SOVratiSveStanice();
        op5.templateExecute(new Stanica());
        List<GeneralEntity> lista = ((SOVratiSveStanice) op5).getStanice();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveKlijente() throws Exception {
        AbstractGenericOperation op7 = new SOVratiSveKlijente();
        op7.templateExecute(new Klijent());
        List<GeneralEntity> lista = ((SOVratiSveKlijente) op7).getKlijenti();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveLinije() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveLinije();
        op6.templateExecute(new Linija());
        List<GeneralEntity> lista = ((SOVratiSveLinije) op6).getLinije();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveTipoveLinije() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveTipoveLinija();
        op6.templateExecute(new TipLinije());
        List<GeneralEntity> lista = ((SOVratiSveTipoveLinija) op6).getTipoviLinija();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveVozove() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveVozove();
        op6.templateExecute(new Voz());
        List<GeneralEntity> lista = ((SOVratiSveVozove) op6).getVozovi();
        return lista;
    }

    public List<GeneralEntity> vratiListuMesta() throws Exception {
        AbstractGenericOperation op3 = new SOVratiSvaMesta();
        op3.templateExecute(new Mesto());
        List<GeneralEntity> lista = ((SOVratiSvaMesta) op3).getMesta();
        return lista;
    }

    public List<GeneralEntity> vratiListuPolazaka() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        return lista;
    }

    public List<GeneralEntity> vratiListuPolazaka(String upit) throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        return lista;
    }

    public List<GeneralEntity> vratiMiPolaskeZaDatum(Polazak p) throws Exception {
        AbstractGenericOperation op = new SOVratiPolazakZaDatum();
        op.templateExecute(p);
        List<GeneralEntity> lista = ((SOVratiPolazakZaDatum) op).getPolasci();
        return lista;
    }

    public Klijent vratiMiKlijenta(Klijent klijent) throws Exception {
        AbstractGenericOperation op7 = new SOVratiKlijenta();
        op7.templateExecute(klijent);
        return (Klijent) ((SOVratiKlijenta) op7).getKlijent();
    }

    public void obrisiMedjustanicu(MedjuStanica m) throws Exception {
        AbstractGenericOperation op6 = new SOObrisiMedjustanicu();
        op6.templateExecute(m);
    }

    public void unesiMedjustanicu(MedjuStanica medjuStanica) throws Exception {
        AbstractGenericOperation op5 = new SOUnesiMedjustanicu();
        op5.templateExecute(medjuStanica);
    }

    public void izmeniSveMedjustanice(ArrayList<MedjuStanica> list) throws Exception {
        for (MedjuStanica medjuStanica : list) {
            AbstractGenericOperation op5 = new SOIzmeniMedjustanicu();
            op5.templateExecute(medjuStanica);
        }
    }

    public void unesiSvePolazke(ArrayList<Polazak> list) throws Exception {
        for (Polazak polazak : list) {
            AbstractGenericOperation op6 = new SOUnesiPolazak();
            op6.templateExecute(polazak);
        }
    }

    public void updejtujMiPolaske(ArrayList<Polazak> polasciZaMenjanje) throws Exception {
        for (Polazak polazak : polasciZaMenjanje) {
            AbstractGenericOperation op6 = new SOIzmeniPolazak();
            op6.templateExecute(polazak);
        }
    }

    public void obrisiLiniju(Linija l) throws Exception {
        AbstractGenericOperation op6 = new SOObrisiLiniju();
        op6.templateExecute(l);
    }

    public void unesiNovuStanicu(Stanica s) throws Exception {
        AbstractGenericOperation op6 = new SOUnesiStanicu();
        op6.templateExecute(s);
    }

    public void obrisiPolazak(Polazak p) throws Exception {
        AbstractGenericOperation op6 = new SOObrisiPolazak();
        op6.templateExecute(p);
    }

    public void unesiLiniju(Linija l) throws Exception {
        AbstractGenericOperation op = new SOUnesiLiniju();
        op.templateExecute(l);
    }

    public void rezervisiPolazak(Rezervacija r) throws Exception {
        AbstractGenericOperation op2 = new SOUnesiRezervaciju();
        op2.templateExecute(r);
    }

    public void izmeniKlijenta(Klijent klijent) throws Exception {
        AbstractGenericOperation op2 = new SOIzmeniKlijenta();
        op2.templateExecute(klijent);
    }

    public void sacuvajKlijenta(Klijent k) throws Exception {
        AbstractGenericOperation op = new SOUnesiKlijenta();
        op.templateExecute(k);
    }

    public void otkaziRezervaciju(Rezervacija rez) throws Exception {
        AbstractGenericOperation op6 = new SOObrisiRezervaciju();
        op6.templateExecute(rez);
    }

    public MedjuStanica vratiMiMedjustanicu(MedjuStanica m) throws Exception {
        AbstractGenericOperation op7 = new SOVratiMedjustanicu();
        op7.templateExecute(m);
        return (MedjuStanica) ((SOVratiMedjustanicu) op7).getMedjustanica();
    }

    public Linija vratiMiLiniju(Linija l) throws Exception {
        AbstractGenericOperation op7 = new SOVratiLiniju();
        op7.templateExecute(l);
        return (Linija) ((SOVratiLiniju) op7).getLinija();
    }

    public Stanica vratiMiStanicu(Stanica s) throws Exception {
        AbstractGenericOperation op7 = new SOVratiStanicu();
        op7.templateExecute(s);
        return (Stanica) ((SOVratiStanicu) op7).getStanica();
    }

    public Rezervacija vratiMiRezervaciju(Rezervacija r) throws Exception {
        AbstractGenericOperation op7 = new SOVratiRezervaciju();
        op7.templateExecute(r);
        return (Rezervacija) ((SOVratiRezervaciju) op7).getRezervacija();
    }

    public List<GeneralEntity> vratiRezervacijePolaska(Rezervacija r) throws Exception {
        AbstractGenericOperation op7 = new SOVratiRezervacijeZaPolazak();
        op7.templateExecute(r);
        List<GeneralEntity> lista = ((SOVratiRezervacijeZaPolazak) op7).getRezervacije();
        return lista;
    }

    public List<GeneralEntity> vratiRezervacijeKlijenta(Rezervacija r) throws Exception {
        AbstractGenericOperation op7 = new SOVratiRezervacijeZaKlijenta();
        op7.templateExecute(r);
        List<GeneralEntity> lista = ((SOVratiRezervacijeZaKlijenta) op7).getRezervacije();
        return lista;
    }

    public Stanica vratiStanicuNaOsnovuNazivaIMesta(Stanica s) throws Exception {
        AbstractGenericOperation op7 = new SOVratiStanicuBezPrimarnogKljuca();
        op7.templateExecute(s);
        return (Stanica) ((SOVratiStanicuBezPrimarnogKljuca) op7).getStanica();
    }

    public Linija vratiLinijuNaOsnovuStanicaITipa(Linija l) throws Exception {
        AbstractGenericOperation op7 = new SOVratiLinijuBezPrimarnogKljuca();
        op7.templateExecute(l);
        return (Linija) ((SOVratiLinijuBezPrimarnogKljuca) op7).getLinija();
    }

    public Klijent vratiMiKlijentaNaOsnovuEmailILozinke(Klijent klijent) throws Exception {
        AbstractGenericOperation op7 = new SOVratiKlijentaBezPrimarnogKljuca();
        op7.templateExecute(klijent);
        return (Klijent) ((SOVratiKlijentaBezPrimarnogKljuca) op7).getKlijent();
    }

    public List<GeneralEntity> vratiMISveMedjuStanice() throws Exception {
        AbstractGenericOperation op = new SOVratiSveMedjustanice();
        op.templateExecute(new MedjuStanica());
        List<GeneralEntity> lista = ((SOVratiSveMedjustanice) op).getMedjustanice();
        return lista;
    }

    public List<GeneralEntity> vratiMiSveMedjustaniceZaLiniju(MedjuStanica medjustanica) throws Exception {
        AbstractGenericOperation op5 = new SOVratiSveMedjustaniceZaLiniju();
        op5.templateExecute(medjustanica);
        List<GeneralEntity> lista = ((SOVratiSveMedjustaniceZaLiniju) op5).getMedjustanice();
        return lista;
    }

}
