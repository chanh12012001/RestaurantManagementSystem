/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Interface;

import DTO.Food_DTO;
import DTO.Staff_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public interface IStaff_DAO extends DAO<Staff_DTO>{
    
    /**
     * Get a staff according to staff id
     *
     * @param id staff id
     * @return A object representing staff 's id
     */
    Staff_DTO getStaffById(String id);
    
    /**
     * Find staffs
     *
     * @param name staff's name
     * @return A list of staff found
     */
    ArrayList<Staff_DTO> findStaffsByName(String name);
}
