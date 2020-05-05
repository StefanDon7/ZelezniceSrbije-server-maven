/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soTipLinije;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiSveTipoveLinija extends AbstractGenericOperation {

    private List<GeneralEntity> tipoviLinija;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TipLinije)) {
            throw new Exception("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            tipoviLinija = databaseBroker.getAllRecord(new TipLinije());
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public List<GeneralEntity> getTipoviLinija() {
        return tipoviLinija;
    }

}
