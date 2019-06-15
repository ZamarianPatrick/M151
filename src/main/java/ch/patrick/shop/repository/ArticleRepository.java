package ch.patrick.shop.repository;

import ch.patrick.shop.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository für Artikel
 *
 * @author Patrick
 * @version 1.0
 */
public interface ArticleRepository extends JpaRepository<Article, Long>, CrudRepository<Article, Long> {

    /**
     * Liefert ein Artikel nach seiner Id zurück
     *
     * @param id Id des Artikels
     * @return Artikel
     */
    Optional<Article> findById(Long id);

}
