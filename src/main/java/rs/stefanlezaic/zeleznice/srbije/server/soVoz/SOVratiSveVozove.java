/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soVoz;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOVratiSveVozove koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca listu svih tipova vozova iz baze.
 *
 * @author sleza
 */
public class SOVratiSveVozove extends AbstractGenericOperation {
    /**
     * Lista GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private List<GeneralEntity> vozovi;
     /**
     * Proverava da li je objekat klase Voz i ako nije baca exception.
     *
     * @param entity - objekat klase Voz.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Voz)) {
            throw new Exception("Pogresni parametri!");
        }
    }
    /**
     * Izvršava upit(Select) nad bazom podataka.
     *
     * @param entity - objekat klase Voz.
     *
     *@throws Exception
     * <ul>
     * <li> SQLException - Greška na strani servera!
     * </ul>   
     */
    @Override
    protected void execute(Object entity) throws Exception {
        try {
            vozovi = databaseBroker.getAllRecord(new Voz());
        } catch (SQLException ex) {
            throw new Exception("Greška na strani servera");
        }
    }
     /**
     * Vraca Listu GeneralEntity(Voz) rezultat pretrage nad bazom podataka.
     * 
     * @return List GeneralEntity.
     */
    public List<GeneralEntity> getVozovi() {
        return vozovi;
    }

}
