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
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soStanica.SOVratiStanicu;
import rs.stefanlezaic.zeleznice.srbije.server.soTipLinije.SOVratiTipLinije;

/**
 *
 * @author sleza
 */
public class SOVratiSveLinije extends AbstractGenericOperation {

    private List<GeneralEntity> linije;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Linija)) {
            throw new Exception("Pogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            linije = databaseBroker.getAllRecord(new Linija());
            for (GeneralEntity generalEntity : linije) {
                Linija l = (Linija) generalEntity;
                AbstractGenericOperation op1 = new SOVratiTipLinije();
                op1.templateExecute(new TipLinije(l.getTipLinije().getTipLinijeID()));
                GeneralEntity tip = ((SOVratiTipLinije) op1).getTipLinije();
                TipLinije tp = (TipLinije) tip;
                l.setTipLinije(tp);
                AbstractGenericOperation op2 = new SOVratiStanicu();
                op2.templateExecute(new Stanica(l.getStanicaPocetna().getStanicaID()));
                GeneralEntity stanica1 = ((SOVratiStanicu) op2).getStanica();
                Stanica s1 = (Stanica) stanica1;
                l.setStanicaPocetna(s1);
                AbstractGenericOperation op3 = new SOVratiStanicu();
                op3.templateExecute(new Stanica(l.getStanicaKrajnja().getStanicaID()));
                GeneralEntity stanica2 = ((SOVratiStanicu) op3).getStanica();
                Stanica s2 = (Stanica) stanica2;
                l.setStanicaKrajnja(s2);
            }
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }

    public List<GeneralEntity> getLinije() {
        return linije;
    }

}
