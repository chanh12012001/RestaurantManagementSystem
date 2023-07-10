
package DAO.strategy.Statistic;

import DAO.Statistic_DAO;
import DAO.strategy.IIncomeCalculationStrategy;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class StatisticByYearStrategy implements IIncomeCalculationStrategy{
    static Statistic_DAO statistic_DAO = new Statistic_DAO();
    
    @Override
    public ArrayList<Statistic_DTO> calculateIncome() {
        return statistic_DAO.statisticIncomeByYear();
    }
    
}
