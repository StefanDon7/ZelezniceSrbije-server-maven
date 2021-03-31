/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soKlijent;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.UpdateEntityException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Menja atribute objekta klase klijent. Dozvoljeno je samo menjanje korisničkog
 * imena i šifre.
 *
 * @author sleza
 */
public class SOIzmeniKlijenta extends AbstractGenericOperation {

    /**
     * Proverava da li je objekat klase klijent i ako nije baca exception.
     *
     * @param entity - objekat klase Klijent.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji služe za upit nisu dobro uneti ili nisu uneti.
     */
    @Override
    protected void validate(Object entity) throws Exception, InvalidProductException {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Pogresan parametar");
        }
        Klijent k = (Klijent) entity;
        if (k.getEmail().isEmpty() || k.getIme().isEmpty() || k.getPrezime().isEmpty() || k.getKorisnickoIme().isEmpty() || k.getLozinka().isEmpty()) {
            throw new InvalidProductException("Sva polja moraju biti popunjena!!");
        }

    }

    /**
     * Izvršava upit nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Klijent.
     *
     *
     */
    @Override
    protected void execute(Object entity) throws UpdateEntityException, SQLException {
        try {
            databaseBroker.updateRecord((GeneralEntity) entity);
        } catch (UpdateEntityException ex) {
            throw new UpdateEntityException("Sistem ne može da izmeni podatke o klijentu");
        } catch (SQLException ex) {
            throw new SQLException("Greska na strani sistema!");
        }
    }
}
