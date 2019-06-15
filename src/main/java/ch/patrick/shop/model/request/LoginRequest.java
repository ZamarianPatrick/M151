package ch.patrick.shop.model.request;

/**
 * Anfrage für ein Login
 *
 * @author Patrick
 * @version 1.0
 */
public class LoginRequest {

    /**
     * Benutzername
     */

    private String user;

    /**
     * Passwort
     */

    private String password;

    public LoginRequest(){

    }

    public LoginRequest(String user, String password){
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
