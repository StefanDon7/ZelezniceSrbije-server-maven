/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soStanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Stanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOVratiStanicuBezPrimarnogKljuca koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca stanicu iz baze na osnovu mesta i naziva.
 *
 * @author sleza
 */
public class SOVratiStanicuBezPrimarnogKljuca extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private GeneralEntity stanica;
    
     /**
     * Proverava da li je objekat klase stanica i ako nije baca exception.
     *
     * @param Object entity - objekat klase Medjustanica.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji se koriste za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws InvalidProductException, Exception {
        if (!(entity instanceof Stanica)) {
            throw new Exception("Nije instanca klase!");
        }
        Stanica s = (Stanica) entity;
        if (s.getMesto().getMestoID() <= 0 || s.getNaziv().isEmpty()) {
            throw new InvalidProductException("Pogresni paramteri!");
        }
    }
    /**
     * Izvršava upit(Select) nad bazom podataka
     *
     * @param Object entity - objekat klase Medjustanica.
     *
     * @throws Exception
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu
     * <li> EntityNotFoundException - ako sistem ne može da nadje stanicu
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            stanica = databaseBroker.findRecordNoPrimaryKey((Stanica) entity);
            Stanica s = (Stanica) stanica;
            s.setMesto((Mesto) databaseBroker.findRecord(new Mesto(s.getMesto().getMestoID())));
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne moze da nadje stanicu");
        }
    }
    /**
     * Vraca GeneralEntity(Stanica) rezultat pretrage nad bazom podataka.
     * 
     * @return GeneralEntity(Stanica). 
     */
    public GeneralEntity getStanica() {
        return stanica;
    }

}
