package ch.patrick.shop.repository;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository für Bestellungen
 *
 * @author Patrick
 * @version 1.0
 */

public interface OrderRepository extends JpaRepository<Order, Long>, CrudRepository<Order, Long> {

    /**
     * Liste aller Bestellungen eines Benutzers
     *
     * @param customer Benutzer
     * @return Liste aller Bestellungen
     */

    List<Order> getAllByCustomer(Customer customer);
}
