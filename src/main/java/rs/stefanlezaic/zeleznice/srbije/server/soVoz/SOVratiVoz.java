/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soVoz;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiVoz extends AbstractGenericOperation {

    private GeneralEntity voz;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Voz)) {
            throw new Exception("Pogresni paremetri");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            voz = databaseBroker.findRecord((Voz) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Pogrešno pogresno");
        }
    }

    public GeneralEntity getVoz() {
        return voz;
    }

}
