
package DAO.Interface;

import DTO.Bill_DTO;
import java.util.ArrayList;


/**
 *
 * @author macbookpro
 */
public interface IBill_DAO extends DAO<Bill_DTO>{
    /**
     * Find bills
     *
     * @param fromDay
     * @param toDay
     * @return A list of bill found
     */
    ArrayList<Bill_DTO> getAllBillsBetweenFromDayAndToDay(String fromDay, String toDay);
}
