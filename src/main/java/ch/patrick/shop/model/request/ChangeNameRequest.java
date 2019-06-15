package ch.patrick.shop.model.request;

/**
 * Anfrage für eine Namensänderung
 *
 * @author Patrick
 * @version 1.0
 */
public class ChangeNameRequest {

    /**
     * Vorname
     */
    private String firstname;

    /**
     * Nachname
     */
    private String surname;

    public ChangeNameRequest() {

    }

    public ChangeNameRequest(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
