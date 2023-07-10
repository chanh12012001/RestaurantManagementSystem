/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.strategy.IIncomeCalculationStrategy;
import DAO.strategy.Statistic.StatisticByDateStrategy;
import DAO.strategy.Statistic.StatisticByDayStrategy;
import DAO.strategy.Statistic.StatisticByMonthStrategy;
import DAO.strategy.Statistic.StatisticByYearStrategy;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class Statistic_BUS {
    private IIncomeCalculationStrategy incomeCalculationStrategy;

    public void setIncomeCalculationStrategy(IIncomeCalculationStrategy incomeCalculationStrategy) {
        this.incomeCalculationStrategy = incomeCalculationStrategy;
    }

    public Object calculateIncome() {
        if (incomeCalculationStrategy == null) {
            throw new IllegalStateException("Income calculation strategy is not set.");
        }
        return incomeCalculationStrategy.calculateIncome();
    }

    // Các phương thức khác của Statistic_BUS

    // Triển khai các hàm triển khai Strategy

    public ArrayList<Statistic_DTO> calculateIncomeByMonth(String year) {
        IIncomeCalculationStrategy strategy = new StatisticByMonthStrategy(year);
        setIncomeCalculationStrategy(strategy);
        Object result = calculateIncome();
    
        if (result instanceof ArrayList<?>) {
            return (ArrayList<Statistic_DTO>) result;
        } else {
            return null;
        }
    }

    public ArrayList<Statistic_DTO> calculateIncomeByYear() {
        IIncomeCalculationStrategy strategy = new StatisticByYearStrategy();
        setIncomeCalculationStrategy(strategy);
        Object result = calculateIncome();
         if (result instanceof ArrayList<?>) {
            return (ArrayList<Statistic_DTO>) result;
        } else {
            return null;
        }
    }

    public ArrayList<Statistic_DTO> calculateIncomeByDate(String fromDate, String toDate) {
        IIncomeCalculationStrategy strategy = new StatisticByDateStrategy(fromDate, toDate);
        setIncomeCalculationStrategy(strategy);
        Object result = calculateIncome();
         if (result instanceof ArrayList<?>) {
            return (ArrayList<Statistic_DTO>) result;
        } else {
            return null;
        }
    }

    public Statistic_DTO calculateIncomeByDay(String date) {
        IIncomeCalculationStrategy strategy = new StatisticByDayStrategy(date);
        Object result = strategy.calculateIncome();
    
        if (result instanceof Statistic_DTO) {
            return (Statistic_DTO) result;
        } else {
            return null; // Hoặc giá trị mặc định khác tùy thuộc vào logic của bạn
        }
    }
}
