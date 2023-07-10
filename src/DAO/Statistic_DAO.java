/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.OrderBill_DAO.conn;
import DTO.Statistic_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbookpro
 */
public class Statistic_DAO {
    static Connection conn;
    SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();
    
    /**
     * Statistic income of month in year
     *
     * @param year year to statistic
     * @return A list of total income each month in year
     */
    public ArrayList<Statistic_DTO> statisticIncomeByMonth(String year) {
        ArrayList<Statistic_DTO> statisticList = new ArrayList<>();

        String sqlStatement = "SELECT substr(NgayThanhToan,4,2) as Thang,sum(SoTien) DoanhThu from HoaDon"
                + " where substr(NgayThanhToan,Length(NgayThanhToan)-3,4) = ? group by Thang";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, year);

        try {
            Statistic_DTO statisticData = null;
            if (rs.next()) {
                statisticData = new Statistic_DTO(rs.getString("Thang"), rs.getDouble("DoanhThu"));
            }
            for (int i = 1; i <= 12; i++) {
                System.err.println("RUN HERE");

                if (statisticData != null && Integer.parseInt(statisticData.getIndex()) == i) {
                    statisticList.add(statisticData);
                    if (rs.next()) {
                        statisticData = new Statistic_DTO(rs.getString("Thang"), rs.getDouble("DoanhThu"));
                    } else {
                        statisticData = null;
                    }
                } else {
                    Statistic_DTO statistic = new Statistic_DTO(Integer.toString(i), 0);
                    statisticList.add(statistic);
                }
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return statisticList;
    }

    /**
     * Statistic income of each year
     *
     * @return A list of total income each year
     */
    public ArrayList<Statistic_DTO> statisticIncomeByYear() {
        ArrayList<Statistic_DTO> statisticList = new ArrayList<>();

        String sqlStatement = "select substr(NgayThanhToan,Length(NgayThanhToan)-3,4) as Nam,sum(SoTien) as DoanhThu from HoaDon"
                + " where Nam is not null group by Nam";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Statistic_DTO statistic = new Statistic_DTO(rs.getString("Nam"), rs.getDouble("DoanhThu"));
                statisticList.add(statistic);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return statisticList;
    }

    /**
     * Statistic income by date
     *
     * @param fromDay from date format dd/MM/YYYY
     * @param toDay to date format dd/MM/YYYY
     * @return A list of income in date
     */
    public ArrayList<Statistic_DTO> statisticIncomeByDate(String fromDay, String toDay) {
        ArrayList<Statistic_DTO> statisticList = new ArrayList<>();

        String fromDateFormat = "";
        String toDateFormat = "";
        try {
            Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDay);
            fromDateFormat = new SimpleDateFormat("yyyy-MM-dd").format(fromDate);

            Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(toDay);
            toDateFormat = new SimpleDateFormat("yyyy-MM-dd").format(toDate);
        } catch (ParseException ex) {
            Logger.getLogger(Bill_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sqlStatement = "select NgayThanhToan, sum(SoTien) as DoanhThu from HoaDon"
                + " where DATE(substr(NgayThanhToan,-4,4) || '-' || substr(NgayThanhToan,4,2) || '-' || substr(NgayThanhToan,1,2))"
                + " between DATE(?) and DATE(?)  group by NgayThanhToan";
        System.out.println(fromDateFormat + " " +toDateFormat);
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, fromDateFormat, toDateFormat);

        try {
            while (rs.next()) {
                Statistic_DTO statistic = new Statistic_DTO(rs.getString("NgayThanhToan"), rs.getDouble("DoanhThu"));
                statisticList.add(statistic);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return statisticList;
    }

    /**
     * Statistic income in today
     *
     * @param date date format dd/MM/YYYY
     * @return Income today
     */
    public Statistic_DTO statisticIncomeInDay(String date) {

        System.out.println(date);
        String sqlStatement = "select sum(SoTien) as DoanhThu from HoaDon where NgayThanhToan = ?";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, date);

        Statistic_DTO statistic = null;
        try {
            while (rs.next()) {
                statistic = new Statistic_DTO(date, rs.getDouble("DoanhThu"));
                return statistic;
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return new Statistic_DTO(date,0);
    }
}
