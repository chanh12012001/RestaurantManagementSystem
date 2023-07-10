/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.strategy;

/**
 *
 * @author macbookpro
 */
public interface IAccessControlStrategy {
    boolean checkAccess(String accountType);
}
