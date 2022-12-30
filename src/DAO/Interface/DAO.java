
package DAO.Interface;

import java.util.ArrayList;

/**
 *
 * @author bao20
 * @param <T> type of object
 */
public interface DAO<T> {
    /**
     * Get all object in database
     * @return A array list of object
     */
    public ArrayList<T> getAll();
    
    /**
     * Add object to database
     * @param obj object to add
     * @return true if add success, otherwise false
     */
    boolean add(T obj);
    
     /**
     * Update object to database
     * @param obj object to update
     * @return true if add success, otherwise false
     */
    boolean update(T obj);
    
     /**
     * Delete object by id or unique property
     * @param uniqueProp id or unique property value
     * @return true if add success, otherwise false
     */
    boolean delete(String uniqueProp);
}