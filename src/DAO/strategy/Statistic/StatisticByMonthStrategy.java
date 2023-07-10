
package DAO.strategy.Statistic;

import DAO.Statistic_DAO;
import DAO.strategy.IIncomeCalculationStrategy;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class StatisticByMonthStrategy implements IIncomeCalculationStrategy{
    static Statistic_DAO statistic_DAO = new Statistic_DAO();
    
    private String year;

    public StatisticByMonthStrategy(String year) {
        this.year = year;
    }

    @Override
    public ArrayList<Statistic_DTO> calculateIncome() {
        return statistic_DAO.statisticIncomeByMonth(year);
    }
    
}
