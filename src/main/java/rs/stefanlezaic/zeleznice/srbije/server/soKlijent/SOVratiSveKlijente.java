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
 * Klasa sistemska operacija vrati sve klijente koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca sve klijente iz baze.
 *
 * @author sleza
 */
public class SOVratiSveKlijente extends AbstractGenericOperation {
     /**
     * Lista Objekata klase GeneralEntity.
     */
    private List<GeneralEntity> klijenti;

    /**
     * Proverava da li je objekat klase klijent i ako nije baca exception.
     *
     * @param Object entity - objekat klase Klijent.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Pogresan parametar");
        }
    }
     /**
     * Izvršava upit(Select) nad bazom podataka, baca izuzetak:
     *
     * @param Object entity - objekat klase Klijent.
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
            klijenti = databaseBroker.getAllRecord((Klijent) entity);
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
    /**
     * 
     * @return Lista Objekata GeneralEntity(Klijent) rezultat pretrage nad bazom podataka.
     * 
     */
    public List<GeneralEntity> getKlijenti() {
        return klijenti;
    }

}
