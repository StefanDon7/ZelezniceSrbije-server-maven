/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.stefanlezaic.zeleznice.srbije.server.soMedjustanica;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.MedjuStanica;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InvalidProductException;
import java.sql.SQLException;
import rs.stefanlezaic.zeleznice.srbije.server.so.AbstractGenericOperation;

/**
 *
 * @author sleza
 */
public class SOUnesiMedjustanicu extends AbstractGenericOperation {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof MedjuStanica)) {
            throw new Exception("\nPogresni parametri!");
        }
        MedjuStanica m = (MedjuStanica) entity;
        if (m.getStanica().equals(m.getLinija().getStanicaPocetna()) || m.getStanica().equals(m.getLinija().getStanicaKrajnja())) {
            throw new InvalidProductException("\nMedjustanica ne sme biti jednaka krajnjoj ili pocetnoj medjustanici!");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        try {
            databaseBroker.insertRecord((GeneralEntity) entity);
        } catch (SQLException ex) {
            throw new SQLException("\nMedjustanica vec postoji u izabranoj listi!");
        } catch (InsertEntityException ex) {
            throw new InsertEntityException("\nMedjustanica vec postoji!");
        }

    }
}
