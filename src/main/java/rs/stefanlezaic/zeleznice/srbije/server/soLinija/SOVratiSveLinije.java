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
 * Klasa sistemska operacija koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca sve linije iz baze.
 *
 * @author sleza
 */
public class SOVratiSveLinije extends AbstractGenericOperation {
    /**
     * Lista linija
     */
    private List<GeneralEntity> linije;
    
    
    /**
     * Proverava da li je objekat klase linija i ako nije baca izuzetak.
     *
     * @param entity - objekat klase Linija.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.

     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Linija)) {
            throw new Exception("Pogresni parametri!");
        }
    }
       /**
     * Izvršava upit(SELECT) nad bazom podataka, baca izuzetka:
     *
     * @param entity - objekat klase Linija.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - Greška na strani servera
     * </ul>
     *
     */

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
    /**
     * Vraca GeneralEntity koji ce kontroler kastovati u klasu Linija
     * 
     * @return List GeneralEntity(Linija) rezultat pretrage nad bazom podataka.
     */
    public List<GeneralEntity> getLinije() {
        return linije;
    }

}
