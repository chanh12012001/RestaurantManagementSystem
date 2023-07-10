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
public class DinnerTable_DTO {

    private int id;
    private String name;
    private String status;

    public DinnerTable_DTO() {
        this.id = -1;
        this.name = "";
        this.status = "";
    }
    
    /**
     * Constructs a DinnerTable_DTO with name.
     *
     * @param name dinner table 's name
     * @see DinnerTable_DTO#DinnerTable_DTO(int, String, String) DinnerTable_DTO
     */
    public DinnerTable_DTO(String name) {
        this.name = name;
    }

    /**
     * Constructs a DinnerTable_DTO without id.
     *
     * @param name dinner table 's name
     * @param status dinner table 's status
     * @see DinnerTable_DTO#DinnerTable_DTO(int, String, String) DinnerTable_DTO
     */
    public DinnerTable_DTO(String name, String status) {
        this.name = name;
        this.status = status;
    }

    /**
     * Constructs a DinnerTable_DTO with id,name and status.
     *
     * @param id dinner table 's id
     * @param name dinner table 's name
     * @param status dinner table 's status
     */
    public DinnerTable_DTO(int id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    /**
     * Gets the dinner table 's id.
     *
     * @return An int representing the dinner table 's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the dinner table 's id.
     *
     * @param id An int containing the dinner table 's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the dinner table 's name.
     *
     * @return A String representing the dinner table 's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the dinner table 's name.
     *
     * @param name An int containing the dinner table 's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the dinner table 's status.
     *
     * @return A String representing the dinner table 's status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the dinner table 's status.
     *
     * @param status An int containing the dinner table 's status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the dinner table obj argument;
     * false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DinnerTable_DTO)) {
            return false;
        }
        DinnerTable_DTO that = (DinnerTable_DTO) o;
        return name.equals(that.name) && status.equals(that.status);
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}
