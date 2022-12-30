
package DTO;

import java.awt.Image;
import java.util.Objects;

/**
 *
 * @author Phạm Văn Chánh
 */
public class Food_DTO {
    private int id;
    private byte[] image;
    private String foodGroupName;
    private String name;
    private String unit;
    private int price;
    
    /**
     * Constructs a Food_DTO without id.
     *
     * @param image  food 's image
     * @param foodGroupName  food 's foodGroupName
     * @param name   food 's name
     * @param unit   food 's unit
     * @param price  food 's price
     * @see Food_DTO#Food_DTO(Image, String, String, String, int)  Food_DTO
     */
    public Food_DTO(byte[] image, String foodGroupName, String name, String unit, int price) {
        this.image = image;
        this.foodGroupName = foodGroupName;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }
    
    /**
     * Constructs a Food_DTO with id.
     *
     * @param id     food group 's id
     * @param image  food 's image
     * @param foodGroupName  food 's foodGroupName
     * @param name   food 's name
     * @param unit   food 's unit
     * @param price  food 's price
     */
    public Food_DTO(int id, byte[] image, String foodGroupName, String name, String unit, int price) {
        this.id = id;
        this.image = image;
        this.foodGroupName = foodGroupName;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    /**
     * Gets the food 's id.
     *
     * @return An int representing the food 's id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the food 's id.
     *
     * @param id An int containing the food 's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the food 's image.
     *
     * @return An Image representing the food 's image.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the food 's image.
     *
     * @param image An Image containing the food 's image.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * Gets the food 's foodGroupName.
     *
     * @return An String representing the food 's foodGroupName.
     */
    public String getFoodGroupName() {
        return foodGroupName;
    }

    /**
     * Sets the food 's foodGroupName.
     *
     * @param foodGroupName An String containing the food 's foodGroupName.
     */
    public void setFoodGroupName(String foodGroupName) {
        this.foodGroupName = foodGroupName;
    }

    /**
     * Gets the food 's name.
     *
     * @return An String representing the food 's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the food 's name.
     *
     * @param name An String containing the food 's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the food 's unit.
     *
     * @return An String representing the food 's unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the food 's unit.
     *
     * @param unit An String containing the food 's unit.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets the food 's price.
     *
     * @return An int representing the food 's price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the food 's price.
     *
     * @param price An int containing the food 's price.
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the food obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food_DTO)) return false;
        Food_DTO that = (Food_DTO) o;
        return image.equals(that.image) && foodGroupName.equals(that.foodGroupName) && name.equals(that.name) && unit.equals(that.unit) && String.valueOf(price).equals(that.price);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, image, foodGroupName, name, unit, price);
    }
}
