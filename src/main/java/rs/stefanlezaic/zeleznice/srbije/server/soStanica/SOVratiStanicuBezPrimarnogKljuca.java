/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soStanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiStanicuBezPrimarnogKljuca extends AbstractGenericOperation {

    private GeneralEntity stanica;

    @Override
    protected void validate(Object entity) throws InvalidProductException, Exception {
        if (!(entity instanceof Stanica)) {
            throw new Exception("Nije instanca klase!");
        }
        Stanica s = (Stanica) entity;
        if (s.getMesto().getMestoID() <= 0 || s.getNaziv().isEmpty()) {
            throw new InvalidProductException("Pogresni paramteri!");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            stanica = databaseBroker.findRecordNoPrimaryKey((Stanica) entity);
            Stanica s = (Stanica) stanica;
            s.setMesto((Mesto) databaseBroker.findRecord(new Mesto(s.getMesto().getMestoID())));
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Pogrešno pogresno");
        }
    }

    public GeneralEntity getStanica() {
        return stanica;
    }

}
