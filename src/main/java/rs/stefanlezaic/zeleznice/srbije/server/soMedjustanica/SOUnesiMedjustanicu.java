/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Unosi medjustanicu u bazu.
 *
 * @author sleza
 */
public class SOUnesiMedjustanicu extends AbstractGenericOperation {
    
     /**
     * Proverava da li je objekat klase medjustanica i ako nije baca exception.
     *
     * @param entity - objekat klase Medjustanica.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof MedjuStanica)) {
            throw new Exception("\nPogresni parametri!");
        }
        MedjuStanica m = (MedjuStanica) entity;
        if (m.getStanica().equals(m.getLinija().getStanicaPocetna()) || m.getStanica().equals(m.getLinija().getStanicaKrajnja())) {
            throw new InvalidProductException("\nMedjustanica ne sme biti jednaka krajnjoj ili pocetnoj medjustanici!");
        }
    }
    /**
     * Izvrsava upit(INSERT) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Klijent.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greska na strani servera!
     * <li> InsertEntityException - Medjustanica vec postoji!
     * </ul>
     *
     */

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException ex) {
            throw new SQLException("\nGreska na strani servera!");
        } catch (InsertEntityException ex) {
            throw new InsertEntityException("\nMedjustanica vec postoji!");
        }

    }
}
