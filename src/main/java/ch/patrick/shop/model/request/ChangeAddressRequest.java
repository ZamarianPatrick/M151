package ch.patrick.shop.model.request;

/**
 * Anfrage für eine Adressänderung
 *
 * @author Patrick
 * @version 1.0
 */
public class ChangeAddressRequest {

    /**
     * Strasse
     */

    private String street;

    /**
     * Postleitzahl
     */

    private String zip;

    /**
     * Ort
     */

    private String city;

    public ChangeAddressRequest() {
    }

    public ChangeAddressRequest(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
