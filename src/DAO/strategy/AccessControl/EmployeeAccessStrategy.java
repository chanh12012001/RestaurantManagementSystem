
package DAO.strategy.AccessControl;

import DAO.Constant;
import DAO.strategy.IAccessControlStrategy;

/**
 *
 * @author macbookpro
 */
public class EmployeeAccessStrategy implements IAccessControlStrategy {
    @Override
    public boolean checkAccess(String accountType) {
        return accountType.equals(Constant.EMPLOYEE_ACCOUNT_TYPE);
    }
}
