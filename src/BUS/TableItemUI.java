
package BUS;

import DAO.DinnerTable_DAO;
import DAO.Interface.IDinnerTable_DAO;
import DTO.TableModelItemUI;
import DTO.DinnerTable_DTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbookpro
 */
public class TableItemUI {
    
    static IDinnerTable_DAO dinnerTable_DAO = new DinnerTable_DAO();
    
    public static List<TableModelItemUI> getAllTableDinnerUI() {
        List<DinnerTable_DTO> dinnerTableInfoList = dinnerTable_DAO.getAll();
        List<TableModelItemUI> tableUIList = new ArrayList<>();
        for (DinnerTable_DTO dinnerTableInfo : dinnerTableInfoList) {
            tableUIList.add(new TableModelItemUI(dinnerTableInfo.getId(), dinnerTableInfo.getName(), dinnerTableInfo.getStatus()));
        }
        return tableUIList;
    }
}
