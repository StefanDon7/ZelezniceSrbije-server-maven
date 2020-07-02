/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soLinija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.DeleteEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Brise iz baze liniju.
 *
 *
 * @author sleza
 */
public class SOObrisiLiniju extends AbstractGenericOperation {
    
    /**
     * Proverava da li je objekat klase linija i ako nije baca exception.
     *
     * @param Object entity - objekat klase Linija.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Linija)) {
            throw new Exception("Pogresni parametri!");
        }
        Linija l = (Linija) entity;
        if (l.getLinijaID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }
    
      /**
     * Izvršava upit(DELETE) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param Object entity - objekat klase Linija.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Sistem ne može da obrise liniju
     * <li> DeleteEntityException - Sistem ne može da obrise liniju
     * </ul>
     *
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
