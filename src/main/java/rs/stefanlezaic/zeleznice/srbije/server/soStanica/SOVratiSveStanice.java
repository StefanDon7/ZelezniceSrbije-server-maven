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
 * Klasa VratiMedjustanicu koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca sve stanice iz baze.
 *
 * @author sleza
 */
public class SOVratiSveStanice extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> stanice;
    /**
     * Proverava da li je objekat klase stanica i ako nije baca exception.
     *
     * @param Object entity - objekat klase Stanica.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji se koriste 
     * za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Stanica)) {
            throw new Exception("Pogresan parametar");
        }
    }
     /**
     * Izvršava upit(Select) nad bazom podataka.
     *
     * @param Object entity - objekat klase Stanica.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu
     * </ul>
     *
     */
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
    /**
     * Vraca List<GeneralEntity>(Stanica) rezultat pretrage nad bazom podataka. 
     * 
     * @return List<GeneralEntity>(Stanica).
     */
    public List<GeneralEntity> getStanice() {
        return stanice;
    }

}
