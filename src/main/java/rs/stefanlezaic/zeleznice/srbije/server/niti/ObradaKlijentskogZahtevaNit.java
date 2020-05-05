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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        } catch (IOException ex) {
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
                        ArrayList<MedjuStanica> listaMedjustanica = Kontroler.getInstance().vratiMISveMedjuStanice();
                        so.setOdgovor(listaMedjustanica);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_MEDJUSTANICE_LINIJE:
                        MedjuStanica m = (MedjuStanica) kz.getParametar();
                        ArrayList<MedjuStanica> listaMedjustanicaZaLiniju = Kontroler.getInstance().vratiMiSveMedjustaniceZaLiniju(m);
                        so.setOdgovor(listaMedjustanicaZaLiniju);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_POLASKE:
                        Polazak p = (Polazak) kz.getParametar();
                        ArrayList<Polazak> listaPolazaka = Kontroler.getInstance().vratiMiPolaskeZaDatum(p);
                        so.setStatus(ResponseStatus.OK);
                        so.setOdgovor(listaPolazaka);
                        break;
                    case Konstante.VRATI_STANICE:
                        ArrayList<Stanica> listaStanica = Kontroler.getInstance().vratiMiSveStanice();
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
                        ArrayList<Rezervacija> solistRezervacijaZaKlijenta = Kontroler.getInstance().vratiRezervacijeKlijenta(kzRezervacijaZaKlijenta);
                        so.setOdgovor(solistRezervacijaZaKlijenta);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.VRATI_REZERVACIJE_ZA_POLAZAK:
                        Rezervacija kzRezervacijaZaPolazak = (Rezervacija) kz.getParametar();
                        ArrayList<Rezervacija> listaRezervacijaZaPolazak = Kontroler.getInstance().vratiRezervacijePolaska(kzRezervacijaZaPolazak);
                        so.setOdgovor(listaRezervacijaZaPolazak);
                        so.setStatus(ResponseStatus.OK);
                        break;
                    case Konstante.OTKAZI_REZERVACIJU:
                        Rezervacija kzRezervacijaZaOtkazivanje = (Rezervacija) kz.getParametar();
                        Kontroler.getInstance().otkaziRezervaciju(kzRezervacijaZaOtkazivanje);
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
