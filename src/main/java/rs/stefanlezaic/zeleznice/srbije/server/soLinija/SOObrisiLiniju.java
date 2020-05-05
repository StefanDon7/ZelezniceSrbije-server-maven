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
 *
 * @author sleza
 */
public class SOObrisiLiniju extends AbstractGenericOperation {

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

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.deleteRecord((GeneralEntity) entity);
        } catch (SQLException | DeleteEntityException ex) {
            throw new Exception("Sistem ne može da obrise liniju");
        }
    }

}
