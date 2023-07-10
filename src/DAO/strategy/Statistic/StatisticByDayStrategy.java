
package DAO.strategy.Statistic;

import DAO.Statistic_DAO;
import DAO.strategy.IIncomeCalculationStrategy;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class StatisticByDayStrategy implements IIncomeCalculationStrategy{
    static Statistic_DAO statistic_DAO = new Statistic_DAO();
    
    private String date;

    public StatisticByDayStrategy(String date) {
        this.date = date;
    }

    @Override
    public Statistic_DTO calculateIncome() {
        Statistic_DTO statistic = statistic_DAO.statisticIncomeInDay(date);
        return statistic;
    }
    
}
