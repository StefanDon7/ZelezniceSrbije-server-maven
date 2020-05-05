/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soTipLinije;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiTipLinije extends AbstractGenericOperation {

    private GeneralEntity tipLinije;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TipLinije)) {
            throw new Exception("Pogresni paremetri");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            tipLinije = databaseBroker.findRecord((TipLinije) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Pogrešno pogresno");
        }
    }

    public GeneralEntity getTipLinije() {
        return tipLinije;
    }

}
