/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soKlijent;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 *
 *
 *
 *
 *
 *
 * @author sleza
 */
public class SOVratiKlijenta extends AbstractGenericOperation {

    private GeneralEntity klijent;

    @Override
    protected void validate(Object entity) throws Exception, InvalidProductException {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Pogresni paremetri");
        }
        Klijent k = (Klijent) entity;
        if (k.getKlijentID() <= 0) {
            throw new InvalidProductException("Primarni kljuc je 0 ili manji od nule!");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            klijent = databaseBroker.findRecord((Klijent) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne moze da pronadje klijenta!");
        }
    }

    public GeneralEntity getKlijent() {
        return klijent;
    }

}
