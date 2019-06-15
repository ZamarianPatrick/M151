package ch.patrick.shop.model.request;

/**
 * Anfrage für eine Telefonnummer Änderung
 */
public class ChangeTelNumberRequest {

    /**
     * Telefonnummer
     */
    private String number;

    public ChangeTelNumberRequest() {
    }

    public ChangeTelNumberRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
