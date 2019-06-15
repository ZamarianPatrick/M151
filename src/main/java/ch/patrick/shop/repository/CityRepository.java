package ch.patrick.shop.repository;

import ch.patrick.shop.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository für Orte
 *
 * @author Patrick
 * @version 1.0
 */
public interface CityRepository extends JpaRepository<City, Long>, CrudRepository<City, Long> {

    /**
     * Liefert ein Ort nach Name und PLZ zurück
     *
     * @param name Name des Ortes
     * @param zip Postleitzahl des Ortes
     * @return Ort
     */
    Optional<City> findByNameAndZip(String name, String zip);
}
