/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soRezervacija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Rezervacija;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.DeleteEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOObrisiRezervaciju koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Brise rezervaciju iz baze.
 *
 * @author sleza
 */
public class SOObrisiRezervaciju extends AbstractGenericOperation {
      /**
     * Proverava da li je objekat klase rezervacija i ako nije baca exception.
     *
     * @param entity - objekat klase Rezervacija.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * 
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

    }
     /**
     * Izvršava upit(DELETE) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Rezervacija.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> DeleteEntityException - Sistem ne moze da obrise rezervaciju!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.deleteRecord((GeneralEntity) entity);
        } catch (SQLException | DeleteEntityException ex) {
            throw new Exception("Sistem ne može da obrise rezervaciju");
        }
    }

}
