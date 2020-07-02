/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soStanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOUnesiStanicu koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Unosi stanicu u bazu.
 *
 * @author sleza
 */
public class SOUnesiStanicu extends AbstractGenericOperation {
    /**
     * Proverava da li je objekat klase Stanica i ako nije baca exception.
     *
     * @param Object entity - objekat klase Stanica.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception, InvalidProductException {
        if (!(entity instanceof Stanica)) {
            throw new Exception("Pogresan parametar");
        }
        Stanica s = (Stanica) entity;
        if (s.getNaziv().isEmpty() || s.getMesto().getMestoID() <= 0) {
            throw new InvalidProductException("Pogresan parametar");
        }
    }
     /**
     * Izvrsava upit(INSERT) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param Object entity - objekat klase Stanica.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> InsertEntityException - Stanica vec postoji!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException | InsertEntityException ex) {
            throw new Exception("Sistem ne moze da napravi stanicu");
        }
    }

}
