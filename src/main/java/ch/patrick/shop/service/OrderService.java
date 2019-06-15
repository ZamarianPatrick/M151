package ch.patrick.shop.service;

import ch.patrick.shop.model.Customer;
import ch.patrick.shop.model.Order;
import ch.patrick.shop.model.OrderPosition;
import ch.patrick.shop.repository.OrderPositionRepository;
import ch.patrick.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service für Bestellungen
 *
 * @author Patrick
 * @version 1.0
 */
@Service
public class OrderService {

    /**
     * Repository für Bestellungspositionen
     */

    @Autowired
    private OrderPositionRepository orderPositionRepository;

    /**
     * Repository für Bestellungen
     */

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Liefert Liste alle Bestellpositionen einer Bestellung
     *
     * @param order Bestellung
     * @return Liste der Bestellpositionen
     */

    public List<OrderPosition> getAllPositions(Order order){
        return orderPositionRepository.getAllByOrder(order);
    }

    /**
     * Speichert eine Bestellung
     *
     * @param order zu speichernde Bestellung
     */

    public void save(Order order){
        System.out.println("save: " + order.getId());
        orderRepository.saveAndFlush(order);
    }

    /**
     * Liefert eine Liste aller Bestellungen eines Benutzers
     *
     * @param customer Benutzer
     * @return Liste aller Bestellungen
     */

    public List<Order> getOrders(Customer customer){
        return orderRepository.getAllByCustomer(customer);
    }

    /**
     * Liefert eine Bestllposition über die Id
     * @param id id der Bestellposition
     * @return Bestellposition
     */

    public Optional<OrderPosition> getOrderPositionById(Long id){
        return orderPositionRepository.getById(id);
    }

}
