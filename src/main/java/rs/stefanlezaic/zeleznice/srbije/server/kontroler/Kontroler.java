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
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import java.sql.SQLException;
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

    public ArrayList<Polazak> vratiMiSvePolaske(Date datum) throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        ArrayList<Polazak> listaPolazaka = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Polazak p = (Polazak) generalEntity;
            listaPolazaka.add(p);
        }
        return listaPolazaka;
    }

    public ArrayList<Stanica> vratiMiSveStanice() throws Exception {
        AbstractGenericOperation op5 = new SOVratiSveStanice();
        op5.templateExecute(new Stanica());
        List<GeneralEntity> lista = ((SOVratiSveStanice) op5).getStanice();
        ArrayList<Stanica> listaStanica = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Stanica s = (Stanica) generalEntity;
            listaStanica.add(s);
        }
        return listaStanica;
    }

    public ArrayList<Klijent> vratiMiSveKlijente() throws Exception {
        AbstractGenericOperation op7 = new SOVratiSveKlijente();
        op7.templateExecute(new Klijent());
        List<GeneralEntity> lista = ((SOVratiSveKlijente) op7).getKlijenti();
        ArrayList<Klijent> listaKlijenta = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Klijent k = (Klijent) generalEntity;
            listaKlijenta.add(k);
        }
        return listaKlijenta;
    }

    public ArrayList<Linija> vratiMiSveLinije() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveLinije();
        op6.templateExecute(new Linija());
        List<GeneralEntity> lista = ((SOVratiSveLinije) op6).getLinije();
        ArrayList<Linija> listaLinija = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Linija l = (Linija) generalEntity;
            listaLinija.add(l);
        }
        return listaLinija;
    }

    public ArrayList<TipLinije> vratiMiSveTipoveLinije() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveTipoveLinija();
        op6.templateExecute(new TipLinije());
        ArrayList<TipLinije> listaTipovaLinija = new ArrayList<>();
        List<GeneralEntity> lista = ((SOVratiSveTipoveLinija) op6).getTipoviLinija();
        for (GeneralEntity generalEntity : lista) {
            TipLinije tp = (TipLinije) generalEntity;
            listaTipovaLinija.add(tp);
        }
        return listaTipovaLinija;
    }

    public ArrayList<Voz> vratiMiSveVozove() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSveVozove();
        op6.templateExecute(new Voz());
        ArrayList<Voz> listaVozova = new ArrayList<>();
        List<GeneralEntity> lista = ((SOVratiSveVozove) op6).getVozovi();
        for (GeneralEntity generalEntity : lista) {
            Voz v = (Voz) generalEntity;
            listaVozova.add(v);
        }
        return listaVozova;
    }

    public ArrayList<Mesto> vratiListuMesta() throws Exception {
        AbstractGenericOperation op3 = new SOVratiSvaMesta();
        op3.templateExecute(new Mesto());
        ArrayList<Mesto> listaMesta = new ArrayList<>();
        List<GeneralEntity> lista = ((SOVratiSvaMesta) op3).getMesta();
        for (GeneralEntity generalEntity : lista) {
            Mesto m = (Mesto) generalEntity;
            listaMesta.add(m);
        }
        return listaMesta;
    }

    public ArrayList<Polazak> vratiListuPolazaka() throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        ArrayList<Polazak> listaPolazaka = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Polazak p = (Polazak) generalEntity;
            listaPolazaka.add(p);
        }
        return listaPolazaka;
    }

    public ArrayList<Polazak> vratiListuPolazaka(String upit) throws Exception {
        AbstractGenericOperation op6 = new SOVratiSvePolaske();
        op6.templateExecute(new Polazak());
        List<GeneralEntity> lista = ((SOVratiSvePolaske) op6).getPolasci();
        ArrayList<Polazak> listaPolazaka = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Polazak p = (Polazak) generalEntity;
            listaPolazaka.add(p);
        }
        return listaPolazaka;
    }

    public ArrayList<Polazak> vratiMiPolaskeZaDatum(Polazak p) throws Exception {
        AbstractGenericOperation op = new SOVratiPolazakZaDatum();
        op.templateExecute(p);
        List<GeneralEntity> lista = ((SOVratiPolazakZaDatum) op).getPolasci();
        ArrayList<Polazak> listaPolazaka = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Polazak p1 = (Polazak) generalEntity;
            listaPolazaka.add(p1);
        }
        return listaPolazaka;
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

    public ArrayList<Rezervacija> vratiRezervacijePolaska(Rezervacija r) throws Exception {
        AbstractGenericOperation op7 = new SOVratiRezervacijeZaPolazak();
        op7.templateExecute(r);
        List<GeneralEntity> lista = ((SOVratiRezervacijeZaPolazak) op7).getRezervacije();
        ArrayList<Rezervacija> listaRezervacija = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Rezervacija rezervacija = (Rezervacija) generalEntity;
            listaRezervacija.add(rezervacija);
        }
        return listaRezervacija;
    }

    public ArrayList<Rezervacija> vratiRezervacijeKlijenta(Rezervacija r) throws Exception {
        AbstractGenericOperation op7 = new SOVratiRezervacijeZaKlijenta();
        op7.templateExecute(r);
        List<GeneralEntity> lista = ((SOVratiRezervacijeZaKlijenta) op7).getRezervacije();
        ArrayList<Rezervacija> listaRezervacija = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            Rezervacija rezervacija = (Rezervacija) generalEntity;
            listaRezervacija.add(rezervacija);
        }
        return listaRezervacija;
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

    public ArrayList<MedjuStanica> vratiMISveMedjuStanice() throws Exception {
        AbstractGenericOperation op = new SOVratiSveMedjustanice();
        op.templateExecute(new MedjuStanica());
        List<GeneralEntity> lista = ((SOVratiSveMedjustanice) op).getMedjustanice();
        ArrayList<MedjuStanica> listaMedjustanica = new ArrayList<>();
        for (GeneralEntity generalEntity : lista) {
            MedjuStanica medjuStanica = (MedjuStanica) generalEntity;
            listaMedjustanica.add(medjuStanica);
        }
        return listaMedjustanica;
    }

    public ArrayList<MedjuStanica> vratiMiSveMedjustaniceZaLiniju(MedjuStanica medjustanica) throws Exception {
        AbstractGenericOperation op5 = new SOVratiSveMedjustaniceZaLiniju();
        op5.templateExecute(medjustanica);
        ArrayList<MedjuStanica> listaMedjuStanica = new ArrayList<>();
        List<GeneralEntity> lista = ((SOVratiSveMedjustaniceZaLiniju) op5).getMedjustanice();
        for (GeneralEntity generalEntity : lista) {
            MedjuStanica m = (MedjuStanica) generalEntity;
            listaMedjuStanica.add(m);
        }
        return listaMedjuStanica;
    }

}
