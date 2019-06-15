package ch.patrick.shop.model.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Anfrage für die Änderung von Benutzern
 *
 * @author Patrick
 * @version 1.0
 */
public class ChangeUsersGroupRequest {

    /**
     * Liste von Anfragen für Benutzeränderung
     */
    private List<ChangeUserGroupRequest> requests = new ArrayList<>();

    public ChangeUsersGroupRequest() {

    }

    public List<ChangeUserGroupRequest> getRequests() {
        return requests;
    }

    public void add(ChangeUserGroupRequest request){
        requests.add(request);
    }
}
