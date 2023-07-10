
package DAO;

import DAO.Interface.IBillDetail_DAO;
import DTO.BillDetail_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class BillDetail_DAO implements IBillDetail_DAO{
    static Connection conn;
      SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();

    /**
     * Get bill detail by table id from database
     *
     * @param tableId   bill detail's table id
     * @return A list of bill detail by table id
     */
    @Override
    public ArrayList<BillDetail_DTO> loadBillDetailByTableId(int tableId) {

        ArrayList<BillDetail_DTO> billDetails = new ArrayList<>();

        String sqlStatement = "select HoaDonInFo.ID,HoaDonInfo.IDMonAn, MonAn.TenMonAn,HoaDonInFo.SoLuong,MonAn.Gia, (HoaDonInFo.SoLuong * MonAn.Gia) as ThanhTien from BanAn, HoaDon, MonAn, HoaDonInFo where HoaDonInFo.IDMonAn = MonAn.ID AND HoaDonInFo.IDHoaDon = HoaDon.ID and HoaDon.IDBan = BanAn.ID AND BanAn.ID = ?";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, tableId);

        try {
            while (rs.next()) {
               
                BillDetail_DTO billDetail = new BillDetail_DTO(
                        rs.getInt("ID"),
                        rs.getInt("IDMonAn"),
                        rs.getString("TenMonAn"),
                        rs.getInt("SoLuong"),
                        rs.getInt("Gia"),
                        rs.getInt("ThanhTien")
                );
                
                billDetails.add(billDetail);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return billDetails;
    }

    @Override
    public ArrayList<BillDetail_DTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean add(BillDetail_DTO obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(BillDetail_DTO obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean delete(String uniqueProp) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
