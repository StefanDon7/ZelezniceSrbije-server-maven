/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soPolazak;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.DeleteEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOObrisiPolazak koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Brise polazak iz baze.Polazak je dozvoljeno obrisati samo 
 * ako za njega ne postoji ni jedan rezervacija.
 *
 * @author sleza
 */
public class SOObrisiPolazak extends AbstractGenericOperation {
     /**
     * Proverava da li je objekat klase Polazak i ako nije baca exception.
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * 
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Polazak)) {
            throw new Exception("Pogresni parametri!");
        }
        Polazak p = (Polazak) entity;
        if (p.getPolazakID() <= -1) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }
    /**
     * Izvršava upit(DELETE) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> DeleteEntityException - Sistem ne moze da obrise Polazak!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.deleteRecord((GeneralEntity) entity);
        } catch (SQLException | DeleteEntityException ex) {
            throw new Exception("Sistem ne može da obrise liniju");
        }
    }

}
