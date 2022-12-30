
package DTO;

import java.util.Objects;

/**
 *
 * @author Phạm Văn Chánh
 */
public class FoodGroup_DTO {
    private int id;
    private String name;

    public FoodGroup_DTO() {
        this.id = -1;
        this.name = "";
    }

    /**
     * Constructs a FoodGroup_DTO without id.
     *
     * @param name   food group 's name
     * @see FoodGroup_DTO#FoodGroup_DTO(int, String)  FoodGroup_DTO
     */
    public FoodGroup_DTO(String name) {
        this.name = name;
    }

    /**
     * Constructs a FoodGroup_DTO with id.
     *
     * @param id     food group 's id
     * @param name   food group 's name
     */
    public FoodGroup_DTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the food group 's id.
     *
     * @return An int representing the food group 's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the food group 's id.
     *
     * @param id An int containing the
     *           food group 's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the food group 's name.
     *
     * @return A String representing the food group 's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the food group 's name.
     *
     * @param name An int containing the
     *             food group 's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the food group obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodGroup_DTO)) return false;
        FoodGroup_DTO that = (FoodGroup_DTO) o;
        return name.equals(that.name);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
