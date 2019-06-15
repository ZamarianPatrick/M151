package ch.patrick.shop.repository;

import ch.patrick.shop.model.Order;
import ch.patrick.shop.model.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository für die Bestellungspositionen
 *
 * @author Patrick
 * @version 1.0
 */
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long>, CrudRepository<OrderPosition, Long> {

    /**
     * Liefert eine Liste aller Bestellpositionen einer Bestellung zurück
     * @param order Bestellung
     * @return Liste aller Bestellungspositionen
     */

    List<OrderPosition> getAllByOrder(Order order);

    /**
     * Liefert eine Bestellposition über die Id zurück
     * @param id Id der Bestellposition
     * @return Bestellposition
     */

    Optional<OrderPosition> getById(Long id);
}
