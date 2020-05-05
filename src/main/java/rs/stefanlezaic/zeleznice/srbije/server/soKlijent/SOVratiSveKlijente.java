/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soKlijent;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiSveKlijente extends AbstractGenericOperation {

    private List<GeneralEntity> klijenti;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Pogresan parametar");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            klijenti = databaseBroker.getAllRecord((Klijent) entity);
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public List<GeneralEntity> getKlijenti() {
        return klijenti;
    }

}
