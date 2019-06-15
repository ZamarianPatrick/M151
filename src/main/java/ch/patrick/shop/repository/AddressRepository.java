package ch.patrick.shop.repository;

import ch.patrick.shop.model.Address;
import ch.patrick.shop.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository für Adressen
 *
 * @author Patrick
 * @version 1.0
 */
public interface AddressRepository extends JpaRepository<Address, Long>, CrudRepository<Address, Long> {

    /**
     * Liefert Adresse nach Strasse und Ort zurück
     *
     * @param Street zu suchende Strasse
     * @param city zu suchender Ort
     * @return Adresse
     */
    Optional<Address> findByStreetAndCity(String Street, City city);
}
