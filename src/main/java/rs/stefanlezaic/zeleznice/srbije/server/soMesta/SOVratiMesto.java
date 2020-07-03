/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMesta;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Mesto;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa VratiMesto koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca mesto iz baze na osnovu id.
 *
 * @author sleza
 */
public class SOVratiMesto extends AbstractGenericOperation {
    
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private GeneralEntity mesto;
    
     /**
     * Proverava da li je objekat klase mesto i ako nije baca exception.
     *
     * @param entity - objekat klase Mesto.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws InvalidProductException, Exception {
        if (!(entity instanceof Mesto)) {
            throw new Exception("Pogresni paremetri");
        }
        Mesto m = (Mesto) entity;
        if (m.getMestoID() <= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }
    
     /**
     * Izvršava upit(Select) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Mesto.
     *
     *@throws EntityNotFoundException
     *@throws SQLException
     * <ul>
     * <li> SQLException - u slučaju da je greška na strani servera
     * <li> EntityNotFoundException - ako sistem ne može da nadje mesto
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            mesto = databaseBroker.findRecord((Mesto) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne moze da pronadje mesto!");
        }
    }
    
     /**
     * Vraca GeneralEntity(Mesto) rezultat pretrage nad bazom podataka.
     * 
     * @return GeneralEntity(Mesto).
     */
    public GeneralEntity getMesto() {
        return mesto;
    }

}
