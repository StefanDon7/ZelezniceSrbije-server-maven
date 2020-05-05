/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;

/**
 *
 * @author sleza
 */
public class SOVratiMedjustanicu extends AbstractGenericOperation {

    private GeneralEntity medjustanica;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof MedjuStanica)) {
            throw new Exception("Objekat nije instanca date klase!");
        }
        MedjuStanica m = (MedjuStanica) entity;
        if (m.getStanica().getStanicaID() <= 0 || m.getLinija().getLinijaID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            medjustanica = databaseBroker.findRecord((MedjuStanica) entity);
            MedjuStanica m = (MedjuStanica) medjustanica;
            AbstractGenericOperation op7 = new SOVratiLiniju();
            op7.templateExecute(new Linija(m.getLinija().getLinijaID()));
            Linija l = (Linija) ((SOVratiLiniju) op7).getLinija();
            m.setLinija(l);
            AbstractGenericOperation op2 = new SOVratiStanicu();
            op2.templateExecute(new Stanica(m.getStanica().getStanicaID()));
            Stanica s = (Stanica) ((SOVratiStanicu) op2).getStanica();
            m.setStanica(s);
        } catch (SQLException ex) {
            throw new Exception("Greska na strani sistema!");
        }
    }

    public GeneralEntity getMedjustanica() {
        return medjustanica;
    }

}
