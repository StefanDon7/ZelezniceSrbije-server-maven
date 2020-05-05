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
 *
 * @author sleza
 */
public class SOUnesiStanicu extends AbstractGenericOperation {

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

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException | InsertEntityException ex) {
            throw new Exception("Sistem ne moze da napravi stanicu");
        }
    }

}
