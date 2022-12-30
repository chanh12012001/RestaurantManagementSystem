
package DTO;

import java.util.Objects;

/**
 *
 * @author macbookpro
 */
public class Staff_DTO {
    private String id;
    private String name;
    private String birth;
    private String sex;
    private String phone;
    private String position;
    private String salary;
    private String address;

    public Staff_DTO() {
        this.id = "";
        this.name = "";
        this.birth = "";
        this.sex = "";
        this.phone = "";
        this.position = "";
        this.salary = "";
        this.address = "";
    }

    /**
     * Constructs a Staff_DTO.
     *
     * @param id     staff's id
     * @param name   staff's name
     * @param birth staff's date of birth
     * @param sex   staff's sex
     * @param phone   staff's phone
     * @param position   staff's position
     * @param salary   staff's salary
     * @param address   staff's address
     */
    public Staff_DTO(String id, String name, String birth, String sex, String phone, String position, String salary, String address) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.sex = sex;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.address = address;
    }

    /**
     * Gets the staff's id.
     *
     * @return A String representing the staff 's id.
     */
    public String getStaffID() {
        return id;
    }

    /**
     * Sets the staff's id.
     *
     * @param id An String containing the
     *           staff's id.
     */
    public void setStaffID(String id) {
        this.id = id;
    }

    /**
     * Gets the staff 's name.
     *
     * @return A String representing the staff 's name.
     */
    public String getStaffName() {
        return name;
    }

    /**
     * Sets the staff 's name.
     *
     * @param name An String containing the
     *             staff 's name.
     */
    public void setStaffName(String name) {
        this.name = name;
    }

    /**
     * Gets the staff 's birth.
     *
     * @return A String representing staff 's birthday.
     */
    public String getStaffBirth() {
        return birth;
    }

    /**
     * Sets the staff 's birth.
     *
     * @param birth An int containing the
     *               staff 's birthday.
     */
    public void setStaffBirth(String birth) {
        this.birth = birth;
    }
    /**
     * Gets the staff 's sex.
     *
     * @return A String representing staff 's sex.
     */
    public String getStaffSex() {
        return sex;
    }

    /**
     * Sets the staff 's sex.
     *
     * @param sex An int containing the
     *               staff 's sex.
     */
    public void setStaffSex(String sex) {
        this.sex = sex;
    }
    /**
     * Gets the staff 's phone.
     *
     * @return A String representing staff 's phone.
     */
    public String getStaffPhone() {
        return phone;
    }

    /**
     * Sets the staff 's phone.
     *
     * @param phone An int containing the
     *               staff 's phone.
     */
    public void setStaffPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Gets the staff 's position.
     *
     * @return A String representing staff 's position.
     */
    public String getStaffPosition() {
        return position;
    }

    /**
     * Sets the staff 's position.
     *
     * @param position An int containing the
     *               staff 's position.
     */
    public void setStaffPosition(String position) {
        this.position = position;
    }
    /**
     * Gets the staff 's salary.
     *
     * @return A String representing staff 's salary.
     */
    public String getStaffSalary() {
        return salary;
    }

    /**
     * Sets the staff 's salary.
     *
     * @param salary An int containing the
     *               staff 's salary.
     */
    public void setStaffSalary(String salary) {
        this.salary = salary;
    }
    /**
     * Gets the staff 's address.
     *
     * @return A String representing staff 's address.
     */
    public String getStaffAddress() {
        return address;
    }

    /**
     * Sets the staff 's address.
     *
     * @param address An int containing the
     *               staff 's address.
     */
    public void setStaffAddress(String address) {
        this.address = address;
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
        if (!(o instanceof Staff_DTO)) return false;
        Staff_DTO that = (Staff_DTO) o;
        return id.equals(that.id);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, birth,sex,phone,position,salary,address);
    }
}
