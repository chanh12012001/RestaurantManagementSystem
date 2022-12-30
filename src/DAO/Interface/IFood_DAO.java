
package DAO.Interface;

import DTO.Food_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public interface IFood_DAO extends DAO<Food_DTO> {
    /**
     * Get a food according to food name
     *
     * @param name food name
     * @return A object representing food 's id
     */
    Food_DTO getFoodByName(String name);
    
    /**
     * Get a food according to food id
     *
     * @param id food id
     * @return A object representing food 's id
     */
    Food_DTO getFoodById(int id);
    
    
    /**
     * Find foods
     *
     * @param name food's name
     * @return A list of food found
     */
    ArrayList<Food_DTO> findFoodsByName(String name);
    
    /**
     * Find foods
     *
     * @param groupName food's group name
     * @return A list of food found
     */
    ArrayList<Food_DTO> findFoodsByGroupName(String groupName);
    
}
