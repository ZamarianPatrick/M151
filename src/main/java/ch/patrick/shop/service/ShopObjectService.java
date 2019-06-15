package ch.patrick.shop.service;

import ch.patrick.shop.model.ShopObject;
import ch.patrick.shop.repository.ShopObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service für Inhalte
 *
 * @author Patrick
 * @version 1.0
 */
@Service
public class ShopObjectService {

    /**
     * Repository für Inhalte
     */

    @Autowired
    private ShopObjectRepository shopObjectRepository;

    /**
     * Liefert ein Inhalt über die Id
     *
     * @param id id des Inhalts
     * @return Inhalt
     */

    public Optional<ShopObject> getById(Long id){return shopObjectRepository.findById(id);}

    /**
     * Liefert eine Liste aller Inhalte
     *
     * @return Liste aller Inhalte
     */

    public List<ShopObject> getAll(){
        return shopObjectRepository.findAll();
    }

    /**
     * Fügt ein Inhalt hinzu
     *
     * @param shopObject hinzuzufügender Inhalt
     *
     * @return Id des neuen Inhaltes
     */

    public Long addShopObject(ShopObject shopObject){
        shopObjectRepository.saveAndFlush(shopObject);
        return shopObject.getId();
    }

    /**
     * Löscht ein Inhalt
     *
     * @param shopObject zu löschender Inhalt
     */

    public void deleteShopObject(ShopObject shopObject){
        shopObjectRepository.delete(shopObject);
    }

}
