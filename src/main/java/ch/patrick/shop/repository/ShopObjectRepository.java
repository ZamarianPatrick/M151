package ch.patrick.shop.repository;

import ch.patrick.shop.model.ShopObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository für Inhalte
 *
 * @author Patrick
 * @version 1.0
 */
public interface ShopObjectRepository extends JpaRepository<ShopObject, Long>, CrudRepository<ShopObject, Long> {

    /**
     * Liefert ein Inhalt über seine Id
     * @param id Id des Inhaltes
     * @return Inhalt
     */

    Optional<ShopObject> findById(Long id);
}
