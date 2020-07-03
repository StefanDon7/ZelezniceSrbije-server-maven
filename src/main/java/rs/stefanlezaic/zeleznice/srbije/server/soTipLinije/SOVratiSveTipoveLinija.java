/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soTipLinije;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.TipLinije;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija VratiMesto koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca medjustanicu iz baze na osnovu id.
 *
 * @author sleza
 */
public class SOVratiSveTipoveLinija extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> tipoviLinija;
     /**
     * Proverava da li je objekat klase mesto i ako nije baca exception.
     *
     * @param entity - objekat klase Mesto.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof TipLinije)) {
            throw new Exception("Pogresni parametri!");
        }
    }
      /**
     * Izvršava upit(Select) nad bazom podataka.
     *
     * @param entity - objekat klase TipLinije.
     *
     *@throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo greška na strani servera.
     * </ul>
     *
     */

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            tipoviLinija = databaseBroker.getAllRecord(new TipLinije());
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
     /**
     * Vraca List GeneralEntity(TipLinije) rezultat pretrage nad bazom podataka.
     * 
     * @return GeneralEntity(TipLinije).
     */
    public List<GeneralEntity> getTipoviLinija() {
        return tipoviLinija;
    }

}
