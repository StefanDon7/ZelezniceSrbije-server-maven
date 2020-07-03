/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soPolazak;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOUnesiPolazak koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Unosi polazak u bazu.
 *
 * @author sleza
 */
public class SOUnesiPolazak extends AbstractGenericOperation {
     /**
     * Proverava da li je objekat klase polazak i ako nije baca exception.
     *
     * @param entity - objekat klase polazak.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Polazak)) {
            throw new Exception("Pogresni parametri!");
        }
    }
    /**
     * Izvrsava upit(INSERT) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> InsertEntityException - Polazak vec postoji!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException | InsertEntityException ex) {
            throw new Exception("Sistem ne moze da unese polazak!");
        }
    }

}
