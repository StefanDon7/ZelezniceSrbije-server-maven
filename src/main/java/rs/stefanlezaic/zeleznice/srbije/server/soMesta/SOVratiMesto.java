/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMesta;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiMesto extends AbstractGenericOperation {

    private GeneralEntity mesto;

    @Override
    protected void validate(Object entity) throws InvalidProductException, Exception {
        if (!(entity instanceof Mesto)) {
            throw new Exception("Pogresni paremetri");
        }
        Mesto m = (Mesto) entity;
        if (m.getMestoID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            mesto = databaseBroker.findRecord((Mesto) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne moze da pronadje mesto!");
        }
    }

    public GeneralEntity getMesto() {
        return mesto;
    }

}
