package ch.patrick.shop.repository;

import ch.patrick.shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository für die Benutzer
 *
 * @author Patrick
 * @version 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long> {

    /**
     * Liefert ein Benutzer nach seiner Id zurück
     *
     * @param id Id des Benutzers
     * @return Benutzer
     */

    Optional<Customer> findById(Long id);

    /**
     * Liefert ein Benutzer nach seiner Email zurück
     *
     * @param email Email des Benutzers
     * @return Benutzer
     */

    Optional<Customer> findByEmail(String email);

    /**
     * Liefert ein Benutzer nach seinem Namen zurück
     *
     * @param username Benutzername des Benutzers
     * @return Benutzer
     */

    Optional<Customer> findByUsername(String username);

}
