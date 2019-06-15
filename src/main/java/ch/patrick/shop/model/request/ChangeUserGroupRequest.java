package ch.patrick.shop.model.request;

import ch.patrick.shop.model.CustomerType;

/**
 * Anfrage für eine Gruppenänderung
 *
 * @author Patrick
 * @version 1.0
 */
public class ChangeUserGroupRequest {

    /**
     * Id des Benutzers
     */
    private Long id;

    /**
     * Gruppe des Benutzers
     */
    private CustomerType group;

    public ChangeUserGroupRequest() {
    }

    public ChangeUserGroupRequest(Long id, CustomerType group) {
        this.id = id;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerType getGroup() {
        return group;
    }

    public void setGroup(CustomerType group) {
        this.group = group;
    }
}
