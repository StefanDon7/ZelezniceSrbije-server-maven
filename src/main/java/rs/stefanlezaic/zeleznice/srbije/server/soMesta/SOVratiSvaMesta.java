/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMesta;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOVratiSvaMesta extends AbstractGenericOperation {

    private List<GeneralEntity> mesta;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Mesto)) {
            throw new Exception("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            mesta = databaseBroker.getAllRecord(new Mesto());
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public List<GeneralEntity> getMesta() {
        return mesta;
    }

}
