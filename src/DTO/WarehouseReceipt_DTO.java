/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 * @author Pham Gia Bao
 * @version 1.0
 * @since 1.0
 */
public class WarehouseReceipt_DTO {
    //id of warehouse receipt
    private int id;
    //id of ingredient
    private int idIngredient;
    private String nameIngredient;
    //Calculation unit of ingredient
    private String calUnit;
    private int amount;
    private int unitPrice;
    private String importDate;
    private String supplier;
    //Person who stock take import goods then post goods receipt into software
    //Người kiểm kê và lưu kết quả lên hệ thống
    private String storekeeper;

    public WarehouseReceipt_DTO() {
    }

    /**
     * Constructs a WarehouseReceipt_DTO without id
     *
     * @param idIngredient   ingredient's id in warehouse receipt
     * @param nameIngredient ingredient's name in warehouse receipt
     * @param calUnit        calculation of ingredient in warehouse receipt
     * @param amount         amount of ingredient in warehouse receipt
     * @param unitPrice      ingredient's unit price in warehouse receipt
     * @param importDate     import ingredient date
     * @param supplier       name of ingredient supplier
     * @param storekeeper    who save the warehouse receipt
     * @see WarehouseReceipt_DTO#WarehouseReceipt_DTO(int, int, String, String, int, int, String, String, String)
     * WarehouseReceipt_DTO
     */
    public WarehouseReceipt_DTO(int idIngredient, String nameIngredient, String calUnit, int amount,
                                int unitPrice, String importDate, String supplier, String storekeeper) {
        this.idIngredient = idIngredient;
        this.nameIngredient = nameIngredient;
        this.calUnit = calUnit;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.importDate = importDate;
        this.supplier = supplier;
        this.storekeeper = storekeeper;
    }

    /**
     * Constructs a WarehouseReceipt_DTO with id
     *
     * @param id             warehouse receipt 's id
     * @param idIngredient   ingredient's id in warehouse receipt
     * @param nameIngredient ingredient's name in warehouse receipt
     * @param calUnit        calculation of ingredient in warehouse receipt
     * @param amount         amount of ingredient in warehouse receipt
     * @param unitPrice      ingredient's unit price in warehouse receipt
     * @param importDate     import ingredient date
     * @param supplier       name of ingredient supplier
     * @param storekeeper    who save the warehouse receipt
     */
    public WarehouseReceipt_DTO(int id, int idIngredient, String nameIngredient, String calUnit,
                                int amount, int unitPrice, String importDate, String supplier, String storekeeper) {
        this.id = id;
        this.idIngredient = idIngredient;
        this.nameIngredient = nameIngredient;
        this.calUnit = calUnit;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.importDate = importDate;
        this.supplier = supplier;
        this.storekeeper = storekeeper;
    }

    /**
     * Gets the warehouse receipt 's id.
     *
     * @return An int representing the warehouse receipt 's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the warehouse receipt 's id.
     *
     * @param id warehouse receipt 's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ingredient's id in warehouse receipt.
     *
     * @return An int representing the ingredient 's id in warehouse receipt.
     */
    public int getIdIngredient() {
        return idIngredient;
    }

    /**
     * Sets the ingredient's id in warehouse receipt.
     *
     * @param idIngredient the ingredient 's id in warehouse receipt.
     */
    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    /**
     * Gets the ingredient's name in warehouse receipt.
     *
     * @return A String representing the ingredient 's name in warehouse receipt.
     */
    public String getNameIngredient() {
        return nameIngredient;
    }

    /**
     * Sets the ingredient's name in warehouse receipt.
     *
     * @param nameIngredient the ingredient's name in warehouse receipt.
     */
    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    /**
     * Gets the calculation unit of ingredient in warehouse receipt.
     *
     * @return A String representing the calculation unit of ingredient in warehouse receipt.
     */
    public String getCalUnit() {
        return calUnit;
    }

    /**
     * Sets the calculation unit of ingredient in warehouse receipt.
     *
     * @param calUnit the calculation unit of ingredient in warehouse receipt.
     */
    public void setCalUnit(String calUnit) {
        this.calUnit = calUnit;
    }

    /**
     * Gets the amount of ingredient in warehouse receipt.
     *
     * @return An int representing the amount of ingredient in warehouse receipt.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of ingredient in warehouse receipt.
     *
     * @param amount the amount of ingredient in warehouse receipt.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the ingredient's unit price in warehouse receipt.
     *
     * @return An int representing the ingredient's unit price in warehouse receipt.
     */
    public int getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the ingredient's unit price in warehouse receipt.
     *
     * @param unitPrice the ingredient's unit price in warehouse receipt.
     */
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Gets the import ingredient date.
     *
     * @return A String representing the ingredient's date import.
     */
    public String getImportDate() {
        return importDate;
    }

    /**
     * Sets the import ingredient date.
     *
     * @param importDate the ingredient's date import.
     */
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    /**
     * Gets the name of ingredient supplier.
     *
     * @return A String representing the name of ingredient supplier.
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Sets the name of ingredient supplier.
     *
     * @param exporter the name of ingredient supplier.
     */
    public void setSupplier(String exporter) {
        this.supplier = exporter;
    }

    /**
     * Gets the name of storekeeper who save this warehouse receipt.
     *
     * @return A String representing the name of storekeeper.
     */
    public String getStorekeeper() {
        return storekeeper;
    }

    /**
     * Sets the name of storekeeper who save this warehouse receipt.
     *
     * @param storekeeper the name of storekeeper.
     */
    public void setStorekeeper(String storekeeper) {
        this.storekeeper = storekeeper;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the dinner table obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WarehouseReceipt_DTO)) return false;
        WarehouseReceipt_DTO that = (WarehouseReceipt_DTO) o;
        return idIngredient == that.idIngredient && amount == that.amount && unitPrice == that.unitPrice &&
                Objects.equals(nameIngredient, that.nameIngredient) && Objects.equals(calUnit, that.calUnit) &&
                Objects.equals(importDate, that.importDate) && Objects.equals(supplier, that.supplier) &&
                Objects.equals(storekeeper, that.storekeeper);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, idIngredient, nameIngredient, calUnit, amount, unitPrice, importDate, supplier, storekeeper);
    }
}
