
package DAO.strategy.Statistic;

import DAO.Statistic_DAO;
import DAO.strategy.IIncomeCalculationStrategy;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class StatisticByDateStrategy implements IIncomeCalculationStrategy{
    static Statistic_DAO statistic_DAO = new Statistic_DAO();
    
    private String fromDate;
    private String toDate;

    public StatisticByDateStrategy(String fromDate, String toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public ArrayList<Statistic_DTO> calculateIncome() {
        return statistic_DAO.statisticIncomeByDate(fromDate, toDate);
    }
    
}
