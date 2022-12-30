
package DAO.Interface;

import DTO.Account_DTO;

/**
 *
 * @author macbookpro
 */
public interface IAccount_DAO  extends DAO<Account_DTO>{
    /**
     * Get user by username
     * @param name username
     * @return Account contain username, pass, account type
     */
    public Account_DTO get(String name);
}
