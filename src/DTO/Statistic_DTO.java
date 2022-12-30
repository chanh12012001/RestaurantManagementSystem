/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author bao20
 */
public class Statistic_DTO {
    //Representing for day, month or year
    private String index;
    private double income;

    /**
     *
     */
    public Statistic_DTO() {
    }

    /**
     *
     * @param index
     * @param income
     */
    public Statistic_DTO(String index, double income) {
        this.index = index;
        this.income = income;
    }

    /**
     *
     * @return
     */
    public String getIndex() {
        return index;
    }

    /**
     *
     * @param index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
     *
     * @return
     */
    public double getIncome() {
        return income;
    }

    /**
     *
     * @param income
     */
    public void setIncome(double income) {
        this.income = income;
    }
}
