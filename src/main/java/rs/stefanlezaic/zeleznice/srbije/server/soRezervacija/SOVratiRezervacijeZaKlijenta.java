/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soRezervacija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiPolazak;

/**
 * Klasa SOVratiRezervacijeZaKlijenta koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca sve rezervacije klijenta.
 *
 * @author sleza
 */
public class SOVratiRezervacijeZaKlijenta extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> rezervacije;
    
     /**
     * Proverava da li je objekat klase rezervacije i ako nije baca exception.
     *
     * @param entity - objekat klase Rezervacije.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji se koriste za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Rezervacija)) {
            throw new Exception("Pogresni parametri!");
        }
        Rezervacija r = (Rezervacija) entity;
        if (r.getKlijent().getKlijentID() <= 0) {
            throw new InvalidProductException("Parametri pogresni!");
        }

    }
    /**
     * Izvršava upit(Select) nad bazom podataka
     *
     * @param entity - objekat klase Rezervacija.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            rezervacije = databaseBroker.findRecords(new Rezervacija(), ((Rezervacija) entity).getKlijent());
            for (GeneralEntity generalEntity : rezervacije) {
                Rezervacija r = (Rezervacija) generalEntity;
                AbstractGenericOperation op3 = new SOVratiKlijenta();
                op3.templateExecute(new Klijent(r.getKlijent().getKlijentID()));
                Klijent k = (Klijent) ((SOVratiKlijenta) op3).getKlijent();
                r.setKlijent(k);
                AbstractGenericOperation op4 = new SOVratiPolazak();
                op4.templateExecute(new Polazak(r.getPolazak().getPolazakID()));
                Polazak p = (Polazak) ((SOVratiPolazak) op4).getPolazak();
                r.setPolazak(p);
            }
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
     /**
     * Vraca List GeneralEntity (Rezervacija) rezultat pretrage nad bazom podataka.
     * 
     * @return List GeneralEntity(Rezervacija).
     */
    public List<GeneralEntity> getRezervacije() {
        return rezervacije;
    }

}
