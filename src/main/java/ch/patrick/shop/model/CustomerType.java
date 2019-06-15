package ch.patrick.shop.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gruppe für Benutzer
 *
 * @author Patrick
 * @version 1.0
 */
public enum CustomerType {
    USER("Benutzer"), EMPLOYEE("Mitarbeiter"), ADMIN("Admin");

    CustomerType(String nameTag){
        this.nameTag = nameTag;
    }

    private String nameTag;

    public String getNameTag(){
        return this.nameTag;
    }

    public static List<CustomerType> all(){
        return Arrays.asList(CustomerType.values());
    }

    /**
     * Liefert alle Gruppen zurück
     *
     * @return alle Benutzergruppen
     */
    public static List<String> getAll(){
        List<String> list = new ArrayList<String>();
        for(CustomerType value : CustomerType.values()){
            list.add(value.nameTag);
        }
        return list;
    }
}
