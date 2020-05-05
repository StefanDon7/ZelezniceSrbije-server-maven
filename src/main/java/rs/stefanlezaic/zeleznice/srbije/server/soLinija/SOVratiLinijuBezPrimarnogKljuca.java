/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soLinija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;

/**
 *
 * @author sleza
 */
public class SOVratiLinijuBezPrimarnogKljuca extends AbstractGenericOperation {

    private GeneralEntity linija;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Linija)) {
            throw new Exception("Pogresni paremetri");
        }
    }

    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException, Exception {
        try {
            linija = databaseBroker.findRecordNoPrimaryKey((Linija) entity);
            Linija l = (Linija) linija;
            l.setTipLinije((TipLinije) databaseBroker.findRecord(new TipLinije(l.getTipLinije().getTipLinijeID())));
            AbstractGenericOperation op2 = new SOVratiStanicu();
            op2.templateExecute(new Stanica(l.getStanicaPocetna().getStanicaID()));
            GeneralEntity stanica = ((SOVratiStanicu) op2).getStanica();
            l.setStanicaPocetna((Stanica) stanica);
            op2.templateExecute(new Stanica(l.getStanicaKrajnja().getStanicaID()));
            GeneralEntity stanica2 = ((SOVratiStanicu) op2).getStanica();
            l.setStanicaKrajnja((Stanica) stanica2);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Pogrešno pogresno");
        }
    }

    public GeneralEntity getLinija() {
        return linija;
    }

}
