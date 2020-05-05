/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soLinija;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Linija;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOUnesiLiniju extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws Exception, InvalidProductException {
        if (!(entity instanceof Linija)) {
            throw new Exception("\nPogresni parametri!");
        }
        Linija l = (Linija) entity;
        if (l.getStanicaPocetna().equals(l.getStanicaKrajnja())) {
            throw new InvalidProductException("\nPocetna i krajnja stanica ne smeju biti iste!");
        }
        if (l.getNaziv().isEmpty() || l.getMinutaza() <= 0 || l.getKilometraza() <= 0 || l.getStanicaPocetna().getStanicaID() <= 0 || l.getStanicaKrajnja().getStanicaID() <= 0 || l.getTipLinije().getTipLinijeID() <= 0) {
            throw new InvalidProductException("\nPogresni parametri!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException | InsertEntityException ex) {
            throw new Exception("\nSistem ne moze da napravi liniju");
        }
    }

}
