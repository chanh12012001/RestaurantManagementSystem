/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author bao20
 */
public class OrderDetail_DTO {

    private int id;
    private int idFood;
    //Name of food
    private String name;
    private int amount;
    private double price;
    private double total;

    /**
     *@see OrderBill_DTO#OrderBill_DTO(int, String, int, double) OrderBill_DTO
     */
    public OrderDetail_DTO() {
    }

    /**
     * Constructs a OrderBill_DTO with id. Use to get display data from database
     *
     * @param id order bill 's id
     * @param name name of food
     * @param amount amount of food
     * @param price food unit price
     */
    public OrderDetail_DTO(int id, String name, int amount, double price) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.total = amount * price;
    }
    
    /**
     * Constructs a OrderBill_DTO without id.
     *
     * @param name name of food
     * @param amount amount of food
     * @param price food unit price
     * @see OrderBill_DTO#OrderBill_DTO(int, String, int, double) OrderBill_DTO
     */
    public OrderDetail_DTO(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.total = amount * price;
    }

    /**
     * Constructs a OrderBill_DTO with id, amount and price. Use to add or update to
     * database
     *
     * @param id Order bill id
     * @param idFood Food id
     * @param amount amount of food
     * @param price food unit price
     */
    public OrderDetail_DTO(int id, int idFood, int amount, double price) {
        this.id = id;
        this.idFood = idFood;
        this.amount = amount;
        this.price = price;
        this.total = amount * price;
    }

    /**
     * Gets the order bill 's id
     *
     * @return An int representing the order bill 's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the order bill 's id.
     *
     * @param id order bill 's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the food 's id
     *
     * @return An int representing the food 's id.
     */
    public int getIdFood() {
        return idFood;
    }

    /**
     * Sets the food 's id.
     *
     * @param idFood food 's id.
     */
    public void setIdFood(int idFood) {
        this.idFood = idFood;
    }

    /**
     * Gets the name of food in order bill
     *
     * @return A String representing the name of food in order bill.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of food in order bill
     *
     * @param name the name of food in order bill.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the amount of food in order bill
     *
     * @return An int representing the amount of food in order bill.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of food in order bill
     *
     * @param amount amount of food
     */
    public void setAmount(int amount) {
        this.amount = amount;
        this.total = amount * price;
    }

    /**
     * Gets the unit price of food
     *
     * @return A double representing the unit price of food in order bill.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the unit price of food
     *
     * @param price the unit price of food
     */
    public void setPrice(double price) {
        this.price = price;
        this.total = amount * price;
    }

    /**
     * Gets the total price of bill
     *
     * @return A double representing the total price of bill.
     */
    public double getTotal() {
        return total;
    }
}
