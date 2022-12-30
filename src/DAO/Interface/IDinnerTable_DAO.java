/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Interface;

import DTO.DinnerTable_DTO;
import java.util.ArrayList;

/**
 *
 * @author bao20
 */
public interface IDinnerTable_DAO extends DAO<DinnerTable_DTO> {

    /**
     * Get a table according to table name
     *
     * @param name dinner table name
     * @return A int representing dinner table 's id
     */
    DinnerTable_DTO getDinnerTableByTableName(String name);

    /**
     * Get a table according to table id
     *
     * @param id dinner table id
     * @return A object representing dinner table 's id
     */
    DinnerTable_DTO getDinnerTableByTableId(int id);
    
    /**
     * Find tables
     *
     * @param name dinner table name
     * @return A list of dinner table found
     */
    ArrayList<DinnerTable_DTO> findTables(String name);

    /**
     * Set status of table to occupied
     *
     * @param id dinner table id
     * @return A Boolean representing success or fail
     */
    boolean setStatusOccupied(int id);

    /**
     * Set status of table to empty
     *
     * @param id dinner table id
     * @return A Boolean representing success or fail
     */
    boolean setStatusEmpty(int id);
}
