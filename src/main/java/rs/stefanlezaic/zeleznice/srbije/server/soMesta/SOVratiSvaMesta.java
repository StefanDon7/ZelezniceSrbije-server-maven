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
 * Klasa sistemska operacija VratiMesto koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca medjustanicu iz baze na osnovu id.
 *
 * @author sleza
 */
public class SOVratiSvaMesta extends AbstractGenericOperation {
    
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> mesta;
    
      /**
     * Proverava da li je objekat klase mesto i ako nije baca exception.
     *
     * @param Object entity - objekat klase Mesto.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Mesto)) {
            throw new Exception("Pogresni parametri!");
        }
    }
     /**
     * Izvršava upit(Select) nad bazom podataka.
     *
     * @param Object entity - objekat klase Mesto.
     *
     *@throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu.
     * </ul>
     *
     */

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            mesta = databaseBroker.getAllRecord(new Mesto());
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
    
     /**
     * Vraca List<GeneralEntity>(Mesto) rezultat pretrage nad bazom podataka.
     * 
     * @return List<GeneralEntity>(Mesto).
     */
    
    public List<GeneralEntity> getMesta() {
        return mesta;
    }

}
