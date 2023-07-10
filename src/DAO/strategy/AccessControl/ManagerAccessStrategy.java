/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.strategy.AccessControl;

import DAO.Constant;
import DAO.strategy.IAccessControlStrategy;

/**
 *
 * @author macbookpro
 */
public class ManagerAccessStrategy implements IAccessControlStrategy {
    @Override
    public boolean checkAccess(String accountType) {
        return accountType.equals(Constant.MANAGER_ACCOUNT_TYPE);
    }
}