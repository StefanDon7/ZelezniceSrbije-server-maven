package rs.stefanlezaic.zeleznice.srbije.server.database;

import rs.stefanlezaic.zeleznice.srbije.lib.domen.GeneralEntity;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.DeleteEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.EntityNotFoundException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.InsertEntityException;
import rs.stefanlezaic.zeleznice.srbije.lib.exception.UpdateEntityException;
import java.sql.SQLException;
import java.util.List;
import rs.stefanlezaic.zeleznice.srbije.lib.domen.Polazak;

public interface IDatabaseBroker {
   
    List<GeneralEntity> getAllRecord(GeneralEntity entity) throws SQLException;

    GeneralEntity findRecord(GeneralEntity entity) throws SQLException, EntityNotFoundException;

    GeneralEntity findRecordNoPrimaryKey(GeneralEntity entity) throws SQLException, EntityNotFoundException;

    List<GeneralEntity> findRecords(GeneralEntity entity, GeneralEntity parent) throws SQLException;

    List<GeneralEntity> findRecordsWithObject(GeneralEntity entity, Object o) throws SQLException;
    
    List<GeneralEntity> findAllDeparture(GeneralEntity entity,Polazak p) throws SQLException;
    
    void insertRecord(GeneralEntity entity) throws SQLException, InsertEntityException;

    void deleteRecord(GeneralEntity entity) throws SQLException, DeleteEntityException;

    void updateRecord(GeneralEntity entity, GeneralEntity entityld) throws Exception;

    void updateRecord(GeneralEntity entity) throws SQLException, UpdateEntityException;

}
