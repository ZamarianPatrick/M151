package ch.patrick.shop.service;

import ch.patrick.shop.model.Article;
import ch.patrick.shop.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service für Artikel
 *
 * @author Patrick
 * @version 1.0
 */

@Service
public class ArticleService {

    /**
     * Repository für Artikel
     */

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * Liefert Artikel über Id
     * @param id id des Artikels
     * @return Artikel
     */

    public Optional<Article> getById(Long id){
        return articleRepository.findById(id);
    }

    /**
     * Liefert alle Artikel
     *
     * @return alle Artikel
     */

    public List<Article> getAll(){
        return articleRepository.findAll();
    }

}
