/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soVoz;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Voz;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa SOVratiVoz koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca Voz iz baze na osnovu id.
 *
 * @author sleza
 */
public class SOVratiVoz extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity(Voz) koji treba da primi vrednosti iz baze.
     */
    private GeneralEntity voz;
     /**
     * Proverava da li je objekat klase Voz i ako nije baca exception.
     *
     * @param entity - objekat klase Voz.
     *
     * @throws Exception u slucaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Voz)) {
            throw new Exception("Pogresni paremetri");
        }
        Voz v = (Voz) entity;
        if (v.getVozID()<= 0) {
            throw new InvalidProductException("Pogresni parametri!");
        }
    }
      /**
     * Izvršava upit(Select) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Voz.
     *
     *@throws EntityNotFoundException
     *@throws SQLException
     * <ul>
     * <li> SQLException - u slučaju da je došlo do greške u sistemu
     * <li> EntityNotFoundException - ako sistem ne može da nadje voz
     * </ul>
     *
     */
    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            voz = databaseBroker.findRecord((Voz) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera!");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne može da nadje voz!");
        }
    }
     /**
     * Vraca GeneralEntity(Voz)rezultat pretrage nad bazom podataka.
     * 
     * @return GeneralEntity(Voz).
     */
    public GeneralEntity getVoz() {
        return voz;
    }

}
