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
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLiniju;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;

/**
 *
 * @author sleza
 */
public class SOVratiSveMedjustaniceZaLiniju extends AbstractGenericOperation {

    private List<GeneralEntity> medjustanice;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof MedjuStanica)) {
            throw new Exception("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            medjustanice = databaseBroker.findRecords(new MedjuStanica(), ((MedjuStanica) entity).getLinija());
            for (GeneralEntity generalEntity : medjustanice) {
                MedjuStanica m = (MedjuStanica) generalEntity;
                AbstractGenericOperation op7 = new SOVratiLiniju();
                op7.templateExecute(new Linija(m.getLinija().getLinijaID()));
                Linija l = (Linija) ((SOVratiLiniju) op7).getLinija();
                m.setLinija(l);
                AbstractGenericOperation op2 = new SOVratiStanicu();
                op2.templateExecute(new Stanica(m.getStanica().getStanicaID()));
                Stanica s = (Stanica) ((SOVratiStanicu) op2).getStanica();
                m.setStanica(s);
            }
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public List<GeneralEntity> getMedjustanice() {
        return medjustanice;
    }

}
