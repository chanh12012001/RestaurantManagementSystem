
package DAO.Interface;

import DTO.FoodGroup_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public interface IFoodGroup_DAO extends DAO<FoodGroup_DTO> {
    /**
     * Get a group food according to food group's name
     *
     * @param name food group's name
     * @return A object representing food group's name
     */
    FoodGroup_DTO getFoodGroupByName(String name);
    
    /**
     * Get a group food according to food group's id
     *
     * @param id food group's id
     * @return A object representing group food's id
     */
    FoodGroup_DTO getFoodGroupById(int id);
    
    /**
     * Find food groups
     *
     * @param name food group's name
     * @return A list of food group found
     */
    ArrayList<FoodGroup_DTO> findFoodGroups(String name);
}
