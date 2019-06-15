package ch.patrick.shop.model.request;

/**
 * Anfrage für die Bearbeitung eines Artikels
 *
 * @author Patrick
 * @version 1.0
 */
public class EditArticleRequest extends AddArticleRequest{

    /**
     * Id des Artikels
     */
    private Long id;

    public EditArticleRequest() {
        super();
    }

    public EditArticleRequest(String icon, String name, Double price) {
        super(icon, name, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
