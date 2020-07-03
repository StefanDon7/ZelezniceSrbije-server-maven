/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.DeleteEntityException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Brise medjustanicu iz baze.
 *
 * @author sleza
 */
public class SOObrisiMedjustanicu extends AbstractGenericOperation {
    
    /**
     * Proverava da li je objekat klase medjustanica i ako nije baca exception.
     *
     * @param entity - objekat klase Medjustanica.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * 
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof MedjuStanica)) {
            throw new Exception("Pogresni parametri!");
        }
    }
     /**
     * Izvršava upit(DELETE) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Klijent.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> DeleteEntityException - Sistem ne moze da obrise medjustanicu!
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.deleteRecord((GeneralEntity) entity);
        } catch (SQLException | DeleteEntityException ex) {
            throw new Exception("Sistem ne može da obrise medjustanicu");
        }
    }

}
