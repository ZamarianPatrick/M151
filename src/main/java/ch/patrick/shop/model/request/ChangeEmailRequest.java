package ch.patrick.shop.model.request;

/**
 * Anfrage für eine Email Adressenänderung
 *  @author Patrick
 *  @version 1.0
 */
public class ChangeEmailRequest {

    /**
     * Email Adresse
     */
    private String email;

    public ChangeEmailRequest() {
    }

    public ChangeEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
