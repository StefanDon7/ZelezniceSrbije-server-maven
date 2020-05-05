/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soStanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soMesta.SOVratiMesto;

/**
 *
 * @author sleza
 */
public class SOVratiSveStanice extends AbstractGenericOperation {

    private List<GeneralEntity> stanice;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Stanica)) {
            throw new Exception("Pogresan parametar");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            stanice = databaseBroker.getAllRecord(new Stanica());
            for (GeneralEntity generalEntity : stanice) {
                Stanica s = (Stanica) generalEntity;
                AbstractGenericOperation op3 = new SOVratiMesto();
                op3.templateExecute(new Mesto(s.getMesto().getMestoID()));
                Mesto m = (Mesto) ((SOVratiMesto) op3).getMesto();
                s.setMesto(m);
            }
        } catch (SQLException ex) {
            throw new Exception("Sistem ne moze da vrati stanicu");
        }
    }

    public List<GeneralEntity> getStanice() {
        return stanice;
    }

}
