/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soPolazak;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;
import rs.stefanlezaic.zeleznice.srbije.server.soLinija.SOVratiLiniju;

/**
 * Klasa sistemska operacija VratiMedjustanicu koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca sve polaske iz baze.
 *
 * @author sleza
 */
public class SOVratiSvePolaske extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> polasci;
    /**
     * Proverava da li je objekat klase Polazak i ako nije baca exception.
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * 
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Polazak)) {
            throw new Exception("Pogresni parametri!");
        }
    }
     /**
     * Izvršava upit(Select) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Polazak.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu
     * <li> EntityNotFoundException - ako sistem ne može da nadje polazak
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            polasci = databaseBroker.getAllRecord(new Polazak());
            for (GeneralEntity generalEntity : polasci) {
                Polazak p = (Polazak) generalEntity;
                AbstractGenericOperation op7 = new SOVratiLiniju();
                op7.templateExecute(new Linija(p.getLinija().getLinijaID()));
                Linija l = (Linija) ((SOVratiLiniju) op7).getLinija();
                p.setLinija(l);
                p.setVoz((Voz) databaseBroker.findRecord(new Voz(p.getVoz().getVozID())));
            }
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
    /**
     * Vraca List GeneralEntity(Polazak) rezultat pretrage nad bazom podataka.
     * 
     * @return List GeneralEntity(Polazak)
     */

    public List<GeneralEntity> getPolasci() {
        return polasci;
    }

}
