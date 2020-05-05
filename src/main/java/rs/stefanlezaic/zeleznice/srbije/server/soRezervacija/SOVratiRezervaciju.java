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
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soKlijent.SOVratiKlijenta;
import rs.stefanlezaic.zeleznice.srbije.server.soPolazak.SOVratiPolazak;

/**
 *
 * @author sleza
 */
public class SOVratiRezervaciju extends AbstractGenericOperation {

    private GeneralEntity rezervacija;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Rezervacija)) {
            throw new Exception("Pogresni parametri!");
        }
        Rezervacija r = (Rezervacija) entity;
        if (r.getKlijent().getKlijentID() <= 0 || r.getPolazak().getPolazakID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            rezervacija = databaseBroker.findRecord((Rezervacija) entity);
            Rezervacija r = (Rezervacija) rezervacija;
            AbstractGenericOperation op3 = new SOVratiKlijenta();
            op3.templateExecute(new Klijent(r.getKlijent().getKlijentID()));
            Klijent k = (Klijent) ((SOVratiKlijenta) op3).getKlijent();
            r.setKlijent(k);
            AbstractGenericOperation op4 = new SOVratiPolazak();
            op4.templateExecute(new Polazak(r.getPolazak().getPolazakID()));
            Polazak p = (Polazak) ((SOVratiPolazak) op4).getPolazak();
            r.setPolazak(p);
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public GeneralEntity getRezervacija() {
        return rezervacija;
    }

}
