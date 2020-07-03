/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soPolazak;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.UpdateEntityException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOIzmeniPolazak koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Menja atribute objekta klase Polazak. Dozvoljeno je samo menjanje napomene.
 *
 *
 * @author sleza
 */
public class SOIzmeniPolazak extends AbstractGenericOperation {
 /**
     * Proverava da li je objekat klase polazak i ako nije baca exception.
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * 
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Polazak)) {
            throw new Exception("Pogresan parametar");
        }
        Polazak p = (Polazak) entity;
        if (p.getPolazakID() <= -1) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }
    /**
     * Izvršava upit(UPDATE) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> UpdateEntityException - Sistem ne moze da izmeni Polazak!
     * </ul>
     *
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.updateRecord((GeneralEntity) entity);
        } catch (SQLException | UpdateEntityException ex) {
            throw new Exception("Sistem ne može da izmeni podatke o polasku");
        }
    }

}
