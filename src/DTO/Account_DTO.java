
package DTO;

/**
 *
 * @author macbookpro
 */
public class Account_DTO {
    private String username;
    private String password;
    private String accountType;

    /**
     * Constructs a Account_DTO
     * 
     * @param username
     * @param password
     * @param accountType
     */
    public Account_DTO(String username, String password, String accountType) {
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    /**
     * Gets the account 's username.
     *
     * @return An String representing the account 's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the account 's username.
     *
     * @param username An String containing the account's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the account 's password.
     *
     * @return An String representing the account 's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the account 's password.
     *
     * @param password An String containing the account's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the account 's accountType.
     *
     * @return An String representing the account 's accountType.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account 's accountType.
     *
     * @param accountType An String containing the account's accountType.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    
}
