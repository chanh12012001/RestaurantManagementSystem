package DAO.Interface;

import DTO.BillDetail_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public interface IBillDetail_DAO extends DAO<BillDetail_DTO>{
    /**
     * Find bill detail
     *
     * @param tableId   table's id
     * @return A bill detail by table found
     */
    ArrayList<BillDetail_DTO> loadBillDetailByTableId(int tableId);
}
