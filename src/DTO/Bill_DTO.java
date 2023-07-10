
package DTO;

import java.util.Date;

/**
 *
 * @author macbookpro
 */
public class Bill_DTO {
    private int ID;
    private String tableName;
    //private String paymentStatus;
    private String DateOfPayment;
    private String price;
    
     
    /**
     * Constructs a Bill_DTO with id.
     *
     * @param ID    bill's ID
     * @param tableName     bill's name
     * @param DateOfPayment     bill's Date Of Payment
     * @param price     bill's price
     */
    public Bill_DTO(int ID, String tableName, String DateOfPayment, String price) {
        this.ID = ID;
        this.tableName = tableName;
        //this.paymentStatus = paymentStatus;
        this.DateOfPayment = DateOfPayment;
        this.price = price;
    }
    
    /**
     * Gets the bill 's id.
     *
     * @return An int representing the bill 's id.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the bill 's id.
     *
     * @param ID An int containing the bill 's id.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the bill 's table name.
     *
     * @return An String representing the bill 's table name.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the bill 's table name.
     *
     * @param tableName An String containing the bill 's tableName.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the bill 's payment status.
     *
     * @return An String representing the bill 's payment status.
     */
//    public String getPaymentStatus() {
//        return paymentStatus;
//    }

    /**
     * Sets the bill 's payment status.
     *
     * @param paymentStatus An String containing the bill 's payment status.
     */
//    public void setPaymentStatus(String paymentStatus) {
//        this.paymentStatus = paymentStatus;
//    }

    /**
     * Gets the bill 's date Of Payment.
     *
     * @return An String representing the bill 's date Of Payment.
     */
    public String getDateOfPayment() {
        return DateOfPayment;
    }

    /**
     * Sets the bill 's date Of Payment.
     *
     * @param DateOfPayment An String containing the bill 's date Of Payment.
     */
    public void setDateOfPayment(String DateOfPayment) {
        this.DateOfPayment = DateOfPayment;
    }

    /**
     * Gets the bill 's price.
     *
     * @return An String representing the bill 's price.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the bill 's price.
     *
     * @param price An String containing the bill 's price.
     */
    public void setPrice(String price) {
        this.price = price;
    }
     
}
