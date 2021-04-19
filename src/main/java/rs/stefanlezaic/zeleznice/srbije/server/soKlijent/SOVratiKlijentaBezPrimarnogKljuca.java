/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soKlijent;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Klijent;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 * Klasa sistemska operacija vrati klijenta koja nasledjuje abstraktnu klasu AbstractGenericOperation.
 * Vraca klijenta iz baze na osnovu emaila i lozinke.
 *
 * @author sleza
 */
public class SOVratiKlijentaBezPrimarnogKljuca extends AbstractGenericOperation {
    /**
     * Objekat klase GeneralEntity koji treba da primi vrednosti iz baze.
     */
    private GeneralEntity klijent;
    
     /**
     * Proverava da li je objekat klase klijent i ako nije baca exception.
     *
     * @param entity - objekat klase Klijent.
     *
     * @throws Exception u slučaju da je kao parametar dat objekat druge klase.
     * @throws InvalidProductException u slučaju da atributi koji se koriste za upit nisu dobro uneti ili nisu uneti.
     */
    
    @Override
    protected void validate(Object entity) throws Exception, InvalidProductException {
        if (!(entity instanceof Klijent)) {
            throw new Exception("Pogresni paremetri");
        }
        Klijent k = (Klijent) entity;
        if (k.getEmail().isEmpty() || k.getLozinka().isEmpty()) {
            throw new InvalidProductException("Sva polja moraju biti popunjena!!");
        }
    }
     /**
     * Izvršava upit(Select) nad bazom podataka, baca dve vrste izuzetka:
     *
     * @param entity - objekat klase Klijent.
     *
     */
    @Override
    protected void execute(Object entity) throws EntityNotFoundException, SQLException {
        try {
            klijent = databaseBroker.findRecordNoPrimaryKey((Klijent) entity);
        } catch (SQLException ex) {
            throw new SQLException("Greška na strani servera");
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Sistem ne moze da pronađe korisnika!");
        }
    }
    /**
     * 
     * @return GeneralEntity(Klijent) rezultat pretrage nad bazom podataka.
     */
    public GeneralEntity getKlijent() {
        return klijent;
    }

}
