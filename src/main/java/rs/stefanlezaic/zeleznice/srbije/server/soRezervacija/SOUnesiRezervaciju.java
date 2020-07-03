/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soRezervacija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import java.util.Date;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOUnesiRezervaciju koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Unosi rezervaciju u bazu.
 *
 * @author sleza
 */
public class SOUnesiRezervaciju extends AbstractGenericOperation {
     /**
     * Proverava da li je objekat klase Rezervacija i ako nije baca exception.
     *
     * @param entity - objekat klase Rezervacija.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Rezervacija)) {
            throw new Exception("Pogresni parametri!");
        }
        Rezervacija r = (Rezervacija) entity;
        if (r.getKlijent().getKlijentID() <= 0 || r.getPolazak().getPolazakID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
        if (r.getPolazak().getNapomena() != null && (r.getPolazak().getNapomena().contains("OTKAZANO") || r.getPolazak().getNapomena().contains("Otkazano") || r.getPolazak().getNapomena().contains("Otkazan"))) {
            throw new InvalidProductException("Polazak je otkazan. Ne mozete ga rezervisati!");
        }
        if (r.getPolazak().getDatumPolaska().before(new Date())) {
            throw new InvalidProductException("Ne mozete rezervisati kartu za polazak koji je vec realizovan!");
        }
        AbstractGenericOperation op = new SOVratiRezervacijeZaPolazak();
        op.templateExecute(r);
        int broj = (((SOVratiRezervacijeZaPolazak) op).getRezervacije()).size();
        if (broj == r.getPolazak().getVoz().getBrojSedista()) {
            throw new InvalidProductException("Sva mesta su rezervisana!");
        }
    }
     /**
     * Izvrsava upit(INSERT) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Rezervacija.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> InsertEntityException - Rezervacija vec postoji!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException | InsertEntityException ex) {
            throw new Exception("Sistem ne moze da unese rezervaciju");
        }
    }
}
