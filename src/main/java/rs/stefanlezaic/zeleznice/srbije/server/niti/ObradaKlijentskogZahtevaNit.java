package rs.stefanlezaic.zeleznice.srbije.server.niti;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.kons.Konstante;
import rs.stefanlezaic.zeleznice.srbije.lib.kons.ResponseStatus;
import rs.stefanlezaic.zeleznice.srbije.server.kontroler.Kontroler;
import rs.stefanlezaic.zeleznice.srbije.lib.transfer.KlijentskiZahtev;
import rs.stefanlezaic.zeleznice.srbije.lib.transfer.ServerskiOdgovor;

/**
 *
 * @author sleza
 */
public class ObradaKlijentskogZahtevaNit extends Thread {

    Socket klijentskiSoket;
    boolean kraj = false;

    public ObradaKlijentskogZahtevaNit(Socket klijentskiSoket) {
        this.klijentskiSoket = klijentskiSoket;
    }

    @Override
    public void run() {
        try {
            handleRequest();
        } catch (java.net.SocketException ex) {
            System.out.println("Klijent se odjavio!");
        }  catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskogZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskogZahtevaNit.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    private void handleRequest() throws IOException, ClassNotFoundException {
        while (!isInterrupted()) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            try {
                switch (kz.getOperacija()) {
                    case Konstante.REGISTRACIJA:
                        Klijent kzKlijentRegistracija = (Klijent) kz.getParametar();
                        Kontroler.getInstance().sacuvajKlijenta(kzKlijentRegistracija);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.PRIJAVLJIVANJE:
                        Klijent kzKlijentPrijavljivanje = (Klijent) kz.getParametar();
                        Klijent soKlijentPrijavljivanje = Kontroler.getInstance().vratiMiKlijentaNaOsnovuEmailILozinke(kzKlijentPrijavljivanje);
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(soKlijentPrijavljivanje);
                        break;
                    case Konstante.VRATI_MEDJUSTANICE:
                        List<GeneralEntity> listaMedjustanica = Kontroler.getInstance().vratiMISveMedjuStanice();
                        so.setOdgovor(listaMedjustanica);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_LINIJE:
                        List<GeneralEntity> listaLinija = Kontroler.getInstance().vratiMiSveLinije();
                        so.setOdgovor(listaLinija);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_MESTA:
                        List<GeneralEntity> listaMesta = Kontroler.getInstance().vratiListuMesta();
                        so.setOdgovor(listaMesta);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_VOZOVE:
                        List<GeneralEntity> listaVozova = Kontroler.getInstance().vratiMiSveVozove();
                        so.setOdgovor(listaVozova);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_TIPOVE_LINIJA:
                        List<GeneralEntity> listaTipovaLinije = Kontroler.getInstance().vratiMiSveTipoveLinije();
                        so.setOdgovor(listaTipovaLinije);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_MEDJUSTANICE_LINIJE:
                        MedjuStanica m = (MedjuStanica) kz.getParametar();
                        List<GeneralEntity> listaMedjustanicaZaLiniju = Kontroler.getInstance().vratiMiSveMedjustaniceZaLiniju(m);
                        so.setOdgovor(listaMedjustanicaZaLiniju);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_POLASKE_ZA_DATUM:
                        Polazak polazakSaDatumom = (Polazak) kz.getParametar();
                        List<GeneralEntity> listaPolazaka = Kontroler.getInstance().vratiMiPolaskeZaDatum(polazakSaDatumom);
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(listaPolazaka);
                        break;
                    case Konstante.VRATI_POLASKE:
                        List<GeneralEntity> sviPolasci = Kontroler.getInstance().vratiSvePolaske();
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(sviPolasci);
                        break;
                    case Konstante.VRATI_POLASKE_ZA_POCETNU_I_KRAJNJU_STANICU:
                        Polazak p = (Polazak) kz.getParametar();
                        List<GeneralEntity> listaPolazakaZaPocentuIKrajnjuStanicu = Kontroler.getInstance().vratiListuPolazakaZaPocetnuIKrajnjuStanicu(p);
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(listaPolazakaZaPocentuIKrajnjuStanicu);
                        break;
                    case Konstante.VRATI_STANICE:
                        List<GeneralEntity> listaStanica = Kontroler.getInstance().vratiMiSveStanice();
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(listaStanica);
                        break;
                    case Konstante.REZERVISI_POLAZAK:
                        Rezervacija kzRezervacija = (Rezervacija) kz.getParametar();
                        Kontroler.getInstance().rezervisiPolazak(kzRezervacija);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.IZMENA_KORISNICKOG_NALOGA:
                        Klijent kzKlijentPromenaNaloga = (Klijent) kz.getParametar();
                        Kontroler.getInstance().izmeniKlijenta(kzKlijentPromenaNaloga);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_REZERVACIJE_ZA_KLIJENTA:
                        Rezervacija kzRezervacijaZaKlijenta = (Rezervacija) kz.getParametar();
                        List<GeneralEntity> solistRezervacijaZaKlijenta = Kontroler.getInstance().vratiRezervacijeKlijenta(kzRezervacijaZaKlijenta);
                        so.setOdgovor(solistRezervacijaZaKlijenta);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_REZERVACIJE_ZA_POLAZAK:
                        Rezervacija kzRezervacijaZaPolazak = (Rezervacija) kz.getParametar();
                        List<GeneralEntity> listaRezervacijaZaPolazak = Kontroler.getInstance().vratiRezervacijePolaska(kzRezervacijaZaPolazak);
                        so.setOdgovor(listaRezervacijaZaPolazak);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.OTKAZI_REZERVACIJU:
                        Rezervacija kzRezervacijaZaOtkazivanje = (Rezervacija) kz.getParametar();
                        Kontroler.getInstance().otkaziRezervaciju(kzRezervacijaZaOtkazivanje);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.IZMENI_POLASKE:
                        ArrayList<Polazak> listaPolazakaZaMenjanje = (ArrayList<Polazak>) kz.getParametar();
                        Kontroler.getInstance().updejtujMiPolaske(listaPolazakaZaMenjanje);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.OBRISI_POLAZAK:
                        Polazak polazakZaBrisanje = (Polazak) kz.getParametar();
                        Kontroler.getInstance().obrisiPolazak(polazakZaBrisanje);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_LINIJU_NA_OSNOVU_STANICE_I_TIPA:
                        Linija linijaZaPretrazivanje = (Linija) kz.getParametar();
                        Kontroler.getInstance().vratiLinijuNaOsnovuStanicaITipa(linijaZaPretrazivanje);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.UNESI_STANICU:
                        Stanica stanicaZaUnos = (Stanica) kz.getParametar();
                        Kontroler.getInstance().unesiNovuStanicu(stanicaZaUnos);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.OBRISI_LINIJU:
                        Linija linijaZaBrisanja = (Linija) kz.getParametar();
                        Kontroler.getInstance().obrisiLiniju(linijaZaBrisanja);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.UNESI_SVE_POLASKE:
                        ArrayList<Polazak> listaPolazakaZaUnos = (ArrayList<Polazak>) kz.getParametar();
                        Kontroler.getInstance().unesiSvePolazke(listaPolazakaZaUnos);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.IZMENI_SVE_MEDJUSTANICE:
                        ArrayList<MedjuStanica> medjustaniceZaIzmenu = (ArrayList<MedjuStanica>) kz.getParametar();
                        Kontroler.getInstance().izmeniSveMedjustanice(medjustaniceZaIzmenu);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.UNESI_LINIJU:
                        Linija linijaZaUnos = (Linija) kz.getParametar();
                        Kontroler.getInstance().unesiLiniju(linijaZaUnos);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.OBRISI_MEDJUSTANICU:
                        MedjuStanica medjustanicaZaBrisanje = (MedjuStanica) kz.getParametar();
                        Kontroler.getInstance().obrisiMedjustanicu(medjustanicaZaBrisanje);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.UNESI_MEDJUSTANICU:
                        MedjuStanica medjustanicaZaUnos = (MedjuStanica) kz.getParametar();
                        Kontroler.getInstance().unesiMedjustanicu(medjustanicaZaUnos);
                        so.setStatus(ResponseStatus.OK);
                        break;

                }
            } catch (Exception ex) {
                so.setPoruka("GRESKA!");
                so.setError(ex);
                so.setStatus(ResponseStatus.ERROR);
                ex.printStackTrace();
            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(klijentskiSoket.getInputStream());
        return (KlijentskiZahtev) ois.readObject();

    }

    private void posaljiOdgovor(ServerskiOdgovor so) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(klijentskiSoket.getOutputStream());
        oos.writeObject(so);
    }

}
