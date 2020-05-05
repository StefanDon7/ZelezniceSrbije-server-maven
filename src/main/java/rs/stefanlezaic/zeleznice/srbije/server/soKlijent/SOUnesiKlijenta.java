/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soKlijent;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOUnesiKlijenta extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws InvalidProductException, Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Exception!" + "\n" + "Objekat nije instanca date klase!");
        }
        Klijent k = (Klijent) entity;
        if (k.getEmail().isEmpty() || k.getIme().isEmpty() || k.getPrezime().isEmpty() || k.getKorisnickoIme().isEmpty() || k.getLozinka().isEmpty()) {
            throw new InvalidProductException("Sva polja moraju biti popunjena!!");
        }

    }

    @Override
    protected void execute(Object entity) throws InsertEntityException, SQLException {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (InsertEntityException ex) {
            throw new InsertEntityException("Sistem ne moze da registruje korisnika");
        } catch (SQLException ex) {
            throw new SQLException("Greska na strani sistema!");
        }
    }
}
